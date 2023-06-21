package by.belstu.lab10_checkboxes;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ResourceBundle;

public class DAO {
    private String url;
    private String user;
    private String password;
    private Connection con;
    private Statement statement;

    public DAO() {
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

    public ResultSet ExecuteQuery(String sqlQuery) {
        try {
            return statement.executeQuery(sqlQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public void closeConnection() {
        try {
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
