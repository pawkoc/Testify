package pl.edu.agh.testify.tasks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class TaskCannotBeSavedException extends Exception {

    public TaskCannotBeSavedException(String message, Throwable cause) {
        super(message, cause);
    }
}
