package by.belstu.lab07_loginsum.BD;

import by.belstu.lab07_loginsum.Model.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserDB {
    private String url;
    private String user;
    private String password;
    private Connection con;
    private Statement statement;
    public UserDB() {
        getConnection();
    }

    private void getProperties() {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        url = resourceBundle.getString("db.url");
        user = resourceBundle.getString("db.username");
        password = resourceBundle.getString("db.password");
        String driver = resourceBundle.getString("db.driver");
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException("Driver class is missing in classpath", e);
        }
    }
    private Boolean getConnection() {
        try {
            getProperties();
            con = DriverManager.getConnection(url, user, password);
            statement = con.createStatement();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<User> select() {
        ArrayList<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM usersWithSum";

        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                if (!resultSet.next()) break;
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int sum = resultSet.getInt("sum");

                User user = new User(login, password, sum);
                users.add(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return users;
    }


    public int getSum(String username_1) {
        int sum = 0;
        String sql = "SELECT sum FROM usersWithSum WHERE login = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, username_1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt("sum");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }

    public void updateSelectedUser(int newSum, String username_1) {
        String sql = "UPDATE usersWithSum SET sum = ? WHERE login = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, newSum);
            preparedStatement.setString(2, username_1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
