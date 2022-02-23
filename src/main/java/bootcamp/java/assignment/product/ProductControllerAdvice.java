package bootcamp.java.assignment.product;

import bootcamp.java.assignment.DefaultResponse;
import bootcamp.java.assignment.product.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DefaultResponse productNotFound(ProductNotFoundException e) {
        return new DefaultResponse(e.getMessage());
    }
}
