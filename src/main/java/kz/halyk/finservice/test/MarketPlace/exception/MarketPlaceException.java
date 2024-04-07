package kz.halyk.finservice.test.MarketPlace.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MarketPlaceException extends RuntimeException {
    private String message;
    private LocalDateTime date;
    private HttpStatus httpStatus;

    public MarketPlaceException(String message) {
        super(message);
    }
}
