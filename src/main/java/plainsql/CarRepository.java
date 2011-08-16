package plainsql;

import java.sql.*;

public class CarRepository {

    Connection connection;

    public CarRepository() throws Exception {
        Class.forName("org.hsqldb.jdbcDriver");
        connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost", "sa", "");
    }

    public synchronized int findAll(String expression) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(expression);

        int numberOfRecords = iterate(resultSet);
        statement.close();
        return numberOfRecords;
    }

    public synchronized void update(String expression) throws SQLException {
        Statement statement = connection.createStatement();

        if (statement.executeUpdate(expression) == -1) {
            System.out.println("db error : " + expression);
        }

        statement.close();
    }

    private static int iterate(ResultSet resultSet) throws SQLException {
        ResultSetMetaData meta   = resultSet.getMetaData();
        int numberOfRows = 0;
        int numberOfColumns = meta.getColumnCount();
        for (; resultSet.next(); ) {
            for (int i = 1; i < numberOfColumns; ++i) {
                Object object = resultSet.getObject(i + 1);

                System.out.print(object.toString() + " ");
            }
            numberOfRows++;
            System.out.println(" ");
        }
        return numberOfRows;
    }

}
