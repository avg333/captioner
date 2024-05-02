package org.avillar.captioner.errors;

public abstract class CaptionerException extends RuntimeException {

  protected CaptionerException(final String message) {
    super(message);
  }
}
