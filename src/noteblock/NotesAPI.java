package noteblock;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


@Path("notes")
public class NotesAPI {

    @POST
    @Path("get/{token}")
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Note> getAllNotes(@PathParam("token") String token, ArrayList<Filter> filter) {
        if (User.check(token) == "false") {
            return null;
        }
        return Note.getAllNotes(filter);
    }

    @POST
    @Path("insert/{token}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertNote(@PathParam("token") String token, Note note) {
        if (User.check(token) == "false") {
            return "false";
        }
        return String.valueOf(Note.insert(note));
    }

    @PUT
    @Path("update/{token}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateNote(@PathParam("token") String token, Note note) {
        if (User.check(token) == "false") {
            return "false";
        }
        return String.valueOf(Note.update(note));
    }

    @DELETE
    @Path("del/{token}/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteNote(@PathParam("token") String token, @PathParam("id") String id) {
        if (User.check(token) == "id") {
            return "false";
        }
        return String.valueOf(Note.delete(Integer.parseInt(id)));
    }
}
