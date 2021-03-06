package noteblock;


import javax.xml.bind.annotation.XmlRootElement;
import java.sql.*;
import java.util.ArrayList;

@XmlRootElement
public class Note {
    private int id;

    private String title;

    private String text;

    private String creatorname;

    private String topic;

    public Note() {
    }

    public Note(int id) {
        this.id = id;
    }

    public Note(int id, String title, String text, String topic, String creatorname) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.creatorname = creatorname;
        this.topic = topic;
    }

    public static ArrayList<Note> getUnseenNotes(String user) {
        ArrayList<Note> ret = new ArrayList<>();
        Connection conn = DBManager.getDBConnection();
        assert conn != null;
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement("SELECT * FROM notes WHERE nid IN (SELECT nid FROM unseen WHERE uid=?);");
            stmt.setInt(1, getUidFromName(user));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ret.add(new Note(rs.getInt("nid")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
        return ret;
    }

    private static int getUidFromName(String username){
        int ret = -1;
        Connection conn = DBManager.getDBConnection();
        assert conn != null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT uid FROM users WHERE uname like ?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()){
                ret = rs.getInt(1);
            }
        } catch (SQLException e){ }
        finally {
            DBManager.closeStmt(stmt);
            DBManager.closeRs(rs);
            DBManager.closeConn(conn);
        }
        return ret;
    }


    public static boolean setSeen(String user, int id) {
        boolean ret = false;
        Connection conn = DBManager.getDBConnection();
        assert conn != null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM unseen WHERE nid =? AND uid=?;");
            stmt.setInt(1, id);
            stmt.setInt(2, getUidFromName(user));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
        return ret;
    }

    public static ArrayList<Note> getAllNotes(ArrayList<Filter> filter) {
        ArrayList<Note> ret = new ArrayList<>();

        if (filter == null)
            filter = new ArrayList<>();

        String filter_q = "";
        for (Filter f : filter) {
            filter_q += (f.getKey() + "=? AND ");
        }
        filter_q += "1";
        Connection cnn = DBManager.getDBConnection();
        assert cnn != null;
        try {
            PreparedStatement pstmt = cnn.prepareStatement("SELECT * FROM notes WHERE " + filter_q + ";");
            int c = 1;
            for (Filter fv : filter)
                pstmt.setString(c++, fv.getValue());
            ResultSet rs = pstmt.executeQuery();
            Note n;
            while (rs.next()) {
                n = new Note(rs.getInt("nid"), rs.getString("ntitle"), rs.getString("ntext"), rs.getString("ntopic"), rs.getString("creator"));
                ret.add(n);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static boolean delete(int id) {
        boolean ret = false;
        Connection conn = DBManager.getDBConnection();
        assert conn != null;
        PreparedStatement stmt = null;
        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement("DELETE FROM notes WHERE nid = ?");
            stmt.setInt(1, id);
            ret = DBManager.expectOne(stmt.executeUpdate());
            if (ret) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
        return ret;
    }

    public int save() {
        int ret = 0;
        Connection conn = DBManager.getDBConnection();
        assert conn != null;
        PreparedStatement stmt = null;
        try {
            if (this.id == 0) {
                //INSERT
                stmt = conn.prepareStatement("INSERT INTO notes(ntitle, ntext, ntopic, creator) VALUES(?,?,?,?);", new String[]{"nid"});
                stmt.setString(1, this.title);
                stmt.setString(2, this.text);
                stmt.setString(3, this.topic);
                stmt.setString(4, this.creatorname);
                ret = stmt.executeUpdate();
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        ret = (int) generatedKeys.getLong(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
                PreparedStatement add_unseen_stmt = conn.prepareStatement("INSERT INTO unseen(nid, uid) VALUES(?,?);");
                add_unseen_stmt.setInt(1, ret);
                stmt = conn.prepareStatement("SELECT uid FROM users WHERE uname<>?;");
                stmt.setString(1, creatorname);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    add_unseen_stmt.setInt(2, rs.getInt(1));
                    add_unseen_stmt.executeUpdate();
                }


            } else {
                //UPDATE
                stmt = conn.prepareStatement("UPDATE notes SET ntitle=?, ntext=?, ntopic=? WHERE nid=?;");
                stmt.setString(1, this.title);
                stmt.setString(2, this.text);
                stmt.setString(3, this.topic);
                stmt.setInt(4, this.id);
                ret = stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
        return ret;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Topic: " + this.topic + "\nTitle: " + this.title + "\nCreator: " + this.creatorname + "\n\n" + this.text;
    }
}
