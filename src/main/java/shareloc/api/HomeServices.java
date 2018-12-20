package shareloc.api;

import shareloc.dao.DaoManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/home")
public class HomeServices {

    public HomeServices(){

    }

    /*@GET
    @Path("mycolocations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response myColocations(){

        return null;
    }*/

    @POST
    @Path("newColocation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addColocation(@QueryParam("name") String name){
        if(DaoManager.createColocation(name)){
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }
}
