package Converter;

import Controller.HotelController;
import Model.Hotel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelConverter {

    ResultSet resultSet;

    public HotelConverter(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    //Converts SQL result to single hotel
    public Hotel singleHotel() throws Exception {
        HotelController controller = new HotelController();
        Hotel hotel = new Hotel();
        hotel.setName(resultSet.getString("name"));
        hotel.setFeatures((String[]) controller.getFeatures(resultSet.getInt("hotelID")));
        hotel.setDescription(resultSet.getString("Description"));
        hotel.setId(resultSet.getInt("hotelID"));
        hotel.setPrice(resultSet.getInt("price"));
        return hotel;
    }

    //Converts SQL result to single hotel with some other features included
    public Hotel singleHotelByID() throws Exception {
        HotelController controller = new HotelController();
        Hotel hotel = new Hotel();
        hotel.setId(resultSet.getInt("hotelID"));
        hotel.setName(resultSet.getString("name"));
        hotel.setFeatures((String[]) controller.getFeatures(hotel.getId()));
        hotel.setPrice(resultSet.getInt("price"));
        hotel.setPhone((String[]) controller.getPhone(hotel.getId()));
        hotel.setSeasons(controller.getSeasons(hotel.getId()));
        hotel.setAddress(resultSet.getString("Address"));
        return hotel;
    }

    //Converts SQL result several hotels
    public ArrayList<Hotel> hotelArray() throws Exception {
        ArrayList<Hotel> hotels = new ArrayList<>();
        while(resultSet.next()) {
            hotels.add(this.singleHotel());
        }
        return hotels;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}