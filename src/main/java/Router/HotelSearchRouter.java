package Router;

import Controller.HotelController;
import Model.Hotel;
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

@WebServlet("/hotels/search")
public class HotelSearchRouter extends HttpServlet {
    private HotelController controller = new HotelController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        Gson gson = new Gson();

        try {
            String name = req.getParameter("name");
            String city = req.getParameter("city");
            String season = req.getParameter("season");
            System.out.println(name);
            System.out.println(city);
            System.out.println(season);
            ArrayList<Hotel> hotels = controller.searchHotels(name, season, city);
            Result<ArrayList<Hotel>> result = new Result<>(ResultStatus.success, hotels);
            writer.print(gson.toJson(result));
            writer.flush();
        } catch (Exception e) {
            Result<ArrayList<Hotel>> result = new Result<>(ResultStatus.error, e.getMessage());
            writer.print(gson.toJson(result));
            writer.flush();
        }
    }
}
