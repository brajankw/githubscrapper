package kwiatkowski.brajan.githubscrapper.exception;

import java.io.Serial;

/**
 * Custom exception class for indicating that a resource was not found.
 */
public class NotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 8558152046450792541L;

  /**
   * Constructs a NotFoundException instance with the provided message.
   *
   * @param message The error message indicating the reason for the exception.
   */
  public NotFoundException(String message) {
    super(message);
  }
}
