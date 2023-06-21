package by.belstu.lab06_book;


import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookDB {
    private String url;
    private String user;
    private String password;
    private Connection con;
    private Statement statement;
    public BookDB() {
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

    public ArrayList<Book> select(int page) {
        ArrayList<Book> books = new ArrayList<Book>();
            int counter = 0;
                String sql = "SELECT * FROM books";

                try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        if ( counter == page*2 || counter==page*2+1) {
                            String author = resultSet.getString("author");
                            String book_name = resultSet.getString("book_name");
                            int publication_year = resultSet.getInt("publication_year");
                            int pages = resultSet.getInt("pages");

                            Book book = new Book(author, book_name, publication_year, pages);
                            books.add(book);
                        }
                        counter++;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


        return books;
    }

    public int getDbSize() {
        int dbSize = 0;
        String sql = "SELECT COUNT(*) FROM books";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dbSize = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dbSize;
    }
}
