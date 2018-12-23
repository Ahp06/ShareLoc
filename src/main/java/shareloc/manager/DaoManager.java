package shareloc.manager;

import shareloc.dao.ColocationDao;
import shareloc.dao.ServiceDao;
import shareloc.dao.UserDao;
import shareloc.model.Colocation;
import shareloc.model.Service;
import shareloc.model.User;

public class DaoManager {

    //Dao
    protected static final UserDao userDao = new UserDao();
    protected static final ColocationDao colocationDao = new ColocationDao();
    protected static final ServiceDao serviceDao = new ServiceDao();

    //Table names
    protected static final String TABLE_EMAIL = "email";
    protected static final String TABLE_NAME = "name";

    public DaoManager() {
    }

    /* Common methods between managers */

    public static User getUser(String email) {
        if (email == null) return null;
        User u = userDao.findByTable(TABLE_EMAIL, email);
        return u;
    }

    public static Colocation getColocation(String name) {
        if (name == null) return null;
        Colocation colocation = colocationDao.findByTable(TABLE_NAME, name);
        return colocation;
    }

    public static boolean isAdmin(User user, Colocation colocation) {
        return user.getEmail().equals(colocation.getAdmin().getEmail());
    }

    public static boolean userIsIntoColoc(User user, Colocation colocation) {
        int cpt = 0;
        for (User u : colocation.getMembers()) {
            if (u.getEmail().equals(user.getEmail())) {
                cpt++;
            }
        }
        return cpt == 1;
    }

}
