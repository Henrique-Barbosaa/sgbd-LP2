package errors;

public class NotFound extends Exception{
    public NotFound() {}

    public NotFound(String message) {
        super(message);
    }
}
