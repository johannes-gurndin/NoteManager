package noteblock;


import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.XmlRootElement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

@XmlRootElement
public class Note {
    private int id;
    private String title;
    private String text;
    private String creatorname;
    private String topic;

    public Note() {
    }

    public Note(int id, String title, String text, String creatorname, String topic) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creatorname = creatorname;
        this.topic = topic;
    }

    public static ArrayList<Note> getAllNotes(ArrayList<Filter> filter) {

        String filter_q = "";
        for(Filter f:filter) {
            filter_q += (f.getKey()+"=? AND ");
        }
        filter_q += "1";
        Connection cnn = DBManager.getDBConnection();
        assert cnn != null;
        try {
            PreparedStatement pstmt = cnn.prepareStatement("SELECT * FROM notes WHERE "+filter_q+";");
            int c = 1;
            for(Filter fv:filter)
                pstmt.setString(c++, fv.getValue());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                rs.
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int save() {
        return 0;
    }

    public static boolean delete(int id) {
        return false;
    }


    public String getCreatorname() {
        return creatorname;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
