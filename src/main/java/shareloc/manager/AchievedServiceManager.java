package shareloc.manager;

import shareloc.model.AchievedService;
import shareloc.model.Image;
import shareloc.model.Service;
import shareloc.model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AchievedServiceManager extends DaoManager {

    public AchievedServiceManager() {
        super();
    }

    /**
     * Store into the DB a new Image entity
     *
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
     * @param to_emails
     * @return
     */
    public static boolean newAchievedService(String email, Long serviceID,
                                             String date, String picture, List<String> to_emails) {

        Service service = serviceDao.find(serviceID);
        User user = getUser(email);

        if (user == null || service == null || date == null) {
            return false;
        }

        System.out.println("to emails : " + to_emails);

        //users who benefit from the service
        List<User> to = new ArrayList<User>();
        for (String user_email : to_emails) {
            User to_user = getUser(user_email);
            if (to_user != null) to.add(to_user);
        }

        Image image = downloadImg(picture);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm", Locale.FRANCE);
        Date achieved_date = null;
        try {
            achieved_date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new java.sql.Timestamp(achieved_date.getTime());

        AchievedService achievedService = new AchievedService(user, to,
                timestamp, image, false);
        achievedServiceDao.create(achievedService);

        return true;
    }
}