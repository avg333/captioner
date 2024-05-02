package org.avillar.captioner.errors;

public class NoCaptionsException extends CaptionerException {

  public NoCaptionsException(String videoId) {
    super("Could not find captionTracks in the data for video: " + videoId);
  }
}
