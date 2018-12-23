package shareloc.manager;

import shareloc.model.Colocation;
import shareloc.model.Service;
import shareloc.model.User;

public class ServiceManager extends DaoManager {

    public ServiceManager() {
        super();
    }

    public static boolean createService(String email, String colocationName, String title, String description, int cost) {
        User creator = getUser(email);
        Colocation colocation = getColocation(colocationName);

        if(creator != null && colocation != null){
            Service service = new Service(colocation, creator, title,description,cost);
            serviceDao.create(service);
            return true;
        }
        return false;
    }
}
