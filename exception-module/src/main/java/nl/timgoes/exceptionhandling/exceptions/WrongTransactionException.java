package nl.timgoes.exceptionhandling.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongTransactionException extends RuntimeException {

    public WrongTransactionException(String message) {
        super("WrongTransactionException: " + message);
    }
}
