package fr.cerema.dsi.commons.exceptions;

/**
 * Exception à lever lorsque l'utilisateur n'a pas été trouvé
 */
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    super(message);
  }
}
