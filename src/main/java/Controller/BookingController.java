package Controller;

import Converter.BookingConverter;
import Converter.HotelConverter;
import Model.Booking;
import Model.Hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

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

    public void addBooking(int hotelID, String checkIn, String checkOut,
                           int numberofAdults, int numberofChildren, int roomNumber,
                           String username) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");

        int roomTypeID = getRoomTypeID(hotelID, roomNumber);
        System.out.println(roomTypeID);
        PreparedStatement preparedStatement = connection.prepareStatement( "INSERT INTO mydb.Guest_reserves_RoomType VALUES (?, ?, ?, ?, ?, ?);");
        preparedStatement.setString(1, username);
        preparedStatement.setInt(2, roomNumber);
        preparedStatement.setDate(3, Date.valueOf(checkIn));
        preparedStatement.setDate(4, Date.valueOf(checkOut));
        preparedStatement.setInt(5, numberofChildren);
        preparedStatement.setInt(6, numberofAdults);
        preparedStatement.executeUpdate();

        PreparedStatement updateStatement = connection.prepareStatement("UPDATE `mydb`.`Room` SET `guestID` = ? " +
                "WHERE (`roomNumber` = ?) and (`hotelID` = ?);");
        updateStatement.setString(1, username);
        updateStatement.setInt(2, roomNumber);
        updateStatement.setInt(3, hotelID);

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
}

