package noteblock;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@XmlRootElement
public class Topic {
    private String title;

    public Topic() {
    }

    public Topic(String title) {
        this.title = title;
    }

    public static ArrayList<Topic> getAllTopics() {
        ArrayList<Topic> ret = new ArrayList<>();

        Connection conn = DBManager.getDBConnection();
        assert conn != null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("SELECT DISTINCT ntopic FROM notes");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ret.add(new Topic(rs.getString(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
            }
        }

        return ret;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
