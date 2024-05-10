import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Client {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/project";
        Connection conn = DriverManager.getConnection(url, "checker", "123456");
        PreparedStatement st = conn.prepareStatement("update line_station set position = (select rk from (select id, rank() over (partition by line_id order by id) rk from line_station) rk where id = ?) where id = ?;");
        for (int i = 1; i <= 373; i++) {
            st.setInt(1, i);
            st.setInt(2, i);
            st.executeUpdate();
        }
        st.close();
        conn.close();
//        Login.login();
    }
}