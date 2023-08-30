package kwiatkowski.brajan.githubscrapper.exception;

import java.io.Serial;

/**
 * Custom exception class for indicating that a request header is not valid.
 */
public class HeaderNotValidException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 277612596303076153L;

  /**
   * Constructs a HeaderNotValidException instance with the provided message.
   *
   * @param message The error message indicating the reason for the exception.
   */
  public HeaderNotValidException(String message) {
    super(message);
  }
}
