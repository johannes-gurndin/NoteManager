package noteblock;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private static MysqlConnectionPoolDataSource dbObject = null;

    private static void establish_connection() {
        dbObject = new MysqlConnectionPoolDataSource();
        dbObject.setUser("user1");
        dbObject.setPassword("plapla11");
        dbObject.setServerName("192.168.56.101");
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

    public static boolean expectOne(int i) {
        return i == 1;
    }
}