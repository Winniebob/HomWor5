package library.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    private static long idCounter = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Book() {
    }

    public Book(String name) {
        this.id = idCounter++;
        this.name = name;
    }


}