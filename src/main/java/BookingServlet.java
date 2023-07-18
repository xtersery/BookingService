import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/book")
public class BookingServlet extends HttpServlet {
    @Override
    public void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, POST, DELETE, OPTIONS");
        res.addHeader("Access-Control-Allow-Headers",
                "Origin, X-Api-Key, X-Requested-With, Content-Type, Accept, Authorization, Detail");
        res.addHeader("Access-Control-Allow-Credentials", "true");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession();

        String data = session.getAttribute("jsonData").toString();
        JsonObject json = new Gson().fromJson(data, JsonObject.class);

        Integer userId = (Integer) session.getAttribute("user_id");
        String departure = json.get("departure").getAsString().split("[(]")[1].split("[)]")[0];
        String destination = json.get("destination").getAsString().split("[(]")[1].split("[)]")[0];
        String startDate = json.get("startDate").getAsString();
        String endDate = json.get("endDate").getAsString();

        try {
            Flight booking = new Flight(userId, departure, destination, startDate, endDate);
            if (!DB.checkExistance("FLIGHTS")) {
                booking.create();
            }
            booking.sendToDB();
            FlightOffer offer = new FlightOffer(departure, destination);
            String result = offer.search();
            System.out.println("Result from db: " + result);
            PrintWriter pr = resp.getWriter();
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json;charset=UTF-8");
            pr.print(result);
            pr.flush();
        } catch (Exception e) {
            System.out.println("Error on the booking stage: " + e.getMessage());
        }
    }
}
