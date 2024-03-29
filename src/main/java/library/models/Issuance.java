package library.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Класс описывает процесс выдачи книги в БД
 */
@Entity
@Data
public class Issuance {
    private static long idCounter = 1L;

    @Id
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "reader_id")
    private Long readerId;
    /**
     * Дата выдачи книги
     */
    @Column(name = "issuance_at")
    private LocalDateTime issuance_at;
    /**
     * Дата возврата книги
     */
    @Column(name = "returned_at")
    private LocalDateTime returned_at;

    public Issuance() {
    }

    public Issuance(long bookId, long readerId) {
        this.id = idCounter++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.issuance_at = LocalDateTime.now();
    }


}