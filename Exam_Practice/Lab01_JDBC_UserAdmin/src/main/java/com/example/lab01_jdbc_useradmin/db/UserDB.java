package com.example.lab01_jdbc_useradmin.db;

import com.example.lab01_jdbc_useradmin.model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDB {
    private static String url = "jdbc:sqlserver://DESKTOP-0I7PKOE:1433;databaseName=Lab01;user=User1;password=User1;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false";
    private static String username = "User1";
    private static String password = "User1";

    public static ArrayList<User> select(String role_param) {
        ArrayList<User> users = new ArrayList<User>();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(url, username, password)){
                String sql = "SELECT * FROM users WHERE role=?";

                try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                    preparedStatement.setString(1, role_param);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        int id = resultSet.getInt("id");
                        String password = resultSet.getString("password");
                        User.Role role = User.Role.valueOf(resultSet.getString("role"));

                        User user = new User(id, password, role);
                        users.add(user);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }

        return users;
    }
}