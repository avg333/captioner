package org.avillar.captioner;

import static org.avillar.captioner.Constants.ALL_LANGUAGES;
import static org.avillar.captioner.Constants.YOUTUBE_VIDEO_URL;
import static org.avillar.captioner.Fetcher.fetch;
import static org.avillar.captioner.JsonParser.extractCaptionsFromTranscript;
import static org.avillar.captioner.JsonParser.extractTracksFromData;
import static org.avillar.captioner.Validator.langsValidation;
import static org.avillar.captioner.Validator.videoIdValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.avillar.captioner.domain.Caption;
import org.avillar.captioner.domain.Track;
import org.avillar.captioner.errors.NoCaptionsException;
import org.avillar.captioner.errors.NoLangCaptionsException;

/** This class is responsible for getting the captions from a YouTube video. */
public class Captioner {

  public List<Caption> getCaptionsFromVideo(final String videoID) {
    return this.getCaptionsFromVideo(videoID, ALL_LANGUAGES);
  }

  public List<Caption> getCaptionsFromVideo(final String videoID, final String lang) {
    final List<String> langs = new ArrayList<>(1);
    langs.add(lang);
    return this.getCaptionsFromVideo(videoID, langs);
  }

  public List<Caption> getCaptionsFromVideo(final String videoID, final List<String> langs) {
    videoIdValidation(videoID);
    langsValidation(langs);

    final String data = fetch(YOUTUBE_VIDEO_URL + videoID);

    final List<Track> captionTracksData = extractTracksFromData(data);
    if (captionTracksData.isEmpty()) {
      throw new NoCaptionsException(videoID);
    }

    final Optional<Track> track = getTrack(captionTracksData, langs);
    if (track.isEmpty()) {
      throw new NoLangCaptionsException(videoID, langs.getFirst());
    }

    final String transcript = fetch(track.get().baseUrl());

    return extractCaptionsFromTranscript(transcript);
  }

  /**
   * Get the track from the caption tracks.
   *
   * @param captionTracks list of caption tracks available
   * @param langs list of languages to search for in priority order
   * @return the track if found, empty otherwise
   */
  private Optional<Track> getTrack(final List<Track> captionTracks, final List<String> langs) {
    for (final String lang : langs) {
      for (final Track track : captionTracks) {
        if (track.languageCode().equals(lang)) {
          return Optional.of(track);
        }
      }
    }
    return Optional.empty();
  }
}
