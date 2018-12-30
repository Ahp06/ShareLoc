package shareloc.api;

import shareloc.manager.MessageManager;
import shareloc.model.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("message")
public class MessageServices extends AbstractServices<Message> {

    public MessageServices() {
        super(Message.class);
    }

    @GET
    @Path("coloc")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessagesIntoColocation(@QueryParam("email") String email,
                                              @QueryParam("name") String name){
        List<Message> messages = MessageManager.getMessages(email,name);
        if(messages != null){
            return Response.ok(messages.toString()).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }


    @POST
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMessage(@QueryParam("email") String email,
                                @QueryParam("name") String name,
                                @QueryParam("message") String message,
                                @QueryParam("picture") String picture) {
        if (MessageManager.sendMessage(email,name,message,picture)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }
}
