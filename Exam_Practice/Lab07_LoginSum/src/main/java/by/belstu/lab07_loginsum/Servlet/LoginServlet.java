package by.belstu.lab07_loginsum.Servlet;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.log4j.Logger;
import by.belstu.lab07_loginsum.BD.UserDB;
import by.belstu.lab07_loginsum.Model.User;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    Logger logger = Logger.getLogger(LoginServlet.class.getName());
    private static final UserDB db = new UserDB();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (verify(username, password)) {
            response.sendRedirect("main.jsp");
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("sum", db.getSum(username));
        } else {
            response.sendRedirect("Wrong.jsp");
        }
            logger.info("User " + username + " logged in");
        logger.info("Time of session creation "+ request.getSession().getCreationTime());
        logger.info("session id "+ request.getSession().getId());
    }

    private boolean verify(String username, String password) {
        ArrayList<User> users = db.select();
        User user = users
                .stream()
                .filter(u -> u.getLogin().equals(username) && u.getPassword().equals(password))
                .findFirst().orElse(null);

        boolean userExist = false;
        if (user != null) {
            userExist = true;
        }
        return userExist;
    }

    public void destroy() {
    }
}