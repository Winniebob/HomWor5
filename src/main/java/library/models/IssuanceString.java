package library.models;

import lombok.Data;

@Data
public class IssuanceString {
    private final long id;
    private final String bookName;
    private final String readerName;
    private final String issuance_at;
    private final String returned_at;

    public IssuanceString(long id, String bookName, String readerName, String issuance_at, String returned_at) {
        this.id = id;
        this.bookName = bookName;
        this.readerName = readerName;
        this.issuance_at = issuance_at;
        this.returned_at = returned_at;
    }
}