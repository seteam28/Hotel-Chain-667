package Router;

import Controller.AuthController;
import Model.Result;
import Model.ResultStatus;
import Model.User;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/auth")
public class AuthRouter extends HttpServlet {

    private AuthController controller = new AuthController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/auth/register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getQueryString().equals("auth=login")) {
            login(req, resp);
        } else if(req.getQueryString().equals("auth=register")) {
            register(req, resp);
        }
    }


    //Login user
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        Gson gson = new Gson();

        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            User user = controller.login(username, password);
            Result<User> result = new Result<>(ResultStatus.success, user, "Great to see you again " + user.getUsername());

            writer.print(gson.toJson(result));
            writer.flush();
        } catch (Exception e) {
            Result<User> result = new Result<>(ResultStatus.error, e.getMessage());
            writer.print(gson.toJson(result));
            writer.flush();
        }
    }

    //Register new user
    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        Gson gson = new Gson();

        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String repeatedPassword = req.getParameter("repeatedPassword");
            String firstName = req.getParameter("firstName");
            String secondName = req.getParameter("secondName");
            String address = req.getParameter("address");
            String homePhoneNumber = req.getParameter("homePhone");
            String mobilePhoneNumber = req.getParameter("mobilePhone");
            String idType = req.getParameter("idType_opt");
            String idNumber = req.getParameter("idNumber");
            String numberOfAdults = req.getParameter("adults");
            String numberOfChildren = req.getParameter("children");

            controller.register(username, firstName, secondName, password, repeatedPassword, address, homePhoneNumber,
                    mobilePhoneNumber, idType, idNumber, numberOfAdults, numberOfChildren);
            Result<User> result = new Result<>(ResultStatus.success, "Successfully registered");
            writer.print(gson.toJson(result));
            writer.flush();
        } catch (Exception e) {
            Result<User> result = new Result<>(ResultStatus.error, e.getMessage());
            writer.print(gson.toJson(result));
            writer.flush();
        }
    }
}
