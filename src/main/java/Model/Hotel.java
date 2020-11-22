package Model;
import java.util.ArrayList;

public class Hotel {
    private String name;
    private String address;
    private String[] features;
    private int id;
    private String[] phone;
    private int price;
    private String description;
    private ArrayList<ArrayList<String>> seasons = new ArrayList<>();

    public Hotel() {
    }

    public Hotel(String name, String address, String[] features, int id, String[] phone) {
        setName(name);
        setAddress(address);
        setFeatures(features);
        setId(id);
        setPhone(phone);
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String[] getFeatures() { return features; }

    public void setFeatures(String[] features) {
        this.features = features;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getPhone() {
        return phone;
    }

    public void setPhone(String[] phone) {
        this.phone = phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) { this.price = price; }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public ArrayList<ArrayList<String>> getSeasons() { return seasons; }

    public void setSeasons(ArrayList<ArrayList<String>> seasons) { this.seasons = seasons; }
}