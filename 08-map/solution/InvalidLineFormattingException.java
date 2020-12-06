package solution;

public class InvalidLineFormattingException extends RuntimeException {
  public InvalidLineFormattingException(String message) {
     super(message);
  }

  public InvalidLineFormattingException(Throwable cause) {
     super(cause);
  }

  public InvalidLineFormattingException(String message, Throwable throwable) {
     super(message, throwable);
  }
}