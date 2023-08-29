package kwiatkowski.brajan.githubscrapper.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiError {

  private HttpStatus status;
  private String message;

  public ApiError(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  public void createErrorMessageForNotFound() {
    String defaultMessage = "You provided wrong variables try again";
    String userRepositoryPattern = "users\\/\\w+\\/repos";
    if (message.matches(userRepositoryPattern)) {
      String username = message.split("/")[1];
      message = "Username : " + username + "does not exist.";
    } else {
      message = defaultMessage;
    }
  }

  public Map<String, String> createResponse() {
    Map<String, String> map = new HashMap<>();
    map.put("status", String.valueOf(status.value()));
    map.put("message", message);
    return map;
  }
}
