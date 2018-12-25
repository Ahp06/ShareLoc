package shareloc.manager;

import shareloc.dao.AchievedServiceDao;
import shareloc.dao.ColocationDao;
import shareloc.dao.ServiceDao;
import shareloc.dao.UserDao;
import shareloc.model.Colocation;
import shareloc.model.User;

public class DaoManager {

    //Dao
    protected static final UserDao userDao = new UserDao();
    protected static final ColocationDao colocationDao = new ColocationDao();
    protected static final ServiceDao serviceDao = new ServiceDao();
    protected static final AchievedServiceDao achievedServiceDao = new AchievedServiceDao();

    //Table names
    protected static final String TABLE_EMAIL = "email";
    protected static final String TABLE_NAME = "name";


    public DaoManager() {
    }

    /* Common methods between managers */

    /**
     * Return an user instance by email
     *
     * @param email
     * @return
     */
    public static User getUser(String email) {
        if (email == null) return null;
        User u = userDao.findByTable(TABLE_EMAIL, email);
        return u;
    }

    /**
     * Return a colocation instance by name
     *
     * @param name
     * @return
     */
    public static Colocation getColocation(String name) {
        if (name == null) return null;
        Colocation colocation = colocationDao.findByTable(TABLE_NAME, name);
        return colocation;
    }

    /**
     * Return True if user is the colocation administrator
     *
     * @param user
     * @param colocation
     * @return
     */
    public static boolean isAdmin(User user, Colocation colocation) {
        return user.getEmail().equals(colocation.getAdmin().getEmail());
    }

    /**
     * Return true if user is into the colocation
     *
     * @param user
     * @param colocation
     * @return
     */
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
