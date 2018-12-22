package shareloc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Service implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private Long cost;
    @OneToOne
    private Colocation colocation;

    public Service(){
    }

    public Service(String title, String description, Long cost, Colocation colocation) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.colocation = colocation;
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

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Colocation getColocation() {
        return colocation;
    }

    public void setColocation(Colocation colocation) {
        this.colocation = colocation;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", colocation=" + colocation +
                '}';
    }
}
