package Router;

import Controller.RoomController;
import Model.Room;
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

@WebServlet("/hotels/rooms")
public class RoomSearchRouter extends HttpServlet{
    private RoomController controller = new RoomController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        Gson gson = new Gson();
        try {
            String id = req.getParameter("id");
            ArrayList<Room> rooms = controller.searchRooms(Integer.parseInt(id));
            Result<ArrayList<Room>> result = new Result<>(ResultStatus.success, rooms);
            writer.print(gson.toJson(result));
            writer.flush();
        } catch (Exception e) {
            Result<ArrayList<Room>> result = new Result<>(ResultStatus.error, e.getMessage());
            writer.print(gson.toJson(result));
            writer.flush();
        }
    }
}
