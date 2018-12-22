package shareloc.api;

import shareloc.dao.DaoManager;
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
        if (DaoManager.editUser(email, password, firstname, lastname)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

}
