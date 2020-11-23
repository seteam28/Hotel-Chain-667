package Model;

public class Schedule {
    private int salary;
    private String startDate = new String();
    private String endDate = new String();
    private String employeeID = new String();



    public Schedule(){}


    public Schedule(String startDate, String endDate, String employeeID, int salary){
        setStartDate(startDate);
        setEndDate(endDate);
        setEmployeeID(employeeID);
        setSalary(salary);
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setEmployeeID(String employeeID) { this.employeeID = employeeID; }

    public void setSalary(int salary) { this.salary = salary; }

    public void setEndDate(String endDate) { this.endDate = endDate; }

    public void setStartDate(String startDate) {this.startDate = startDate; }


}
