package nl.timgoes.exceptionhandling.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CreditNotFoundException extends RuntimeException {

    public CreditNotFoundException(String message) {
        super("CreditNotFoundException: " + message);
    }
}
