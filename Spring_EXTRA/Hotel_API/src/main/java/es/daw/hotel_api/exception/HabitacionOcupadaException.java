package es.daw.hotel_api.exception;

public class HabitacionOcupadaException extends RuntimeException {
    public HabitacionOcupadaException(String message) {
        super(message);
    }
}
