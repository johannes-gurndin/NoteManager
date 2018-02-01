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
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Topic> getTopics(@PathParam("token") String token) {
        return Topic.getAllTopics();
    }
}
