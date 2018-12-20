package shareloc.api;

import shareloc.dao.DaoManager;
import shareloc.model.Colocation;

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
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addColocation(@QueryParam("name") String name, @QueryParam("admin") String admin_email) {
        if (DaoManager.createColocation(name, admin_email)) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("remove")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeColocation(@QueryParam("name") String name, @QueryParam("admin") String admin_email) {
        if (DaoManager.removeColocation(name, admin_email)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("invite")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendInvitation(@QueryParam("name") String name, @QueryParam("email") String email) {
        if (DaoManager.inviteUserIntoColocation(name, email)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("editName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editColocationName(@QueryParam("name") String name, @QueryParam("admin") String admin_email, @QueryParam("newName") String newName) {
        if (DaoManager.editColocationName(name, admin_email, newName)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    @POST
    @Path("removeMember")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeMember(@QueryParam("name") String name, @QueryParam("admin") String admin_email, @QueryParam("email") String member_email) {
        if (DaoManager.editColocationName(name, admin_email, member_email)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }


}
