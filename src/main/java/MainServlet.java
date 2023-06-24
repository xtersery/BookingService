import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpSession;


@WebServlet("/hello")
public class MainServlet extends HttpServlet {

    public static JsonArray convertToJSON(Stream<String> stream) {
        Gson gson = new Gson();
        JsonArray jArray = new JsonArray();
        stream.forEach(jArray::add);
        return jArray;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        Integer visitCounter = (Integer) session.getAttribute("visitCounter");
//        if (visitCounter == null) {
//            visitCounter = 1;
//        } else {
//            visitCounter++;
//        }
//        session.setAttribute("visitCounter", visitCounter);
//        String username = req.getParameter("username");
//        resp.setContentType("text/html");
//        PrintWriter writer = resp.getWriter();
//        if (username == null) {
//            writer.write("Hello, Anonymous " + "<br>");
//        } else {
//            writer.write("Hello, " + username + "<br>");
//        }
//        writer.write("Page was visited " + visitCounter + " times");
//        writer.close();
        Stream<String> list = Stream.generate(System::currentTimeMillis)
                .map(String::valueOf)
                .limit(10);
        JsonArray arr = convertToJSON(list);
        HttpSession session = req.getSession();
        session.setAttribute("numbers", arr);
        String username = req.getParameter("username");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        if (username == null) {
            writer.write("Hello, Anonymous " + "<br>");
        } else {
            writer.write("Hello, " + username + "<br>");
        }
        writer.write("The numbers array: " + session.getAttribute("numbers"));
        writer.close();
    }
}