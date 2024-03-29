package library.controllers;

import lombok.Data;

/**
 * Класс описывает запрос на выдачу книги
 */
@Data
public class IssuanceRequest {
    /**
     * Идентификатор читателя
     */
    private long readerId;
    /**
     * Идентификатор книги
     */
    private long bookId;
}