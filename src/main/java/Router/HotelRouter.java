package Router;

import Controller.HotelController;
import Model.Hotel;
import Model.Result;
import Model.ResultStatus;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/hotels")
public class HotelRouter extends HttpServlet {
    private HotelController controller = new HotelController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/hotels/search.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        Gson gson = new Gson();
        try {
            Hotel hotel = controller.getHotel(Integer.parseInt(id));
            req.setAttribute("hotel", gson.toJson(hotel));

            RequestDispatcher dispatcher = req.getRequestDispatcher("views/hotels/hotel.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            resp.sendRedirect("/hotels/search");
        }
    }
}