package noteblock;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    private static MysqlConnectionPoolDataSource dbObject = null;

    private static void establish_connection() {
        dbObject = new MysqlConnectionPoolDataSource();
        dbObject.setUser("nmrest");
        dbObject.setPassword("123456");
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
    public static void closeStmt(Statement stmt){
        try {
            stmt.close();
        }catch (SQLException e){
        }
    }
    public static void closeRs(ResultSet rs){
        try {
            rs.close();
        }catch (SQLException e){
        }
    }
    public static void closeConn(Connection conn){
        try {
            conn.close();
        }catch (SQLException e){
        }
    }
    public static boolean expectOne(int i) {
        return i == 1;
    }
}