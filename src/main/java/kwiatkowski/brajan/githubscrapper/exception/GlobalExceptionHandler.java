package kwiatkowski.brajan.githubscrapper.exception;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Map<String, String>> handleNotFoundException(
      NotFoundException ex) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
    apiError.createErrorMessageForNotFound();

    return new ResponseEntity<>(apiError.createResponse(), apiError.getStatus());
  }

  @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
  public ResponseEntity<Map<String, String>> handleHttpMediaTypeNotAcceptableExceptionException(
      HttpMediaTypeNotAcceptableException ex) {
    ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, "Value of header accept must equal to application/json");

    return new ResponseEntity<>(apiError.createResponse(), apiError.getStatus());
  }

}
