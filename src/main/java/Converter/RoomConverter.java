package Converter;

import Controller.RoomController;
import Model.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomConverter {

    ResultSet resultSet;
    public RoomConverter(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public Room singleRoom() throws Exception {
        RoomController controller = new RoomController();
        Room room = new Room();
        room.setRoomNumber(resultSet.getInt("RoomNumber"));
        room.setFloor(resultSet.getInt("Floor"));
        room.setClean(true);
        room.setCapacity(resultSet.getInt("capacity"));
        room.setSize(resultSet.getInt("size"));
        room.setFeatures((String[]) controller.getFeatures(room.getRoomNumber()));
        room.setNaming(resultSet.getString("Naming"));
        return room;
    }

    public ArrayList<Room> roomArray() throws Exception {
        ArrayList<Room> rooms = new ArrayList<>();
        while(resultSet.next()) {
            rooms.add(this.singleRoom());
        }
        return rooms;
    }

}
