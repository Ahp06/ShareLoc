package shareloc.model;

import javax.persistence.*;
import java.net.URI;
import java.util.Date;
import java.util.List;

@Entity
public class AchievedService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User from;
    @OneToMany
    private List<User> to;
    private Date date;
    //We will store in the DB the path on the disk to the downloaded picture
    private String picture;
    private boolean validated;

    public AchievedService(){
    }

    public AchievedService(User from, List<User> to, Date date, String picture, boolean validated) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.picture = picture;
        this.validated = validated;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public List<User> getTo() {
        return to;
    }

    public void setTo(List<User> to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
