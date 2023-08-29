package kwiatkowski.brajan.githubscrapper.exception;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 8558152046450792541L;

  public NotFoundException(String message) {
    super(message);
  }
}
