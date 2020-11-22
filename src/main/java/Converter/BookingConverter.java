package Converter;

import Controller.BookingController;
import Controller.RoomController;
import Model.Booking;
import Model.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class BookingConverter {

    ResultSet resultSet;

    public BookingConverter(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public Booking singleBooking() throws Exception {
        BookingController controller = new BookingController();
        Booking booking = new Booking();
        booking.setUsername(resultSet.getString("guestID"));
            booking.setFirstName(resultSet.getString("firstName"));
        booking.setSecondName(resultSet.getString("secondName"));
        booking.setStartDate(resultSet.getDate("checkIN").toString());
        booking.setEndDate(resultSet.getDate("checkOut").toString());
        booking.setRoomNumber(resultSet.getInt("roomType"));
        booking.setNumberofAdults(resultSet.getInt("numberofAdults"));
        booking.setNumberofChildren(resultSet.getInt("numberOfChildren"));
        booking.setPhoneNumber(resultSet.getString("mobilePhoneNumber"));
        booking.setNaming(resultSet.getString("naming"));
        return booking;
    }

    public ArrayList<Booking> bookingArray() throws Exception {
        ArrayList<Booking> bookings = new ArrayList<>();
        while(resultSet.next()) {
            bookings.add(this.singleBooking());
        }
        return bookings;
    }
}
