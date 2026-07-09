package es.daw.hotel_api.exception;

public class HabitacionNotFoundException extends RuntimeException {
    public HabitacionNotFoundException(String message) {
        super(message);
    }
}
