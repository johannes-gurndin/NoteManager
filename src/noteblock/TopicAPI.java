package noteblock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("topic")
public class TopicAPI {
    @GET
    @Path("getall")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Topic> getTopics() {
        return Topic.getAllTopics();
    }
}
