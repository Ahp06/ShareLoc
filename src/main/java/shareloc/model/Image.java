package shareloc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private byte[] bytes;


    public Image(){
    }

    public Image(String name, byte[] bytes, AchievedService achievedService) {
        this.name = name;
        this.bytes = bytes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}
