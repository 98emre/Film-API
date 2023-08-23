package experis.academy.filmapi.exceptions;

public class MovieNotFoundException extends Exception {
    
    public MovieNotFoundException() {
        super();
    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
