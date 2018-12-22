package shareloc.api;

import shareloc.model.Service;

import javax.ws.rs.Path;

@Path("service")
public class ServiceServices extends AbstractServices<Service>{

    public ServiceServices() {
        super(Service.class);
    }
}
