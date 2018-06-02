package nl.timgoes.exceptionhandling.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserCreditAlreadyExistsException extends RuntimeException {

    public UserCreditAlreadyExistsException(String message) {
        super("UserCreditAlreadyExistsException: " + message);
    }
}
