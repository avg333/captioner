package org.avillar.captioner.errors;

/** Exception thrown when there is a connection error trying to fetch data from the server */
public class ConnectionException extends CaptionerException {

  /** Constructor for the ConnectionException */
  public ConnectionException() {
    super("Connection error trying to fetch data from the server");
  }
}
