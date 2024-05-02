package org.avillar.captioner;

import java.util.List;

/** Validates the input data */
final class Validator {

  private Validator() {}

  /**
   * Validates the video ID. It cannot be null or empty
   *
   * @param videoID the video ID
   * @throws IllegalArgumentException if the video ID is null or empty
   */
  static void videoIdValidation(final String videoID) throws IllegalArgumentException {
    if (videoID == null || videoID.isEmpty()) {
      throw new IllegalArgumentException("Video ID cannot be null or empty");
    }
  }

  /**
   * Validates the languages. The list cannot be null or empty and each language cannot be null or
   * empty
   *
   * @param langs the languages
   * @throws IllegalArgumentException if the list is null or empty or if any language is null or
   *     empty
   */
  static void langsValidation(final List<String> langs) throws IllegalArgumentException {
    if (langs == null || langs.isEmpty()) {
      throw new IllegalArgumentException("Languages cannot be null or empty");
    }
    langs.forEach(Validator::langValidation);
  }

  /**
   * Validates the language. It cannot be null or empty
   *
   * @param lang the language
   * @throws IllegalArgumentException if the language is null or empty
   */
  static void langValidation(final String lang) throws IllegalArgumentException {
    if (lang == null || lang.isEmpty()) {
      throw new IllegalArgumentException("Language cannot be null or empty");
    }
  }
}
