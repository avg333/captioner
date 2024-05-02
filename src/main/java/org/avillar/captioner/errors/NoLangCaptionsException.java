package org.avillar.captioner.errors;

import java.util.List;

/** Exception thrown when the video does not have captions in the specified languages. */
public class NoLangCaptionsException extends CaptionerException {

  /**
   * Creates a new NoLangCaptionsException.
   *
   * @param videoId the video ID
   * @param langs the languages
   */
  public NoLangCaptionsException(String videoId, List<String> langs) {
    super(
        "Could not find captionTracks in the data for video: "
            + videoId
            + " with languages: "
            + langs);
  }
}
