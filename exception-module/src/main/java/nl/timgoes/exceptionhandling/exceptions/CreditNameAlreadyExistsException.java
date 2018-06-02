package nl.timgoes.exceptionhandling.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CreditNameAlreadyExistsException extends RuntimeException {

    public CreditNameAlreadyExistsException(String message) {
        super("CreditNameAlreadyExistsException: " + message);
    }
}
