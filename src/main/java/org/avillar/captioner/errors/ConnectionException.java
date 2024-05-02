package org.avillar.captioner.errors;

public class ConnectionException extends CaptionerException {

  public ConnectionException() {
    super("Connection error trying to fetch data from the server");
  }
}
