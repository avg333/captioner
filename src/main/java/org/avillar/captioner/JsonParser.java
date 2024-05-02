package org.avillar.captioner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.avillar.captioner.domain.Caption;
import org.avillar.captioner.domain.Track;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/** Parses JSON data. */
final class JsonParser {

  private static final Pattern CAPTION_TRACKS_REGEX =
      Pattern.compile("\"captionTracks\":(\\[[^]]*+])");

  private JsonParser() {}

  /**
   * Extracts tracks from a YouTube video data XML if they exist.
   *
   * @param jsonData the YouTube video data XML to extract tracks from
   * @return a list of tracks if they exist, otherwise an empty list
   */
  static List<Track> extractTracksFromData(final String jsonData) {
    final Matcher matcher = CAPTION_TRACKS_REGEX.matcher(jsonData);
    if (matcher.find()) {
      final String captionsData = matcher.group(1);
      final JSONObject json = new JSONObject("{captionTracks:" + captionsData + "}");
      final JSONArray jsonArray = json.getJSONArray("captionTracks");
      final List<Track> tracks = new ArrayList<>(jsonArray.length());
      for (int i = 0; i < jsonArray.length(); i++) {
        tracks.add(new Track(jsonArray.getJSONObject(i)));
      }
      return tracks;
    }
    return List.of();
  }

  /**
   * Extracts captions from a YouTube transcript.
   *
   * @param transcript the YouTube transcript to extract captions from in XML format
   * @return a list of captions
   */
  static List<Caption> extractCaptionsFromTranscript(final String transcript) {
    final Document doc = Jsoup.parse(transcript);
    final Elements unparsedCaptions = doc.select("text");

    final List<Caption> captions = new ArrayList<>(unparsedCaptions.size());
    for (final Element unparsedCaption : unparsedCaptions) {
      final double start = Double.parseDouble(unparsedCaption.attr("start"));
      final double duration = Double.parseDouble(unparsedCaption.attr("dur"));
      final String text = unparsedCaption.text();

      captions.add(new Caption(start, duration, text));
    }

    return captions;
  }
}
