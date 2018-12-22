package shareloc.dao;

import shareloc.model.Colocation;
import shareloc.model.User;

import java.util.List;

public class DaoManager {

    //Dao
    private static final UserDao userDao = new UserDao();
    private static final ColocationDao colocationDao = new ColocationDao();

    //Table names
    private static final String TABLE_EMAIL = "email";
    private static final String TABLE_NAME = "name";

    public DaoManager() {
    }

    /* User Methods */

    public static List<User> getUsers() {
        List<User> lv = userDao.findAll();
        return lv;
    }

    public static boolean createUser(String email, String password, String firstname, String lastname) {
        if (userDao.findByTable(TABLE_EMAIL, email) == null) {
            if (!(email.equals("") && password.equals("") && firstname.equals("") && lastname.equals(""))) {
                User user = new User(email, password, firstname, lastname);
                userDao.create(user);
                return true;
            }
        }
        return false;
    }

    public static User login(String email, String password) {
        User u = userDao.findByTable(TABLE_EMAIL, email);
        if (u != null && u.password.equals(password))
            return u;
        return null;
    }

    public static User getUser(String email) {
        if (email == null) return null;
        User u = userDao.findByTable(TABLE_EMAIL, email);
        return u;
    }

    public static boolean editUser(String email, String firstname, String lastname) {
        User user = DaoManager.getUser(email);
        if (user != null && firstname != null && lastname != null && firstname != "" && lastname != "") {
            user.setFirstname(firstname);
            user.setLastname(lastname);
            userDao.edit(user);
            return true;
        }
        return false;
    }

    public static boolean quitColocation(String email, String name) {
        User user = DaoManager.getUser(email);
        Colocation colocation = DaoManager.getColocation(name);

        if (user != null && colocation != null) {
            colocation.getMembers().remove(user);
            colocationDao.edit(colocation);
            return true;
        }
        return false;
    }

    public static boolean isAdmin(User user, Colocation colocation) {
        return user.getEmail().equals(colocation.getAdmin().getEmail());
    }

    private static boolean userIsIntoColoc(User user, Colocation colocation) {
        int cpt = 0;
        for (User u : colocation.getMembers()) {
            if (u.getEmail().equals(user.getEmail())) {
                cpt++;
            }
        }
        return cpt == 1;
    }

    /* Colocation Methods */

    public static boolean createColocation(String name, String admin_email) {
        User admin = DaoManager.getUser(admin_email);
        if (admin != null && colocationDao.findByTable(TABLE_NAME, name) == null) {
            if (!(name.equals(""))) {
                Colocation colocation = new Colocation(name, admin);
                colocationDao.create(colocation);
                return true;
            }
        }
        return false;
    }

    public static Colocation getColocation(String name) {
        if (name == null) return null;
        Colocation colocation = colocationDao.findByTable(TABLE_NAME, name);
        return colocation;
    }

    public static boolean inviteUserIntoColocation(String name, String email) {
        Colocation colocation = DaoManager.getColocation(name);
        User user = DaoManager.getUser(email);
        if (user == null || colocation == null) {
            return false;
        }

        if (!userIsIntoColoc(user, colocation)) {
            colocation.addMember(user);
            colocationDao.edit(colocation);
            return true;
        }

        return false;
    }


    public static boolean removeColocation(String name, String email) {
        Colocation colocation = DaoManager.getColocation(name);
        if (colocation != null && email.equals(colocation.getAdmin().getEmail())) {
            colocationDao.remove(colocation);
            return true;
        }
        return false;
    }

    public static boolean editColocationName(String name, String admin_email, String newName) {
        Colocation colocation = DaoManager.getColocation(name);
        User admin = DaoManager.getUser(admin_email);
        if (colocation != null && admin != null && admin.getEmail().equals(colocation.getAdmin().getEmail())) {
            System.out.println("admin_email : " + admin_email);
            System.out.println("colocation admin : " + colocation.getAdmin().getEmail());
            System.out.println("colocation name : " + colocation.getName());
            colocation.setName(newName);
            colocationDao.edit(colocation);
            return true;
        }
        return false;
    }

    public static boolean removeMemberFromColoc(String name, String admin_email, String member_email) {
        User admin = DaoManager.getUser(admin_email);
        User toDelete = DaoManager.getUser(member_email);
        Colocation colocation = DaoManager.getColocation(name);

        if (colocation != null && admin != null && toDelete != null) {
            if (DaoManager.isAdmin(admin, colocation) && DaoManager.userIsIntoColoc(toDelete, colocation)) {
                colocation.getMembers().remove(toDelete);
                colocationDao.edit(colocation);
                return true;
            }
        }
        return false;
    }
}
