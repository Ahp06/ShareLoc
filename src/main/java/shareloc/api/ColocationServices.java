package shareloc.api;

import shareloc.dao.DaoManager;
import shareloc.model.Colocation;
import shareloc.model.User;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("colocation")
public class ColocationServices extends AbstractServices<Colocation> {

    public ColocationServices() {
        super(Colocation.class);
    }

    @POST
    @Path("newColocation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addColocation(@QueryParam("name") String name, @QueryParam("email") String email){
        if(DaoManager.createColocation(name,email)){
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("invite")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendInvitation(@QueryParam("name") String name, @QueryParam("email") String email){
        if(DaoManager.inviteUserIntoColocation(name,email)){
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

}
