package shareloc.api;

import shareloc.dao.DaoManager;
import shareloc.model.User;
import shareloc.security.JWTokenUtility;
import shareloc.security.SigninNeeded;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/")
public class LogServices {

    public LogServices() {
    }

    @GET
    @SigninNeeded
    @Path("whoami")
    @Produces(MediaType.APPLICATION_JSON)
    public Response whoami(@Context SecurityContext security) {
        User user = DaoManager.getUser(security.getUserPrincipal().getName());
        if (user!=null)
            return Response.ok().entity(user).build();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signup(@QueryParam("email") String email, @QueryParam("password") String password,
                           @QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname) {
        if (DaoManager.createUser(email, password, firstname, lastname))
            return Response.status(Response.Status.CREATED).build();
        return Response.status(Response.Status.CONFLICT).build();

    }

    @POST
    @Path("signin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signin(@QueryParam("email") String email, @QueryParam("password") String password){
        User user = DaoManager.login(email,password);
        if(user != null) {
            return Response.ok().entity(JWTokenUtility.buildJWT(user.getEmail())).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    /**
     * Méthode permettant de récupérer l'ensemble des roles d'un utilisateur
     *
     * @param user l'utilisateur
     * @return une liste de tous les roles associés à l'utilisateur user
     */
    public static List<String> findUserRoles(String user) {
        return null;
    }

}
