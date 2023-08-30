package kwiatkowski.brajan.githubscrapper.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Represents an error response for API exceptions.
 */
@Getter
@AllArgsConstructor
public class ApiError {

  private HttpStatus status;
  private String message;

  /**
   * Creates an error message for resource not found scenario.
   */
  public void createErrorMessageForNotFound() {
    String userRepositoryPattern = "users\\/\\w+\\/repos";
    if (message.matches(userRepositoryPattern)) {
      String username = message.split("/")[1];
      message = "Username : " + username + "does not exist.";
    } else {
      message = "You provided wrong variables try again";
    }
  }

  /**
   * Creates a map representing the error response.
   *
   * @return A map containing status and message information.
   */
  public Map<String, String> createResponse() {
    Map<String, String> map = new HashMap<>();
    map.put("status", String.valueOf(status.value()));
    map.put("message", message);
    return map;
  }
}
