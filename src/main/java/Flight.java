import java.sql.*;
import java.time.LocalDate;

public class Flight {
    private Integer userId;
    private Connection con;
    private String query;
    private String departure;
    private String startDate;
    private String endDate;
    private String destination;

    Flight(int userId, String departure, String destination, String startDate, String endDate) {
        this.userId = userId;
        this.departure = departure;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void create() throws SQLException {

        query = "CREATE TABLE FLIGHTS("
                + "ID SERIAL PRIMARY KEY, "
                + "USER_ID INT NOT NULL REFERENCES USER_ACCOUNT (ID), "
                + "DEPARTURE VARCHAR(40) NOT NULL, "
                + "DESTINATION VARCHAR(40) NOT NULL, "
                + "START_DATE VARCHAR(40) NOT NULL, "
                + "END_DATE VARCHAR(40) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL"
                + ");";

        DB.send(query);
    }

    public void sendToDB() throws SQLException {

        query = "INSERT INTO FLIGHTS (USER_ID, DEPARTURE, DESTINATION, START_DATE, END_DATE, CREATED_DATE) " +
                String.format("VALUES ('%d', '%s', '%s', '%s', '%s', '%s');",
                        userId, departure, destination, startDate, endDate, getCurrentDate());

        DB.send(query);
    }



    private static Date getCurrentDate() {
        return Date.valueOf(LocalDate.now());
    }
}
