package library.services;

import jakarta.annotation.PostConstruct;
import library.models.Issuance;
import library.models.Reader;
import library.repositories.ReaderRepository;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class ReaderService {
    private  ReaderRepository readerRepository;
    private final IssuanceService issuanceService;

    public ReaderService(ReaderRepository readerRepository, IssuanceService issuanceService) {
        this.readerRepository = readerRepository;
        this.issuanceService = issuanceService;
    }

    /**
     * Первоначальные тестовые данные
     */
    @PostConstruct
    void generateData() {
        readerRepository.saveAll(
                List.of(
                        new Reader("Максим"),
                        new Reader("Павел"),
                        new Reader("Евкакий"),
                        new Reader("Марк"),
                        new Reader("Даша"),
                        new Reader("Оля")
                )
        );
    }

    /**
     * Метод обрабатывает получение списка выдачи книг читателю по ID
     *
     * @param id идентификатор читателя
     * @return если список не пуст, то метод возвращает список выдачи книг, иначе исключение
     */
    public List<Issuance> readerIssuanceListById(long id) {
        List<Issuance> list = issuanceService.getIssuanceByIdReader(id);
        if (list.isEmpty()) {
            throw new NoSuchElementException("Читателю с ID = " + id + " книги не выдавались");
        }
        return list;
    }

    /**
     * Метод обрабатывает получение читателя по ID
     *
     * @param id идентификатор читателя
     * @return если данные полученные не пусты, то метод возвращает читателя по ID, иначе исключение
     */
    public Reader getReaderById(long id) {
        Reader reader = readerRepository.findById(id).get();
        if (Objects.isNull(reader)) {
            throw new NoSuchElementException("Читатель с ID = " + id + " не найдена");
        }
        return reader;
    }

    /**
     * Метод обрабатывает получение списка читателей
     *
     * @return если список не пуст, то метод возвращает список читателей, иначе исключение
     */
    public List<Reader> getReaderList() {
        List<Reader> readers = readerRepository.findAll();
        if (readers.isEmpty()) {
            throw new NoSuchElementException("Список читателей пуст");
        }
        return readers;
    }

    /**
     * Метод обрабатывает данные читателя, введенные пользователем, для записи
     *
     * @param reader данные полученные от пользователя
     * @return если данные введенные корректно, то метод возвращает информацию о пользователе подлежащей для записи,
     * иначе исключение
     */
    public Reader addNewReader(Reader reader) {
        if (reader.getName().isEmpty()) {
            throw new RuntimeException("Имя читателя не задано");
        }
        return readerRepository.save(reader);
    }

    /**
     * Метод проверяет информацию о читателе перед удалением
     *
     * @param id идентификатор читателя подлежащего удалению
     * @return информацию удаленного читателя
     */
    public Reader deleteReaderById(long id) {
        Reader reader = getReaderById(id);
        readerRepository.deleteById(id);
        if (Objects.isNull(reader)) {
            throw new NoSuchElementException("Читатель с ID = " + id + " не найдена");
        }
        return reader;
    }
}