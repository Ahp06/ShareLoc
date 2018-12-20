package shareloc.model;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Colocation implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne
    private User admin;
    @OneToMany
    private List<User> members;

    public Colocation(){

    }

    public Colocation(String name, User admin){
        this.name = name;
        this.admin = admin;
        this.members = new ArrayList<>();
        members.add(admin);
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", admin=" + admin +
                ", members=" + members +
                '}';
    }
}
