package nl.timgoes.exceptionhandling.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughUserCreditException extends RuntimeException {

    public NotEnoughUserCreditException(String message) {
        super("NotEnoughUserCreditException: " + message);
    }
}
