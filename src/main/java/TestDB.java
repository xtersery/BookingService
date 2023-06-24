import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/";
    static final String USER = "postgres";
    static final String PASS = "xtersery";

    private static Connection getDBConnection() {
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

    private static void createDBTable() throws SQLException {
        Connection dbConn = null;
        Statement smt = null;

        String createTableSQL = "CREATE TABLE TEST("
                + "USER_ID INT NOT NULL, "
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "CREATED_BY VARCHAR(20) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL, "
                + "PRIMARY KEY (USER_ID) " + ");";

        try {
            dbConn = getDBConnection();
            smt = dbConn.createStatement();

            smt.execute(createTableSQL);
            System.out.println("Table \"TEST\" is created!");
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

    public static void main(String[] args) {
        try {
            createDBTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
