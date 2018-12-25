package shareloc.manager;

import shareloc.model.AchievedService;
import shareloc.model.Service;
import shareloc.model.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AchievedServiceManager extends DaoManager {

    public AchievedServiceManager() {
        super();
    }

    /**
     * Download the user img on disk with this format : /imgs/img_current_timestamp
     * @param imgPath
     * @param imgName
     */
    public static void downloadImg(String imgPath, String imgName){

        File directory = new File("imgs");
        if (!directory.exists()){
            directory.mkdir();
        }

        try {
            BufferedImage img = img = ImageIO.read(new File(imgPath));
            File outputfile = new File(imgName);
            ImageIO.write(img, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean newAchievedService(String email, Long serviceID, Date date, String picture) {
        Service service = serviceDao.find(serviceID);
        User user = getUser(email);

        if (user == null || service == null || date == null) {
            return false;
        }

        List<User> to = service.getColocation().getMembers();
        to.remove(user); //users who benefit from the service

        Date current = new Date();
        String imgName = "/imgs/img_" + current.toString();
        downloadImg(picture,imgName);

        AchievedService achievedService = new AchievedService(user,to,date,imgName,false);
        achievedServiceDao.create(achievedService);
        return true;
    }
}
