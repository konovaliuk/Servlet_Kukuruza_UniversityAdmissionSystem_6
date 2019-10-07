package ua.company.training.kukuruza.persistence.exception;

public class TransactionException extends PersistenceException {
    public TransactionException() {
    }

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionException(Throwable cause) {
        super(cause);
    }
}
