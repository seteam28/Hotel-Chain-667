package Controller;

import Converter.BookingConverter;
import Converter.HotelConverter;
import Model.Booking;
import Model.Hotel;
import org.joda.time.DateMidnight;
import org.joda.time.Days;
import org.joda.time.ReadablePartial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookingController {

    public BookingController(){}

    public ArrayList<Booking> searchBookings(String username, int hotelID) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        PreparedStatement preparedStatement = connection.prepareStatement( "SELECT  G.GuestID, G.firstName, G.secondName, B.checkIN, B.checkOut, B.numberOfAdults, B.numberofChildren, R.RoomNumber, RT.Naming, G.mobilePhoneNumber\n" +
                                                                                "FROM mydb.Guest G, mydb.Guest_reserves_roomtype B, mydb.Room R, mydb.Hotel H, mydb.RoomType RT\n" +
                                                                                "WHERE G.GuestID = B.GuestID AND G.GuestID = ? AND R.guestID = G.GuestID AND H.HotelID = ? AND R.RoomTypeID = RT.RoomTypeID \n" +
                                                                                "AND B.RoomTypeID = RT.RoomTypeID AND H.HotelID = RT.HotelID;");
        preparedStatement.setString(1, username);
        preparedStatement.setInt(2, hotelID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new BookingConverter(resultSet).bookingArray();
    }

    public void deleteBookings(String username, int roomTypeID) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        PreparedStatement preparedStatement = connection.prepareStatement( "DELETE FROM `mydb`.`Guest_reserves_RoomType` " +
                                                                                "WHERE (`guestID` = ?) and (`RoomTypeID` = ?);");
        preparedStatement.setString(1, username);
        preparedStatement.setInt(2, roomTypeID);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        PreparedStatement updateStatement = connection.prepareStatement("UPDATE `mydb`.`Room` SET `guestID` = NULL " +
                                                                        "WHERE (`guestID` = ?) and (`RoomTypeID` = ?);");

        updateStatement.setString(1, username);
        updateStatement.setInt(2, roomTypeID);
        updateStatement.executeUpdate();

    }

    protected int getRoomTypeID(int hotelID, int roomNumber) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        PreparedStatement preparedStatement = connection.prepareStatement( "SELECT RT.RoomTypeID\n" +
                "FROM mydb.RoomType RT, mydb.hotel H, mydb.Room R\n" +
                "WHERE R.RoomNumber = ? AND H.hotelID = ? AND R.RoomTypeID = RT.RoomTypeID AND RT.HotelID = H.hotelID;");
        preparedStatement.setInt(1, roomNumber);
        preparedStatement.setInt(2, hotelID);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        return rs.getInt("RoomTypeID");
    }

    public float getPrice(String username, int RoomTypeID, int hotelID, String checkIn, String checkOut) throws Exception {
        float[] dayCoefs = new float[]{ 1.0f, 1.0f, 1.0f, 1.3f, 1.4f, 1.5f, 1.5f };
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        float dayCoef = dayCoefs[day - 2];

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        PreparedStatement preparedStatement1 = connection.prepareStatement( "SELECT s.coefficient\n" +
                "FROM mydb.Season s WHERE ? BETWEEN s.startDate AND s.endDate;");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        String currDate = formatter.format(date);
        //currDate = "2020-07-15";
        preparedStatement1.setString(1, currDate);
        ResultSet rs1 = preparedStatement1.executeQuery();

        float seasonCoef = 1.0f;

        while(rs1.next()){
            float coef = rs1.getFloat("coefficient");
            seasonCoef = seasonCoef * coef;
        }

        PreparedStatement preparedStatement2 = connection.prepareStatement( "SELECT discount\n" +
                "FROM mydb.Guest_has_Category ghc, Category c WHERE ghc.category=c.category AND guestID=?");

        preparedStatement2.setString(1, username);
        ResultSet rs2 = preparedStatement2.executeQuery();
        float discount = 1.0f;

        while(rs2.next()){
            discount = discount * (rs2.getFloat("discount"));
        }

        PreparedStatement preparedStatement3 = connection.prepareStatement( "SELECT rt.price\n" +
                "FROM mydb.RoomType rt WHERE rt.hotelID=? AND rt.RoomTypeID=?");
        preparedStatement3.setInt(1, hotelID);
        preparedStatement3.setInt(2, RoomTypeID);
        ResultSet rs3 = preparedStatement3.executeQuery();
        rs3.next();
        float price = rs3.getFloat("price");

        DateMidnight start = new DateMidnight(checkIn);
        DateMidnight end = new DateMidnight(checkOut);
        int days = Days.daysBetween(start, end).getDays();

        return days * price * discount * seasonCoef * dayCoef;
    }

    public void addBooking(int hotelID, String checkIn, String checkOut,
                           int numberofAdults, int numberofChildren, int roomNumber,
                           String username) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        int roomTypeID = getRoomTypeID(hotelID, roomNumber);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO mydb.Guest_reserves_RoomType VALUES (?, ?, ?, ?, ?, ?, ?);");
        preparedStatement.setString(1, username);
        preparedStatement.setInt(2, roomTypeID);
        preparedStatement.setDate(3, Date.valueOf(checkIn));
        preparedStatement.setDate(4, Date.valueOf(checkOut));
        preparedStatement.setInt(5, numberofChildren);
        preparedStatement.setInt(6, numberofAdults);
        preparedStatement.setFloat(7, getPrice(username, roomTypeID, hotelID, checkIn, checkOut));
        preparedStatement.executeUpdate();
        System.out.println(222);
        PreparedStatement updateStatement = connection.prepareStatement("UPDATE `mydb`.`Room` SET `guestID` = ? " +
                "WHERE (`roomNumber` = ?) and (`hotelID` = ?);");
        updateStatement.setString(1, username);
        updateStatement.setInt(2, roomNumber);
        updateStatement.setInt(3, hotelID);

        updateStatement.executeUpdate();

    }
}

