package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnectionUtil {

    private final String baseName = "dbresource";
    private final ResourceBundle resource = ResourceBundle.getBundle("/config/" + baseName);

    private static final DBConnectionUtil instance = new DBConnectionUtil();

    private DBConnectionUtil() {
        try {
            Class.forName(resource.getString("mysql.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBConnectionUtil getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        String url = resource.getString("mysql.url");
        String user = resource.getString("mysql.user");
        String password = resource.getString("mysql.password");

        return DriverManager.getConnection(url, user, password);
    }

    public void close(AutoCloseable... autoCloseables) {
        for (AutoCloseable ac : autoCloseables) {
            if (ac != null) {
                try {
                    ac.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
