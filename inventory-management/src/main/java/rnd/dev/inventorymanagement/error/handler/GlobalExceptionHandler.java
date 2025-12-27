package rnd.dev.inventorymanagement.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rnd.dev.inventorymanagement.error.exception.ProductInsufficientQuantityException;
import rnd.dev.inventorymanagement.error.exception.ProductNotFoundException;
import rnd.dev.inventorymanagement.error.response.ApiErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleProductNotFound(ProductNotFoundException productNotFoundException) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), productNotFoundException.getMessage());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductInsufficientQuantityException.class)
    public ResponseEntity<ApiErrorResponse> handleProductInsufficient(ProductInsufficientQuantityException productInsufficientQuantityException) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), productInsufficientQuantityException.getMessage());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
