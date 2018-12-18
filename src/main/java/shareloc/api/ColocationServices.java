package shareloc.api;

import shareloc.model.Colocation;

import javax.ws.rs.Path;

@Path("colocation")
public class ColocationServices extends ApiService<Colocation> {

    public ColocationServices() {
        super(Colocation.class);
    }


}
