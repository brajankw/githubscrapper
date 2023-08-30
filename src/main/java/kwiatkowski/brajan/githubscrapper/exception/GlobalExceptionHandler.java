package kwiatkowski.brajan.githubscrapper.exception;

import java.util.Map;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for mapping exceptions to appropriate HTTP responses.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles the NotFoundException and maps it to an HTTP 404 response.
   *
   * @param ex The NotFoundException instance.
   * @return ResponseEntity containing the error response.
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Map<String, String>> handleNotFoundException(
      NotFoundException ex) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
    apiError.createErrorMessageForNotFound();

    return new ResponseEntity<>(apiError.createResponse(), apiError.getStatus());
  }

  /**
   * Handles the HeaderNotValidException and maps it to an HTTP 406 response.
   *
   * @param ex The HeaderNotValidException instance.
   * @return ResponseEntity containing the error response.
   */
  @ExceptionHandler(HeaderNotValidException.class)
  public ResponseEntity<Map<String, String>> handleHeaderNotValidException(
      HeaderNotValidException ex) {
    ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());

    return new ResponseEntity<>(apiError.createResponse(),HttpStatusCode.valueOf(406));
  }
}
