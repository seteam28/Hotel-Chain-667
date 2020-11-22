package Controller;

import Converter.AuthConverter;
import Model.User;
import Model.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthController {

    public AuthController() {}

    /*
    *  Check whether the password confirmed
    */
    public boolean isPasswordConfirmed(String password, String repeated) {
         return password.equals(repeated);
    }

    /*
     *  Check whether the password is valid
     *  - Checks length
     *  - Checks one capital letter
     */
    public boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }

    /*
    *
    */
    public User login(String username,
                      String password) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                                                           "root",
                                                       "password");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from mydb.Guest where guestID = ? and password = ?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return new AuthConverter(resultSet).singleUser();
    }

    public Employee employeeLogin(String username,
                      String password) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from mydb.Employee where EmployeeID = ? and password = ?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return new AuthConverter(resultSet).singleEmployee();
    }


    /*
     *
     */
    public void register(String username,
                         String firstName,
                         String secondName,
                         String password,
                         String repeatedPassword,
                         String address,
                         String homePhoneNumber, String mobilePhoneNumber,
                         String idType, String idNumber,
                         String numberofAdults, String numberofChidren)
            throws Exception {
        if(!isPasswordConfirmed(password, repeatedPassword)) {
            throw new Exception("Please confirm your password");
        }
        if(!isPasswordValid(password)) {
            throw new Exception("Please enter save password");
        }

        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                                                           "root",
                                                       "password");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Guest VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, homePhoneNumber);
        preparedStatement.setInt(3, Integer.parseInt(idNumber));
        preparedStatement.setString(4, idType);
        preparedStatement.setInt(5, Integer.parseInt(mobilePhoneNumber));
        preparedStatement.setString(6, address);
        preparedStatement.setString(7, firstName);
        preparedStatement.setString(8, secondName);
        preparedStatement.setString(9, password);
        preparedStatement.execute();
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
