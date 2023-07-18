import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.json.JsonString;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;



@WebServlet(name="MainServlet", urlPatterns = {"/"})
public class MainServlet extends HttpServlet {

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
        res.addHeader("Access-Control-Allow-Credentials", "true");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        resp.addHeader("Access-Control-Allow-Credentials", "true");

        PrintWriter pr = resp.getWriter();
        req.setCharacterEncoding("utf-8");
        String requestData = req.getReader().lines().collect(Collectors.joining());
        HttpSession session = req.getSession(false);
        if (session == null) {
            session = req.getSession(true);
        }
        session.setAttribute("jsonData", requestData);

        String param = req.getHeader("Detail");

        switch (param) {
            case "user":
                RequestDispatcher rd = req.getRequestDispatcher("/signup");
                rd.forward(req, resp);
                break;
            case "requisites":
                RequestDispatcher rd2 = req.getRequestDispatcher("/book");
                rd2.forward(req, resp);
                break;
            case "login":
                RequestDispatcher rd3 = req.getRequestDispatcher("/login");
                rd3.forward(req, resp);
                break;
        }
    }
}