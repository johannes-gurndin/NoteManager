package noteblock;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private static MysqlConnectionPoolDataSource dbObject = null;

    private static void establish_connection() {
        dbObject = new MysqlConnectionPoolDataSource();
        dbObject.setUser("root");
        dbObject.setPassword("");
        dbObject.setServerName("localhost");
        dbObject.setDatabaseName("notedb");
    }

    public static Connection getDBConnection() {
        if (dbObject == null)
            establish_connection();
        try {
            return dbObject.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}