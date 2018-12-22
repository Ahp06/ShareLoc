package shareloc.dao;

import shareloc.model.User;

public class UserDao extends AbstractDao<User> {

    public UserDao() {
        super(User.class);
    }
}
