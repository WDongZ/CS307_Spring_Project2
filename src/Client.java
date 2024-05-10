import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Client {
    public static void main(String[] args) throws Exception {
        Login.login();
    }
}