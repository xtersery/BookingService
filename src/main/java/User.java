import java.sql.*;
import java.time.LocalDate;

public class User {
    private String email;
    private String username;
    private String departure;
    private String destination;

    User(){}
    User(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public void addRequisites(String departure, String destination) {
        this.departure = departure;
        this.destination = destination;
    }

    public void sendToDB() throws SQLException {
        Connection dbCon = null;
        Statement smt = null;

        String updateTableSQL = "INSERT INTO FLIGHTS (USER_EMAIL, USERNAME, DEPARTURE, DESTINATION, CREATED_DATE) " +
                String.format("VALUES ('%s', '%s', '%s', '%s', '%s');",
                        email, username, departure, destination, getCurrentDate());

        try {
            dbCon = DB.connection();
            smt = dbCon.createStatement();

//            if (!DB.checkExistance(dbCon, "FLIGHTS"))  {
//                DB.createDBTable();
//            }

            System.out.println(getCurrentDate());
            smt.execute(updateTableSQL);
            System.out.println("Table \"FLIGHTS\" is updated!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (smt != null) {
                smt.close();
            }
            if (dbCon != null) {
                dbCon.close();
            }
        }
    }



    private static Date getCurrentDate() {
        return Date.valueOf(LocalDate.now());
    }
}
