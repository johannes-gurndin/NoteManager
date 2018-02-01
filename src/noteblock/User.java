package noteblock;


import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.XmlRootElement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

@XmlRootElement
public class User {
    private static HashMap<String, String> usertokens = new HashMap<>();
    private String username;
    private String password;
    private String token;


    public User() {

    }

    public User(String username, String password, String token) {
        this.username = username;
        this.password = password;
        this.token = token;
    }

    /**
     * Checks if the User is registered in the Database. If that
     * is the case, a generated Token will be returned and
     * user + token will be saved in the static Hashmap. In any
     * other case "false" will be returned.
     *
     */
    public void login() {
        Connection cnn = DBManager.getDBConnection();
        assert cnn != null;
        try {
            PreparedStatement pstmt = cnn.prepareStatement("SELECT COUNT(*) FROM users WHERE uname=? AND upass=SHA2(?);");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            if (rs.getInt(1) == 1) {
                Random rnd = new Random();
                try {
                    MessageDigest md = MessageDigest.getInstance("md5");
                    String base = rnd.nextInt(1000000) + "" + System.currentTimeMillis();
                    token = DatatypeConverter.printHexBinary(md.digest(base.getBytes())).toUpperCase();
                    usertokens.put(token, username);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } else {
                token = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * If the token is connected to a username the method returnes that specific username, else null is
     * returned.
     *
     * @param token the token connected to the username
     * @return the username of connected to the token or null
     */
    public static String check(String token) {
        return usertokens.keySet().contains(token) ? usertokens.get(token) : null;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
