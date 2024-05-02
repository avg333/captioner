package org.avillar.captioner.errors;

/** Exception thrown when an error occurs in the captioner. */
public abstract class CaptionerException extends RuntimeException {

  /**
   * Creates a new CaptionerException.
   *
   * @param message the error message
   */
  protected CaptionerException(final String message) {
    super(message);
  }
}
