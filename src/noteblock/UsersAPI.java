package noteblock;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("user")
public class UsersAPI {

    @POST
    @Path("login/{username}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@PathParam("username") String username, String password) {
        return User.login(username, password);
    }

    @GET
    @Path("logout/{token}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String logout(@PathParam("token") String token) {
        return User.logout(token);
    }
}
