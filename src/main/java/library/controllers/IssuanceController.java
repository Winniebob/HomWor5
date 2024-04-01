package library.controllers;

import library.models.Issuance;
import library.services.IssuanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/issuance")
public class IssuanceController {
    private final IssuanceService issuanceService;

    public IssuanceController(IssuanceService issuanceService) {
        this.issuanceService = issuanceService;
    }

    @GetMapping
    public ResponseEntity<List<Issuance>> issuanceList() {
        final List<Issuance> issuanceList;
        try {
            issuanceList = issuanceService.getIssuanceList();
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(issuanceList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issuance> getIssuanceById(@PathVariable long id) {
        Issuance issuance;
        try {
            issuance = issuanceService.getIssuanceById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(issuance);
    }

    @PostMapping
    public ResponseEntity<Issuance> issuanceBook(@RequestBody IssuanceRequest issuanceRequest) {
        final Issuance issuance;
        try {
            issuance = issuanceService.issuanceBook(issuanceRequest);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(issuance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Issuance> returnBookByReader(@PathVariable long id) {
        Issuance issuance;
        try {
            issuance = issuanceService.getIssuanceById(id);
            issuanceService.returnBookByReader(issuance);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(issuance);
    }
}