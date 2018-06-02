package nl.timgoes.exceptionhandling.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserCreditNotFoundException extends RuntimeException {

    public UserCreditNotFoundException(String message) {
        super("UserCreditNotFoundException: " + message);
    }
}
