package experis.academy.filmapi.utilites.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieCharacterNotFoundException extends RuntimeException {

    public MovieCharacterNotFoundException(int id) {
        super("Character with id: " + id + " does not exist");
    }

}
