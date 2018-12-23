package shareloc.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int cost;
    private int upVotes;
    private int downVotes;

    @OneToOne
    private Colocation colocation;
    @OneToOne
    private User created_by;


    public Service(){
    }

    public Service(Colocation colocation, User created_by, String title, String description, int cost) {
        this.colocation = colocation;
        this.created_by = created_by;
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.upVotes = 0;
        this.downVotes = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Colocation getColocation() {
        return colocation;
    }

    public void setColocation(Colocation colocation) {
        this.colocation = colocation;
    }

    public int getUpVotes(){
        return this.upVotes;
    }

    public void upVote(){
        this.upVotes ++;
    }

    public int getDownVotes(){
        return this.downVotes;
    }

    public void downVote(){
        this.downVotes ++;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", colocation=" + colocation +
                ", created_by=" + created_by +
                '}';
    }
}
