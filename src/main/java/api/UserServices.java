package api;

import model.User;

import javax.ws.rs.Path;

@Path("/users")
public class UserServices extends BaseServices<User>{

    protected UserServices() {
        super(User.class);
    }
}
