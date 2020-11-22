package Model;

public class Employee {
    private String employeeID;
    private String password;
    private String jobTitle;

    public String getEmployeeID() {
        return employeeID;
    }

    public String getPassword() {
        return password;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
