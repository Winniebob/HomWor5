package library.repositories;

import library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Класс описывает взаимодействие с хранилищем книг
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}