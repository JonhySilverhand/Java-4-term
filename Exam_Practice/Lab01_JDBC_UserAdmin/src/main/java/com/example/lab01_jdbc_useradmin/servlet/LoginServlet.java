package com.example.lab01_jdbc_useradmin.servlet;

import com.example.lab01_jdbc_useradmin.db.UserDB;
import com.example.lab01_jdbc_useradmin.model.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final String INVALID_PASSWORD_REGEX = "Пароль не состоит из 6-12 символов или цифр";
    private static final String INVALID_PASSWORD = "Неверный пароль";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (!password.matches("[\\w&&[^_]]{6,12}")) {
            request.setAttribute("info", INVALID_PASSWORD_REGEX);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else {
            ArrayList<User> users = UserDB.select(role);
            User user = users
                    .stream()
                    .filter(u -> u.getPassword().equals(password))
                    .findFirst().orElse(null);

            if (user != null) {
                request.setAttribute("user", user);

                if (user.getRole() == User.Role.USER) {
                    getServletContext().getRequestDispatcher("/views/user.jsp").forward(request, response);
                }
                else {

                    if (getCookie(request,"time1") == null){
                        request.setAttribute("time","");
                    }
                    else{
                        request.setAttribute("time",getCookie(request,"time1").getValue());
                    }

                    response.addCookie(new Cookie("time1", URLEncoder.encode(new Date().toString(), "UTF-8")));

                    getServletContext().getRequestDispatcher("/views/admin.jsp").forward(request, response);
                }
            }
            else {
                request.setAttribute("info", INVALID_PASSWORD);
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
