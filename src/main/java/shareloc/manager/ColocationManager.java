package shareloc.manager;

import shareloc.model.Colocation;
import shareloc.model.User;

public class ColocationManager extends DaoManager {

    public static boolean createColocation(String name, String admin_email) {
        User admin = getUser(admin_email);
        if (admin != null && getColocation(name) == null) {
            if (!(name.equals(""))) {
                Colocation colocation = new Colocation(name, admin);
                colocationDao.create(colocation);
                return true;
            }
        }
        return false;
    }

    public static boolean inviteUserIntoColocation(String name, String admin_email, String email) {
        Colocation colocation = getColocation(name);
        User invited = getUser(email);
        User admin = getUser(admin_email);

        if (admin == null || invited == null || colocation == null) {
            return false;
        }

        if (isAdmin(admin, colocation) && !userIsIntoColoc(invited, colocation)) {
            colocation.addMember(invited);
            colocationDao.edit(colocation);
            return true;
        }

        return false;
    }


    public static boolean removeColocation(String name, String email) {
        Colocation colocation = getColocation(name);
        if (colocation != null && email.equals(colocation.getAdmin().getEmail())) {
            colocationDao.remove(colocation);
            return true;
        }
        return false;
    }

    public static boolean editColocationName(String name, String admin_email, String newName) {
        Colocation colocation = getColocation(name);
        User admin = getUser(admin_email);
        if (colocation != null && admin != null && admin.getEmail().equals(colocation.getAdmin().getEmail())) {
            colocation.setName(newName);
            colocationDao.edit(colocation);
            return true;
        }
        return false;
    }

    public static boolean removeMemberFromColoc(String name, String admin_email, String member_email) {
        User admin = getUser(admin_email);
        User toDelete = getUser(member_email);
        Colocation colocation = getColocation(name);

        if (colocation != null && admin != null && toDelete != null) {
            if (isAdmin(admin, colocation) && userIsIntoColoc(toDelete, colocation)) {
                colocation.getMembers().remove(toDelete);
                colocationDao.edit(colocation);
                return true;
            }
        }
        return false;
    }
}
