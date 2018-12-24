package shareloc.api;

import shareloc.manager.DaoManager;
import shareloc.manager.UserManager;
import shareloc.model.Service;
import shareloc.model.User;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserServices extends AbstractServices<User> {

    public UserServices() {
        super(User.class);
    }

    @POST
    @Path("edit")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editFirstLastNames(@QueryParam("email") String email, @QueryParam("password") String password,
                                       @QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname) {
        if (UserManager.editUser(email, password, firstname, lastname)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("quit")
    @Produces(MediaType.APPLICATION_JSON)
    public Response quitColocation(@QueryParam("email") String email, @QueryParam("name") String coloc_name){
        if(UserManager.quitColocation(email,coloc_name)){
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("vote")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vote(@QueryParam("email") String email, @QueryParam("serviceID") Long serviceID, @QueryParam("vote") int vote){
        if(UserManager.vote(email,serviceID,vote)){
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("validService")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validService(@QueryParam("serviceID") Long serviceID){
        if(UserManager.validService(serviceID)){
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

}
