package noteblock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("topic")
public class TopicAPI {
    @GET
    @Path("getall/{token}")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Topic> getTopics(@PathParam("token") String token) {
        if (User.check(token) == null) {
            return null;
        }
        return Topic.getAllTopics();
    }
}
