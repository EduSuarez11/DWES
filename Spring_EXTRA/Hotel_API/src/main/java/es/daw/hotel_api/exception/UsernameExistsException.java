package es.daw.hotel_api.exception;

public class UsernameExistsException extends RuntimeException {

    public UsernameExistsException(String msg) {
        super(msg);
    }
}
