package pl.krzychuuweb.debtmanagment.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionResponse {

    private final LocalDateTime localDateTime;
    private final HttpStatus status;
    private final String message;

    public ExceptionResponse(final HttpStatus status, final String message) {
        this.localDateTime = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
