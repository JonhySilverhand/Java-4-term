import by.belstu.Lab6.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DAO db = new DAO();
            db.getConnection();
            /* Вывести информацию обо всех планетах, на которых присутствует жизнь, и их спутниках */
            System.out.println("Query #1");

            ResultSet resultSet = db.ExecuteQuery("""
                    SELECT * FROM PLANETS\s
                    INNER JOIN SATELLITES\s
                    ON PLANETS.Name = SATELLITES.Planet_Name
                    WHERE PLANETS.Have_Life = 1""");

            while(resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t|\t");
                System.out.println(resultSet.getString(2) + "\t|\t");
                System.out.println(resultSet.getString(3) + "\t|\t");
                System.out.println(resultSet.getString(4) + "\t|\t");
                System.out.println(resultSet.getString(5) + "\t|\t");
                System.out.println(resultSet.getString(6) + "\t|\t");
                System.out.println(resultSet.getString(7) + "\t|\t");
                System.out.println(resultSet.getString(8) + "\t|\t");
                System.out.println(resultSet.getString(9));
                System.out.println();
            }
            System.out.println("\nQuery #2");
            /* Вывести информацию о планетах и их спутниках, имеющих наименьший радиус и наибольшее количество спутников */
            resultSet =db.ExecuteQuery("""
                    SELECT PLANETS.Name, PLANETS.Radius, COUNT(PLANETS.Name) as Satellites_Number
                    FROM PLANETS INNER JOIN SATELLITES\s
                    ON PLANETS.Name = SATELLITES.Planet_Name
                    GROUP BY PLANETS.Name, PLANETS.Radius
                    ORDER BY PLANETS.Radius ASC, Satellites_Number DESC""");
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + "\t|\t");
                System.out.print(resultSet.getString(2) + "\t| ");
                System.out.print(resultSet.getString(3));
                System.out.println();
            }

            System.out.println("\nPrepared statement");
            resultSet = db.ExecutePrepareStatement(2);
            while(resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t|\t");
                System.out.println(resultSet.getString(2) + "\t|");
                System.out.println(resultSet.getString(3));
                System.out.println();
            }
            System.out.println("Transaction");
            db.ExecuteTransaction();
            db.closeConnection();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}