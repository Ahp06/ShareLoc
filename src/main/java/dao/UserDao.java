package dao;

import model.User;

public class UserDao extends AbstractDao<User>{

    public UserDao() {
        super(User.class);
    }
}
