package org.avillar.captioner;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.avillar.captioner.errors.ConnectionException;

/** Fetches the content of a given URL. */
final class Fetcher {

  private Fetcher() {}

  /**
   * Fetches the content of a given URL.
   *
   * @param url the URL to fetch
   * @return the content of the URL
   * @throws ConnectionException if the connection fails
   */
  static String fetch(final String url) throws ConnectionException {
    try (final HttpClient client = HttpClient.newHttpClient()) {
      return client
          .send(
              HttpRequest.newBuilder().uri(URI.create(url)).build(),
              HttpResponse.BodyHandlers.ofString())
          .body();
    } catch (IOException | InterruptedException e) {
      throw new ConnectionException();
    }
  }
}
