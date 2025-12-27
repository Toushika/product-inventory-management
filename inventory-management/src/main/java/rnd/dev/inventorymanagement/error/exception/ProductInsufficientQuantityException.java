package rnd.dev.inventorymanagement.error.exception;

public class ProductInsufficientQuantityException extends RuntimeException {

    public ProductInsufficientQuantityException() {
    }

    public ProductInsufficientQuantityException(String message) {
        super(message);
    }

    public ProductInsufficientQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductInsufficientQuantityException(Throwable cause) {
        super(cause);
    }

    public ProductInsufficientQuantityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
