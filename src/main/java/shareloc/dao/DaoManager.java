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

    public static User login(String email, String password){
        User u = userDao.findByTable(TABLE_EMAIL, email);
        if(u!=null && u.password.equals(password))
            return u;
        return null;
    }

    public static User getUser(String email){
        if (email == null) return null;
        User u = userDao.findByTable(TABLE_EMAIL, email);
        return u;
    }

    public static boolean createColocation(String name, String admin_email) {
        User admin = DaoManager.getUser(admin_email);
        if (colocationDao.findByTable(TABLE_NAME, name) == null) {
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

    public static boolean inviteUserIntoColocation(String name, String email){
        Colocation colocation = DaoManager.getColocation(name);
        User user = DaoManager.getUser(email);
        if(user == null || colocation == null){
            return false;
        }
        //Check if user is already in
        int cpt = 0;
        for(User u : colocation.getMembers()){
            if(u.getEmail().equals(email)){
                cpt ++;
            }
        }

        if (cpt == 0){
            colocation.addMember(user);
            colocationDao.edit(colocation);
            return true;
        }

        return false;
    }

    public static boolean deleteColocation(String name, String email){
        return false;
    }

}
