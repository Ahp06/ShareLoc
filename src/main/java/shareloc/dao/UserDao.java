package shareloc.dao;

import shareloc.model.User;

public class UserDao extends AbstractDao<User> {

    public UserDao() {
        super(User.class);
    }

    public User findByEmail(String email) {
        User user = (User) this.getEntityManager()
                .createNativeQuery("SELECT u FROM User u WHERE u.EMAIL = : " + email)
                .getSingleResult();
        return user;
    }


    public boolean log(String email, String password) {
        final User user = this.findByEmail(email);
        if (user == null) return false;

        return user.getPassword() == password;
    }
}
