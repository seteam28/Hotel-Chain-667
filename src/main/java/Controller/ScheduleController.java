package Controller;

import Converter.BookingConverter;
import Converter.ScheduleConverter;
import Converter.HotelConverter;
import Model.Booking;
import Model.Schedule;
import Model.Hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

public class ScheduleController {

    public ScheduleController(){}



    public ArrayList<Schedule> searchSchedules(String username, int hotelID) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        PreparedStatement preparedStatement = connection.prepareStatement( "SELECT E.EmployeeID, E.jobTitle, S.StartTime, S.EndTime, E.Salary * TIMESTAMPDIFF(hour, S.StartTime, S.EndTime) as TotalPayment\n" +
                                                                                "FROM mydb.Employee E, mydb.Schedules S\n" +
                                                                                "WHERE E.EmployeeID = S.EmployeeID;");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new ScheduleConverter(resultSet).scheduleArray();
    }



}
