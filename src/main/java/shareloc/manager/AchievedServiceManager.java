package shareloc.manager;

import shareloc.model.AchievedService;
import shareloc.model.Service;
import shareloc.model.User;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AchievedServiceManager extends DaoManager {

    public AchievedServiceManager() {
        super();
    }

    /**
     * Download the user img on disk with this format : /imgs/img_current_timestamp
     *
     * @param imgPath
     */
    public static void downloadImg(String imgPath, String output) {
        try {
            Iterator writers = ImageIO.getImageWritersByFormatName("jpg");
            ImageWriter writer = (ImageWriter)writers.next();

            File f = new File(output);
            ImageOutputStream ios = ImageIO.createImageOutputStream(f);
            writer.setOutput(ios);

            BufferedImage bf = ImageIO.read(new File(imgPath));
            writer.write(bf);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try {
            Service service = serviceDao.find(serviceID);
            User user = getUser(email);

            if (user == null || service == null || date == null) {
                return false;
            }

            List<User> to = service.getColocation().getMembers();
            to.remove(user); //users who benefit from the service

            Date today = new Date();
            SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
            String imgName = "\\AppIntAv\\imgs" +formater.format(today).toString() + ".jpg";

            downloadImg(picture, imgName);

            DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
            Date achieved_date = dateFormat.parse(date);
            AchievedService achievedService = new AchievedService(user, to,
                    achieved_date, imgName, false);
            achievedServiceDao.create(achievedService);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }
}
