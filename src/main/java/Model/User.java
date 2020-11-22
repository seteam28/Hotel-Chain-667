package Model;

public class User {
    private String firstName;
    private String secondName;
    private String userName;
    private String password;
    private String homePhoneNumber;
    private int mobilePhoneNumber;
    private String idType;
    private int idNumber;
    private String address;
    private int numberofAdults;
    private int numberofChildren;



    public User() {

    }

    public User(String firstName, String secondName, String userName, String homePhoneNumber, int mobilePhoneNumber,
                String idType, int idNumber, String address, int numberofAdults, int numberofChildren) {
        setFirstName(firstName);
        setSecondName(secondName);
        setUsername(userName);
        setHomePhoneNumber(homePhoneNumber);
        setMobilePhoneNumber(mobilePhoneNumber);
        setIdType(idType);
        setIdNumber(idNumber);
        setAddress(address);
        setNumberofAdults(numberofAdults);
        setNumberofChildren(numberofChildren);
    }

    public String hashPassword(String password){
        return Integer.toString(password.hashCode());
    }

    public String getUsername(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getSecondName(){
        return secondName;
    }

    public String getHomePhoneNumber() {return homePhoneNumber; }

    public int getMobilePhoneNumber() {return mobilePhoneNumber; }

    public String getIdType() {return idType;}

    public int getIdNumber() { return idNumber; }

    public String getAddress() {return address; }

    public int getNumberofAdults() { return numberofAdults; }

    public int getNumberofChildren() { return numberofChildren; }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName){
        this.secondName = secondName;
    }

    public void setPassword(String password){
        this.password = hashPassword(password);
    }

    public void setHomePhoneNumber(String homePhoneNumber){
        this.homePhoneNumber = homePhoneNumber;
    }

    public void setMobilePhoneNumber(int mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumberofAdults(int numberofAdults) {
        this.numberofAdults = numberofAdults;
    }

    public void setNumberofChildren(int numberofChildren) {
        this.numberofChildren = numberofChildren;
    }
}
