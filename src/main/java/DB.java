import java.sql.*;
import java.time.LocalDate;

public class DB {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/";
    static final String USER = "postgres";
    static final String PASS = "xtersery";

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

    public static boolean checkExistance(String name) throws SQLException {
        ResultSet set = connection().getMetaData().getCatalogs();

        while (set.next()) {
            String databaseName = set.getString(1);
            if (name.equals(databaseName)) {
                return true;
            }
        }
        return false;
    }


    public static void send(String query) throws SQLException {
        Statement smt = null;
        Connection con = connection();
        try {
            smt = con.createStatement();
            smt.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (smt != null) {
                smt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    private static Date getCurrentDate() {
        return Date.valueOf(LocalDate.now());
    }
}
