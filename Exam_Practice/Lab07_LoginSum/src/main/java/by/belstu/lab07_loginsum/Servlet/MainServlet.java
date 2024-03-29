package by.belstu.lab07_loginsum.Servlet;
import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.log4j.Logger;
import by.belstu.lab07_loginsum.BD.UserDB;

@WebServlet(name = "MainServlet", value = "/main")
public class MainServlet  extends  HttpServlet {
    Logger logger = Logger.getLogger(MainServlet.class.getName());
    private static final UserDB db = new UserDB();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String command = req.getParameter("command");
        int sum = (int)req.getSession().getAttribute("sum");
        String username = (String)req.getSession().getAttribute("username");


        if (command != null) {
            switch (command) {
                case "show_balance": {
                    req.setAttribute("balance", sum);
                    break;
                }
                case "PAY": {
                    int newSum = sum - 100;
                    if (newSum >= 0) {

                        req.getSession().setAttribute("sum", newSum);
                        db.updateSelectedUser(newSum, username);
                        logger.info("User " + username + " paid 100. New balance: " + newSum);

                    }
                    break;
                }

            }
        }

        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }
}
