package se.lexicon.g40_jpa_booking.exception;

public class AppResourceNotFoundException extends RuntimeException{

    public AppResourceNotFoundException(String message) {
        super(message);
    }
}
