import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String data = req.getSession().getAttribute("jsonData").toString();
        JsonObject json = new Gson().fromJson(data, JsonObject.class);
        
        String email = json.get("email").getAsString();
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        User user = new User(email, password, username);
        try {
//            if (!DB.checkExistance("USER_ACCOUNT")) {
//                user.create();
//            }
            user.sendToDB();
        } catch (SQLException e) {
            System.out.println("Error on signUp stage: " + e.getMessage());
        }
    }
}
