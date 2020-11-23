package Converter;

import Controller.ScheduleController;
import Controller.HotelController;
import Controller.RoomController;
import Model.Schedule;
import Model.Hotel;
import Model.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleConverter {

    ResultSet resultSet;
    public ScheduleConverter(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public Schedule singleSchedule() throws Exception {
        ScheduleController controller = new ScheduleController();
        Schedule schedule = new Schedule();
        schedule.setEndDate(resultSet.getString("endTime"));
        schedule.setStartDate(resultSet.getString("startTime"));
        schedule.setEmployeeID(resultSet.getString("EmployeeID"));
        schedule.setSalary(resultSet.getInt("TotalPayment"));
        return schedule;
    }


    public ArrayList<Schedule> scheduleArray() throws Exception {
        ArrayList<Schedule> schedules = new ArrayList<>();
        while(resultSet.next()) {
            schedules.add(this.singleSchedule());
        }
        return schedules;
    }
}
