package shareloc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] bytes;

    public Image() {
    }

    public Image(byte[] bytes) {
        this.bytes = bytes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ",bytes=" + Arrays.toString(bytes) +
                '}';
    }
}
