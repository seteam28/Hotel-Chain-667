package Router;

import Controller.ScheduleController;
import Model.Schedule;
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

@WebServlet("/schedule")
public class ScheduleRouter extends HttpServlet{

    private ScheduleController controller = new ScheduleController();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        Gson gson = new Gson();

        try {
            String username = req.getParameter("username");
            String hotelID = req.getParameter("id");
            int id = Integer.parseInt(hotelID);
            ArrayList<Schedule> schedule = controller.searchSchedules(username, id);
            Result<ArrayList<Schedule>> result = new Result<>(ResultStatus.success, schedule);
            writer.print(gson.toJson(result));
            writer.flush();
        } catch (Exception e) {
            Result<String> result = new Result<>(ResultStatus.error, e.getMessage());
            writer.print(gson.toJson(result));
            writer.flush();
        }
    }

}
