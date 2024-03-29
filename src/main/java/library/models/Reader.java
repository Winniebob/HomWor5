package library.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Reader {
    private static long idCounter = 1L;

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    public Reader(String name) {
        this.id = idCounter++;
        this.name = name;
    }

    public Reader() {

    }
}