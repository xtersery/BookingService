import java.sql.*;

public class User {
    private static String query;
    private String email;
    private String username;
    private String password;

    User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public void create() throws SQLException {

        query = "CREATE TABLE USER_ACCOUNT ("
                + "ID SERIAL PRIMARY KEY, "
                + "USER_EMAIL VARCHAR(40) NOT NULL, "
                + "USERNAME VARCHAR(40) NOT NULL, "
                + "PASSWORD VARCHAR(40) NOT NULL"
                + ");";

        DB.send(query);
    }

    public void sendToDB() throws SQLException {
        query = "INSERT INTO USER_ACCOUNT (USER_EMAIL, USERNAME, PASSWORD) " +
                String.format("VALUES ('%s', '%s', '%s');",
                        email, username, password);

        DB.send(query);
    }

    public static Integer search(String email, String password) {
        Connection con = null;

        query = "SELECT * FROM USER_ACCOUNT WHERE user_email=? and password=?";

        try {
            con = DB.connection();
            PreparedStatement smt = con.prepareStatement(query);

            smt.setString(1, email);
            smt.setString(2, password);
            ResultSet rs = smt.executeQuery();
            if (rs.next()) {
                int user_id = rs.getInt("id");
                return user_id;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;

    }

    //TODO: create a verifying algorithm for user login
    public boolean checkUser(String email, String password) {
        query = "SELECT * FROM USER_ACCOUNT WHERE user_email=? and password=? " +
                "EXCEPTION " +
                "WHEN NO_DATA_FOUND THEN " +
                "" +
                "END;";
        return true;
    }
}
