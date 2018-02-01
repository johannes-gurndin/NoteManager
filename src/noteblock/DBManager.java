package noteblock;


import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class DBManager {
    private static MysqlConnectionPoolDataSource dbObject = null;

    private static void establish_connection() {
        dbObject = new MysqlConnectionPoolDataSource();
        dbObject.setUser("root");
        dbObject.setPassword("");
        dbObject.setServerName("localhost");
        dbObject.setDatabaseName("notedb");
    }

    public static MysqlConnectionPoolDataSource getDbObject() {
        if (dbObject == null)
            establish_connection();
        return dbObject;
    }

}