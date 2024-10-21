package hr.unizd.web.springbootAMapp.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {        //just a basic exception handler class
        super(message);
    }
}
