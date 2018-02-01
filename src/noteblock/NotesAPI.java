package noteblock;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("notes")
public class NotesAPI {
    @GET
    @Path("get/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Note getNotes(@PathParam("id") String id) {
        return new Note("pla" + id);
    }

}
