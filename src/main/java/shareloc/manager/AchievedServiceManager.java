package shareloc.manager;

import shareloc.model.AchievedService;
import shareloc.model.Service;
import shareloc.model.User;

import java.net.URI;
import java.util.Date;
import java.util.List;

public class AchievedServiceManager extends DaoManager {

    public AchievedServiceManager() {
        super();
    }

    public static boolean newAchievedService(String email, Long serviceID, Date date, URI picture) {
        Service service = serviceDao.find(serviceID);
        User user = getUser(email);

        if (user == null || service == null || date == null) {
            return false;
        }

        List<User> to = service.getColocation().getMembers();
        to.remove(user); //users who benefit from the service

        AchievedService achievedService = new AchievedService(user,to,date,picture,false);
        achievedServiceDao.create(achievedService);
        return true;
    }
}
