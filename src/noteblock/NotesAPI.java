package noteblock;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


@Path("notes")
public class NotesAPI {

    @POST
    @Path("get/{token}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Note> getAllNotes(@PathParam("token") String token, ArrayList<Filter> filter) {
        if (User.check(token).equals("false")) {
            return null;
        }
        return Note.getAllNotes(filter);
    }

    @GET
    @Path("getUnseen/{token}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Note> getAllNotes(@PathParam("token") String token) {
        String user = User.check(token);
        if (user.equals("false")) {
            return null;
        }
        return Note.getUnseenNotes(user);
    }

    @GET
    @Path("setSeen/{token}/{id}")
    @Produces({MediaType.TEXT_PLAIN})
    public boolean setSeen(@PathParam("token") String token, @PathParam("id") int id) {
        String user = User.check(token);
        if (user.equals("false")) {
            return false;
        }
        return Note.setSeen(user, id);
    }

    @GET
    @Path("getAll")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Note> get() {
        return Note.getAllNotes(null);
    }

    @POST
    @Path("insert/{token}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertNote(@PathParam("token") String token, Note note) {
        if (User.check(token).equals("false")) {
            return "false";
        }
        return String.valueOf(note.save());
    }

    @PUT
    @Path("update/{token}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateNote(@PathParam("token") String token, Note note) {
        if (User.check(token).equals("false")) {
            return "false";
        }
        return String.valueOf(note.save());
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
