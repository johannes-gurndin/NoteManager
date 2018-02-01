package noteblock;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

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
     * @return the generated Token or "false
     */
    public String login() {
        return null;
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
