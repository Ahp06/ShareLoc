package shareloc.manager;

import shareloc.model.AchievedService;
import shareloc.model.Image;
import shareloc.model.Service;
import shareloc.model.User;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class AchievedServiceManager extends DaoManager {

    public AchievedServiceManager() {
        super();
    }

    /**
     * Store into the DB a new Image entity
     * @param imgPath
     * @return
     */
    public static Image downloadImg(String imgPath) {
        File img = new File(imgPath);
        try {
            byte[] bytes = Files.readAllBytes(img.toPath());
            Image image = new Image(bytes);
            imageDao.create(image);
            return image;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Declare the realization of a service
     *
     * @param email
     * @param serviceID
     * @param date
     * @param picture
     * @return
     */
    public static boolean newAchievedService(String email, Long serviceID, String date, String picture) {

        Service service = serviceDao.find(serviceID);
        User user = getUser(email);

        if (user == null || service == null || date == null) {
            return false;
        }

        List<User> to = service.getColocation().getMembers();
        to.remove(user); //users who benefit from the service

        Image image = downloadImg(picture);

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
        Date achieved_date = null;
        try {
            achieved_date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        AchievedService achievedService = new AchievedService(user, to,
                achieved_date, image, false);
        achievedServiceDao.create(achievedService);


        return true;
    }
}
