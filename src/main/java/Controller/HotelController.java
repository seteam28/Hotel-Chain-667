package Controller;

import Converter.HotelConverter;
import Model.Hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

public class HotelController {

    public HotelController() {}

    public Hotel getHotel(int id) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        PreparedStatement preparedStatement = connection.prepareStatement( "SELECT H1.HotelID, H1.Name, H1.Description, H1.Price, HS.seasonName, H1.Address, S.seasonName, S.startDate, S.endDate\n" +
                                                                               "FROM mydb.Hotel H1, mydb.Hotel_Has_Season HS, mydb.Season S\n" +
                                                                               "WHERE H1.HotelID = ? AND HS.HotelID = H1.HotelID AND H1.hotelID = HS.hotelID AND HS.seasonName = S.seasonName;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new HotelConverter(resultSet).singleHotelByID();
    }

    public ArrayList<Hotel> searchHotels(String name, String season, String city) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        String statement = "SELECT distinct H1.HotelID, H1.Name, H1.Description, H1.Price\n" +
                "FROM mydb.Hotel H1, mydb.Hotel_has_season HS\n" +
                "WHERE H1.Name = ?";

        if(city != null){
            statement += " AND H1.City = ?";
        }
        if(season != null)
            statement += " AND HS.hotelID = H1.HotelID AND HS.SeasonName = ?";

        statement += ";";

        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, name);
        if(city != null)
            preparedStatement.setString(2, city);
        if(season != null)
            preparedStatement.setString(3, season);
        ResultSet resultSet = preparedStatement.executeQuery();
        return new HotelConverter(resultSet).hotelArray();
    }

    public String[] getFeatures(int id) throws Exception{
        ArrayList <String> features = new ArrayList<>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT Features\n" +
                                                                            "FROM mydb.Features F\n" +
                                                                            "WHERE F.hotelID = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            features.add(resultSet.getString("features"));
        }

        String[] featuresArray = new String[features.size()];
        featuresArray = features.toArray(featuresArray);

        return featuresArray;
    }

    public String[] getPhone(int id) throws Exception{
        ArrayList <String> features = new ArrayList<>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT PhoneNumber\n" +
                "FROM mydb.PhoneNumber P\n" +
                "WHERE P.hotelID = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            features.add(resultSet.getString("PhoneNumber"));
        }

        String[] featuresArray = new String[features.size()];
        featuresArray = features.toArray(featuresArray);

        return featuresArray;
    }

    public ArrayList<ArrayList<String>> getSeasons(int id) throws Exception{
        ArrayList<ArrayList<String>> seasons = new ArrayList<>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT S.seasonName, S.startDate, S.endDate\n" +
                "FROM mydb.Season S, mydb.Hotel_has_Season HS, mydb.Hotel H\n" +
                "WHERE H.hotelID = ? AND H.hotelID = HS.hotelID AND HS.seasonName = S.seasonName;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            ArrayList <String> season = new ArrayList<>();
            season.add(resultSet.getString("seasonName"));
            System.out.println(season.get(0));
            season.add(resultSet.getDate("startDate").toString());
            season.add(resultSet.getDate("endDate").toString());
            seasons.add(season);
        }
        return seasons;
    }

    public ArrayList<String> getEmails(int id) throws Exception{
        ArrayList<String> emails = new ArrayList<>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT G.EmailAddress\n" +
                "FROM mydb.Guest G, mydb.Guest_reserves_RoomType GRR, mydb.RoomType R\n" +
                "WHERE G.GuestID = GRR.GuestID AND GRR.RoomTypeID = R.RoomTypeID AND R.HotelID = ?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            emails.add(resultSet.getString("emailAddress"));
        }
        return emails;
    }


}