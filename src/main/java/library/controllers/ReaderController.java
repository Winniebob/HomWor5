package library.controllers;

import library.models.Issuance;
import library.models.Reader;
import library.services.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/reader")
public class ReaderController {
    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping()
    public ResponseEntity<List<Reader>> getReaderList() {
        final List<Reader> readers;
        try {
            readers = readerService.getReaderList();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(readers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReaderById(@PathVariable long id) {
        final Reader reader;
        try {
            reader = readerService.getReaderById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }

    @GetMapping("/{id}/issuance")
    public ResponseEntity<List<Issuance>> getIssuanceByIdReader(@PathVariable long id) {
        List<Issuance> list;
        try {
            list = readerService.readerIssuanceListById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @PostMapping()
    public ResponseEntity<Reader> addNewReader(@RequestBody Reader newBook) {
        Reader reader;
        try {
            reader = readerService.addNewReader(newBook);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(reader);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reader> deleteReaderById(@PathVariable long id) {
        final Reader reader;
        try {
            reader = readerService.deleteReaderById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }
}