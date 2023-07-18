import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class FlightOffer {
    private static String query;
    private String departure;
    private String destination;
    private String startTime;
    private String endTime;
    private String company;

    private String flightNumber;
    private String price;

    private class Creator {

        private ArrayList<HashMap<String, String>> data = new ArrayList<>();
        private HashMap<String, String> object = new HashMap<>();
        public void put(String start, String end, String comp, String flightNum, String price) {
            object.put("start", start);
            object.put("end", end);
            object.put("company", comp);
            object.put("flightNumber", flightNum);
            object.put("price", price);
            data.add(object);
            object = new HashMap<>();
        }
    }

    public FlightOffer(String departure, String destination) {
        this.departure = departure;
        this.destination = destination;
    }

    public String search() throws SQLException {
        Connection con = DB.connection();
        query = "SELECT * FROM FLIGHT_OFFERS WHERE DEPARTURE=? AND DESTINATION=?";
        PreparedStatement smt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                            ResultSet.CONCUR_UPDATABLE);

        smt.setString(1, departure);
        smt.setString(2, destination);

        ResultSet rs = smt.executeQuery();
        if (!rs.next()) {
            System.out.println("Error: ResultSet is empty");
        }

        Creator creator = new Creator();
        while (rs.next()) {
            startTime = rs.getString("start_at");
            endTime = rs.getString("end_at");
            company = rs.getString("company");
            flightNumber = rs.getString("flight_number");
            price = rs.getString("price");
            creator.put(startTime, endTime, company, flightNumber, price);
        }

        rs.close();
        return new Gson().toJson(creator);

    }

    private static Date getCurrentDate() {
        return Date.valueOf(LocalDate.now());
    }
}
