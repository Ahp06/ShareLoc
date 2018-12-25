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
    private URI picture;
    private boolean validated;

    public AchievedService(){
    }

    public AchievedService(User from, List<User> to, Date date, URI picture, boolean validated) {
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

    public URI getPicture() {
        return picture;
    }

    public void setPicture(URI picture) {
        this.picture = picture;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
