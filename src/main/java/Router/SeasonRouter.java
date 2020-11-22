package Router;

import Controller.SeasonController;
import Model.Season;
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

@WebServlet("/season")
public class SeasonRouter extends HttpServlet{


    private SeasonController controller = new SeasonController();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        Gson gson = new Gson();
        try {
            int hotelID = Integer.parseInt(req.getParameter("hotelId"));
            ArrayList<Season> seasons = controller.searchSeasons(hotelID);
            Result<ArrayList<Season>> result = new Result<>(ResultStatus.success, seasons);
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
            String seasonName = req.getParameter("seasonName");
            String startDate = req.getParameter("startDate");
            String endDate = req.getParameter("endDate");
            double coefficient = Double.parseDouble(req.getParameter("coefficient"));
            System.out.println("here1");
            controller.addSeason(hotelID, seasonName, startDate, endDate, coefficient);
            System.out.println("here2");
            controller.notifyUsers(hotelID, seasonName, startDate, endDate, coefficient, true);
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
            int hotelID = Integer.parseInt(req.getParameter("hotelId"));
            String seasonName = req.getParameter("seasonName");
            System.out.println("osynda");
            controller.deleteSeasons(seasonName, hotelID);
            controller.notifyUsers(hotelID, seasonName, "", "", 0, false);
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
