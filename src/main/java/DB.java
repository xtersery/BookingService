import java.sql.*;
import java.time.LocalDate;

public class DB {
    private static int id = 0;
    static final String DB_URL = System.getenv("DATABASE_URL");
    static final String USER = System.getenv("DATABASE_USER");
    static final String PASS = System.getenv("DATABASE_PASSWORD");

    public static Connection connection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static boolean checkExistance(Connection con, String name) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("SELECT count(*) " +
                "FROM information_schema.tables " +
                "WHERE table_name = ?" +
                "LIMIT 1;");
        preparedStatement.setString(1, name);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) != 0;
    }

    public static void createDBTable() throws SQLException {
        Connection dbConn = null;
        Statement smt = null;

        String createTableSQL = "CREATE TABLE FLIGHTS("
                + "USER_EMAIL VARCHAR(40) NOT NULL, "
                + "USERNAME VARCHAR(40) NOT NULL, "
                + "DEPARTURE VARCHAR(40) NOT NULL, "
                + "DESTINATION VARCHAR(40) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL, "
                + "PRIMARY KEY (USER_EMAIL) " + ");";

        String deleteTableSQL = "DROP TABLE FLIGHTS ";

        try {
            dbConn = connection();
            smt = dbConn.createStatement();
            //smt.execute(deleteTableSQL);
            smt.execute(createTableSQL);
            System.out.println("Table \"FLIGHTS\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (smt != null) {
                smt.close();
            }
            if (dbConn != null) {
                dbConn.close();
            }
        }
    }

    public static void insert(String departure, String destination) throws SQLException {
        Connection dbConn = null;
        Statement smt = null;

        id++;
        String updateTableSQL = "INSERT INTO FLIGHTS (USER_ID, USERNAME, DEPARTURE, DESTINATION, CREATED_DATE) "
                + String.format("VALUES (%d, 'firstUser', '%s', '%s', '%s');",
                  id,departure,destination, getCurrentDate());
        try {
            dbConn = connection();
            smt = dbConn.createStatement();
            System.out.println(getCurrentDate());
            smt.execute(updateTableSQL);
            System.out.println("Table \"FLIGHTS\" is updated!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (smt != null) {
                smt.close();
            }
            if (dbConn != null) {
                dbConn.close();
            }
        }
    }

    private static Date getCurrentDate() {
        return Date.valueOf(LocalDate.now());
    }
}
