package Converter;

import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthConverter {

    ResultSet resultSet;

    public AuthConverter(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    //Converts SQL result to single user
    public User singleUser() throws SQLException {
        User user = new User();
        user.setFirstName(resultSet.getString("firstName"));
        user.setSecondName(resultSet.getString("secondName"));
        user.setUsername(resultSet.getString("guestID"));
        return user;
    }

    //Converts SQL result several users
    public ArrayList<User> userArray() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        while(resultSet.next()) {
            users.add(this.singleUser());
        }

        return users;
    }

}
