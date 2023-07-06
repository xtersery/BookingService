import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.json.JsonString;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;



@WebServlet("/hello")
public class MainServlet extends HttpServlet {

    private User user;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Successful launch");
    }

    @Override
    public void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, POST, DELETE, OPTIONS");
        res.addHeader("Access-Control-Allow-Headers",
                "Origin, X-Api-Key, X-Requested-With, Content-Type, Accept, Authorization, Detail");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");

        PrintWriter pr = resp.getWriter();
        req.setCharacterEncoding("utf-8");
        String requestData = req.getReader().lines().collect(Collectors.joining());
        JsonObject convertedObject = new Gson().fromJson(requestData, JsonObject.class);
        System.out.println(convertedObject);
        pr.write(requestData);


        String param = req.getHeader("Detail");
        switch (param) {
            case "user":
                String email = convertedObject.get("email").getAsString();
                String username = convertedObject.get("username").getAsString();
                user = new User(email, username);
                break;
            case "requisites":
                if (user == null) {
                    pr.write("Error: the user is not authorized");
                    return;
                }
                String departure = convertedObject.get("departure").getAsJsonObject().get("value").getAsString();
                String destination = convertedObject.get("destination").getAsJsonObject().get("value").getAsString();

                user.addRequisites(departure, destination);

                try {
                    user.sendToDB();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}