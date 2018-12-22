package shareloc.manager;

import shareloc.model.Colocation;
import shareloc.model.User;

import java.util.List;

public class UserManager extends DaoManager {

    public UserManager(){
        super();
    }

    public static List<User> getUsers() {
        List<User> lv = userDao.findAll();
        return lv;
    }

    public static boolean createUser(String email, String password, String firstname, String lastname) {
        if (getUser(email) == null) {
            if (!(email.equals("") && password.equals("") && firstname.equals("") && lastname.equals(""))) {
                User user = new User(email, password, firstname, lastname);
                userDao.create(user);
                return true;
            }
        }
        return false;
    }

    public static User login(String email, String password) {
        User u = getUser(email);
        if (u != null && u.password.equals(password))
            return u;
        return null;
    }

    public static boolean editUser(String email, String password, String firstname, String lastname) {
        User user = getUser(email);
        if (user.getPassword().equals(password)) {
            if (user != null && firstname != null && lastname != null && firstname != "" && lastname != "") {
                user.setFirstname(firstname);
                user.setLastname(lastname);
                userDao.edit(user);
                return true;
            }
        }
        return false;
    }

    public static boolean quitColocation(String email, String name) {
        User user = getUser(email);
        Colocation colocation = getColocation(name);

        if (user != null && colocation != null) {
            colocation.getMembers().remove(user);
            colocationDao.edit(colocation);
            return true;
        }
        return false;
    }


}
