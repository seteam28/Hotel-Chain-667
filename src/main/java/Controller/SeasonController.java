package Controller;

import Converter.SeasonConverter;
import Model.Season;

import java.sql.*;
import java.util.ArrayList;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SeasonController {
    public SeasonController(){}

    public ArrayList<Season> searchSeasons(int hotelID) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        PreparedStatement preparedStatement = connection.prepareStatement( "SELECT S.SeasonName, S.startDate, S.endDate, S.coefficient\n" +
                "FROM mydb.Hotel_Has_Season H, mydb.Season S\n" +
                "WHERE H.hotelID = ? AND H.SeasonName = S.seasonName");
        preparedStatement.setInt(1, hotelID);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new SeasonConverter(resultSet).seasonArray();
    }

    public void deleteSeasons(String seasonName, int hotelID) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        PreparedStatement preparedStatement = connection.prepareStatement( "DELETE FROM `mydb`.`Hotel_has_Season` " +
                "WHERE (`seasonName` = ?) and (`hotelID` = ?);");
        preparedStatement.setString(1, seasonName);
        preparedStatement.setInt(2, hotelID);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void addSeason(int hotelID, String seasonName, String startDate, String endDate, double coefficient) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "password");
        System.out.println("here2");
        PreparedStatement preparedStatement = connection.prepareStatement( "INSERT INTO mydb.Season VALUES (?, ?, ?, ?);");
        preparedStatement.setString(1, seasonName);
        System.out.println("here21");
        preparedStatement.setDate(2, Date.valueOf(startDate));
        System.out.println("here22");
        preparedStatement.setDate(3, Date.valueOf(endDate));
        System.out.println("here23");
        preparedStatement.setDouble(4, coefficient);
        System.out.println("here3");
        preparedStatement.executeUpdate();
        System.out.println("here4");
        PreparedStatement updateStatement = connection.prepareStatement("INSERT INTO mydb.Hotel_has_Season VALUES (?, ?)");
        updateStatement.setString(1, seasonName);
        updateStatement.setInt(2, hotelID);
        updateStatement.executeUpdate();
    }

    public void notifyUsers(int hotelID, String seasonName, String startDate, String endDate, double coefficient, boolean toAdd){
        final String email = "noreplyhotelchain667@gmail.com";
        final String password = "HotelChain777";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        HotelController controller = new HotelController();

        try {
            ArrayList<String> emailList = controller.getEmails(hotelID);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));

            for(String userEmail: emailList) {
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(userEmail));
                if(toAdd) {
                    message.setSubject("Season add notification");

                    //set the content of the email message
                    message.setText("Dear client,\n We have an offer for you. Starting from " + startDate + " we have a season " + seasonName + "\n Do not miss that opportunity!");

                    //send the email message
                }
                else{
                    message.setSubject("Season Delete notification");

                    //set the content of the email message
                    message.setText("Dear client,\n Unfortunately our season offer " + seasonName + " is not active. Sorry for inconveniences");

                    //send the email message
                }
                Transport.send(message);
                System.out.println("Email Message Sent Successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
