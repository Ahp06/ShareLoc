package shareloc.api;

import shareloc.dao.UserDao;
import shareloc.model.User;

import javax.ws.rs.Path;

@Path("users")
public class UserServices extends ApiService<User> {

    public UserServices() {
        super(User.class);
    }
}
