package by.belstu.Lab6;

import java.util.ArrayList;

public interface IConnection {
    public ArrayList<String> getProperties();
    public Boolean getConnection();
    public void closeConnection();
}