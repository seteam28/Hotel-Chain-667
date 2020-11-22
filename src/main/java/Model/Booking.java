package Model;

import java.sql.Date;

public class Booking {
    private String startDate = new String();
    private String endDate = new String();
    private String username = new String();
    private String naming = new String();
    private String firstName = new String();
    private String secondName = new String();
    private int numberofAdults;
    private int numberofChildren;
    private int roomNumber;
    private String phoneNumber;


    public Booking(){}

    public Booking(String startDate, String endDate, String phoneNumber,
                   int numberofAdults, int numberofChildren, int roomNumber, String naming,
                   String username, String firstName, String secondName){
        setStartDate(startDate);
        setEndDate(endDate);
        setPhoneNumber(phoneNumber);
        setNumberofAdults(numberofAdults);
        setNumberofChildren(numberofChildren);
        setRoomNumber(roomNumber);
        setNaming(naming);
        setUsername(username);
        setFirstName(firstName);
        setSecondName(secondName);
    }

    public void setNumberofChildren(int numberofChildren) { this.numberofChildren = numberofChildren; }

    public void setNumberofAdults(int numberofAdults) { this.numberofAdults = numberofAdults; }

    public void setEndDate(String endDate) { this.endDate = endDate; }

    public void setStartDate(String startDate) {this.startDate = startDate; }

    public void setNaming(String naming) { this.naming = naming; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public void setUsername(String username) { this.username = username; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setSecondName(String secondName) { this.secondName = secondName; }

    public int getRoomNumber() { return roomNumber; }

    public int getNumberofAdults() { return numberofAdults; }

    public int getNumberofChildren() { return numberofChildren; }

    public String getEndDate() { return endDate; }

    public String getStartDate() { return startDate; }

    public String getNaming() { return naming; }

    public String getUsername() { return username; }

    public String getFirstName() { return firstName; }

    public String getSecondName() { return secondName; }

    public String getPhoneNumber() { return phoneNumber; }
}
