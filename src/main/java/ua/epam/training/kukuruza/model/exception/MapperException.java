package ua.epam.training.kukuruza.model.exception;

public class MapperException extends PersistenceException {
    public MapperException() {
    }

    public MapperException(String message) {
        super(message);
    }

    public MapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapperException(Throwable cause) {
        super(cause);
    }
}
