package shareloc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

@Entity
public class Colocation implements Serializable {

    @Id
    private String name;
    @OneToOne
    private User admin;
    @OneToMany
    private List<User> members;

    public Colocation(){

    }

    public Colocation(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Colocation{" +
                "name='" + name + '\'' +
                ", admin=" + admin +
                ", members=" + members +
                '}';
    }
}
