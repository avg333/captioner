package org.avillar.captioner.errors;

public class NoLangCaptionsException extends CaptionerException {

  public NoLangCaptionsException(String videoId, String lang) {
    super("Could not find captionTracks in the data for video: " + videoId + " with lang: " + lang);
  }
}
