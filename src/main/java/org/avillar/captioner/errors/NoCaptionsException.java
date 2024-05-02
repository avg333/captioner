package org.avillar.captioner.errors;

/** Exception thrown when the video does not have any captions. */
public class NoCaptionsException extends CaptionerException {

  /**
   * Creates a new NoCaptionsException.
   *
   * @param videoId the video ID
   */
  public NoCaptionsException(String videoId) {
    super("Could not find captionTracks in the data for video: " + videoId);
  }
}
