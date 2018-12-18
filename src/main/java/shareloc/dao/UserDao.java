package shareloc.dao;

import shareloc.model.User;

public class UserDao extends AbstractDao<User> {

    public UserDao() {
        super(User.class);
    }

    public boolean createUser(String email, String password, String firstname, String lastname) {
        if (this.find(email) == null) {
            if (!(email.equals("") && password.equals("") && firstname.equals("") && lastname.equals(""))) {
                User user = new User(email, password, firstname, lastname);
                this.create(user);
                return true;
            }
        }
        return false;
    }

    public boolean login(String email, String password){
        User u = this.find(email);
        return (u!=null && u.getPassword().equals(password));
    }

    public User getUser(String email){
        if (email == null)
            return null;
        return this.find(email);
    }
}
