package Model;

public class Room {
    private int RoomNumber;
    private int floor;
    private int capacity;
    private int size;
    private boolean clean;
    private String naming;
    private String guestID;
    private String[] features;

    public Room(){}

    public Room(int roomNumber, int floor, boolean isClean, int capacity, int size, String[] features, String naming){
        setRoomNumber(roomNumber);
        setFloor(floor);
        setClean(clean);
        setCapacity(capacity);
        setSize(size);
        setFeatures(features);
        setNaming(naming);
    }

    public boolean getClean() { return clean; }

    public int getCapacity() { return capacity; }

    public int getFloor() { return floor; }

    public int getRoomNumber() { return RoomNumber; }

    public int getSize() { return size; }

    public String getNaming() {return naming;}

    public String getGuestID() { return guestID; }

    public String[] getFeatures() { return features; }

    public void setCapacity(int capacity) { this.capacity = capacity; }

    public void setClean(boolean clean) { this.clean = clean; }

    public void setFloor(int floor) { this.floor = floor; }

    public void setGuestID(String guestID) { this.guestID = guestID; }

    public void setNaming(String naming) { this.naming = naming; }

    public void setRoomNumber(int roomNumber) { RoomNumber = roomNumber; }

    public void setSize(int size) { this.size = size; }

    public void setFeatures(String[] features) { this.features = features; }
}
