package by.belstu.Lab6;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DAO implements IConnection, IQuery {
    static {
        new DOMConfigurator().doConfigure("log/log4j.xml", LogManager.getLoggerRepository());
    }
    private String url;
    private String username;
    private String password;
    private Connection connection;
    private Statement statement;
    private static final Logger LOG = LogManager.getLogger(Main.class);
    public void LogInfo(){
        LOG.info("Started Main");
    }
    public DAO(){
        LogInfo();
        getProperties();
    }
    @Override
    public ArrayList<String> getProperties() {
        ResourceBundle resource = ResourceBundle.getBundle("db");
        url = resource.getString("db.driver");
        username = resource.getString("db.username");
        password = resource.getString("db.password");

        ArrayList<String> ret = new ArrayList<String>();
        ret.add(url);
        ret.add(username);
        ret.add(password);
        return ret;
    }

    @Override
    public Boolean getConnection() {
        try {
            LOG.debug("Connection...");
            getProperties();
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            LOG.info("Connected");
            return true;
        }
        catch(Exception ex){
            LOG.error("*Connection Setup Error*");
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
            LOG.info("Connection closed");
        }
        catch(Exception ex){
            ex.printStackTrace();
            LOG.error("Ошибка при закрытии соединения");
        }
    }

    @Override
    public ResultSet ExecuteQuery(String sqlQuery) {
        try {
            return statement.executeQuery(sqlQuery);
        }
        catch(SQLException ex){
            ex.printStackTrace();
            LOG.error("*Error while executing request*");
            return null;
        }
    }

    public ResultSet ExecutePrepareStatement(int numOfSatellites) {
        try {
            String sqlQuery = """
                    SELECT PLANETS.Name, PLANETS.Radius, COUNT(PLANETS.Name) as Satellites_Number
                    FROM PLANETS INNER JOIN SATELLITES\s
                    ON PLANETS.Name = SATELLITES.Planet_Name
                    GROUP BY PLANETS.Name, PLANETS.Radius HAVING COUNT(*) > ?;""";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, numOfSatellites);
            return preparedStatement.executeQuery();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
            LOG.error("*Error while executing request*");
            return null;
        }
    }

    public void ExecuteTransaction() {
        try {
            try {
                connection.setAutoCommit(false);
                {
                    statement.executeUpdate("UPDATE Planets SET Have_Life = 1 WHERE Name = 'Земля'");
                    statement.executeUpdate("UPDATE Planets SET Have_Life = 0 WHERE Name = 'Марс'");
                }
                ResultSet rs = statement.executeQuery("SELECT Name, Have_Life FROM Planets");
                System.out.println("До обновления");
                while (rs.next()) {
                    System.out.print(rs.getString(1) + "\t");
                    System.out.print(rs.getString(2));
                    System.out.println();
                }
                System.out.println("После обновления");
                statement.executeUpdate("UPDATE Planets SET Have_Life = 1 WHERE Name = 'Марс'");
                statement.executeUpdate("UPDATE Planets SET Have_Life = 0 WHERE Name = 'Земля'");
                connection.commit();
                rs = statement.executeQuery("SELECT Name, Have_Life FROM Planets");
                while (rs.next()) {
                    System.out.print(rs.getString(1) + "\t");
                    System.out.print(rs.getString(2));
                    System.out.println();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                LOG.error("*Transaction Error");
                connection.rollback();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOG.error("*Transaction Error*");
        }
    }
}