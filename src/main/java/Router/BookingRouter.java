package Router;

import Controller.BookingController;
import Model.Booking;
import Model.Result;
import Model.ResultStatus;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/booking")
public class BookingRouter extends HttpServlet{


    private BookingController controller = new BookingController();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        Gson gson = new Gson();

        try {
            String username = req.getParameter("username");
            String hotelID = req.getParameter("id");
            int id = Integer.parseInt(hotelID);
            ArrayList<Booking> bookings = controller.searchBookings(username, id);
            Result<ArrayList<Booking>> result = new Result<>(ResultStatus.success, bookings);
            writer.print(gson.toJson(result));
            writer.flush();
        } catch (Exception e) {
            Result<String> result = new Result<>(ResultStatus.error, e.getMessage());
            writer.print(gson.toJson(result));
            writer.flush();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        Gson gson = new Gson();

        try {
            int hotelID = Integer.parseInt(req.getParameter("hotelId"));
            String checkIn = req.getParameter("checkIn");
            String checkOut = req.getParameter("checkOut");
            int numberOfAdults = Integer.parseInt(req.getParameter("adults"));
            int numberOfChildren = Integer.parseInt(req.getParameter("children"));
            int roomNumber = Integer.parseInt(req.getParameter("roomNumber"));
            String username = req.getParameter("username");
            controller.addBooking(hotelID, checkIn, checkOut, numberOfAdults, numberOfChildren,
                    roomNumber, username);
            Result<String> result = new Result<>(ResultStatus.success, "Successfully created");
            writer.print(gson.toJson(result));
            writer.flush();
        } catch (Exception e) {
            Result<String> result = new Result<>(ResultStatus.error, e.getMessage());
            writer.print(gson.toJson(result));
            writer.flush();
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        Gson gson = new Gson();

        try {
            String username = req.getParameter("username");
            String roomTypeID = req.getParameter("roomTypeID");
            int id = Integer.parseInt(roomTypeID);
            controller.deleteBookings(username, id);
            Result<String> result = new Result<>(ResultStatus.success, "Successfully Deleted");
            writer.print(gson.toJson(result));
            writer.flush();
        } catch (Exception e) {
            Result<String> result = new Result<>(ResultStatus.error, e.getMessage());
            writer.print(gson.toJson(result));
            writer.flush();
        }
    }
}
