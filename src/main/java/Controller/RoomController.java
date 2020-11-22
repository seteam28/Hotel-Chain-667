package Controller;

import Converter.RoomConverter;
import Model.Room;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

public class RoomController {

    public RoomController() {}

    public ArrayList<Room> searchRooms(int id) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");


        PreparedStatement preparedStatement = connection.prepareStatement("SELECT distinct R.RoomNumber, R.Floor, R.isClean, RT.Capacity, RT.Size, RT.Naming\n" +
                                                                               "FROM mydb.Hotel H, mydb.Room R, mydb.RoomType RT\n" +
                                                                                "WHERE H.hotelID = ? AND RT.hotelID = H.hotelID AND R.RoomTypeID = RT.RoomTypeID;");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        return new RoomConverter(resultSet).roomArray();
    }

    public String[] getFeatures(int roomNumber) throws Exception{
        ArrayList <String> features = new ArrayList<>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT distinct RTF.Feature\n" +
                                                                               "FROM mydb.Room R, mydb.RoomTypeFeatures RTF\n" +
                                                                                "WHERE R.RoomNumber = ? AND R.RoomTypeID = RTF.RoomTypeID;");
        preparedStatement.setInt(1, roomNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            features.add(resultSet.getString("feature"));
        }

        String[] featuresArray = new String[features.size()];
        featuresArray = features.toArray(featuresArray);

        return featuresArray;
    }


}
