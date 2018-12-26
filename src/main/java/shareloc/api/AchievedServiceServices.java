package shareloc.api;

import shareloc.manager.AchievedServiceManager;
import shareloc.model.AchievedService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("achievedService")
public class AchievedServiceServices extends AbstractServices<AchievedService> {

    public AchievedServiceServices() {
        super(AchievedService.class);
    }

    @POST
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newAchievedService(@QueryParam("email") String email,
                                       @QueryParam("serviceID") Long serviceID,
                                       @QueryParam("date") String date,
                                       @QueryParam("picture") String picture) {
        if (AchievedServiceManager.newAchievedService(email, serviceID, date, picture)) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }


}
