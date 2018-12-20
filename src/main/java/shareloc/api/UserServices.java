package shareloc.api;

import shareloc.model.User;

import javax.ws.rs.Path;

@Path("user")
public class UserServices extends AbstractServices<User> {

    public UserServices() {
        super(User.class);
    }


}
