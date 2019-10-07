package ua.company.training.kukuruza.persistence.exception;

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
