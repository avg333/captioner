package org.avillar.captioner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.avillar.captioner.JsonParser.extractCaptionsFromTranscript;
import static org.avillar.captioner.JsonParser.extractTracksFromData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.avillar.captioner.domain.Caption;
import org.avillar.captioner.domain.Track;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonParserTest {

  private static final String PATH = "src/test/resources/org/avillar/captioner/";

  @Test
  void shouldGetTracksFromDataSuccessfully() {
    String filename = "example_tracks_ok.xml";
    String data = getTextFromResource(PATH + filename);

    List<Track> tracks = extractTracksFromData(data);
    assertThat(tracks).hasSize(1);
    System.out.println(tracks.getFirst());
    assertThat(tracks.getFirst())
        .extracting("baseUrl", "vssId", "languageCode", "kind", "isTranslatable", "trackName")
        .containsExactly(
            "https://www.youtube.com/api/timedtext?v=uM_6r68f6wY&ei=S-4zZt71Nsn3xN8Pz_uFiAk&caps=asr&opi=112496729&xoaf=5&hl=es&ip=0.0.0.0&ipbits=0&expire=1714704571&sparams=ip,ipbits,expire,v,ei,caps,opi,xoaf&signature=10FF27BA58F5255DBD84AEA4B1BFC05874B1F5DF.59F4A5F6ABF874462CED95F954D9201A6602E6D4&key=yt8&kind=asr&lang=es",
            "a.es",
            "es",
            "asr",
            true,
            "");
  }

  @Test
  void shouldGetEmptyTracksFromDataSuccessfully() {
    String filename = "example_tracks_ko.xml";
    String data = getTextFromResource(PATH + filename);

    assertThat(extractTracksFromData(data)).isNotNull().isEmpty();
  }

  @Test
  void shouldExtractCaptionsFromTranscriptionSuccessfully() {
    String filename = "example_transcription_ok.xml";
    String transcript = getTextFromResource(PATH + filename);

    List<Caption> captions = extractCaptionsFromTranscript(transcript);
    assertThat(captions).hasSize(3);
    assertThat(captions.get(0))
        .extracting("start", "duration", "text")
        .containsExactly(0.04, 5.4, "test line 1");
    assertThat(captions.get(1))
        .extracting("start", "duration", "text")
        .containsExactly(2.879, 4.601, "test line 2");
    assertThat(captions.get(2))
        .extracting("start", "duration", "text")
        .containsExactly(5.44, 4.0, "test line 3");
  }

  private String getTextFromResource(String path) {
    try {
      return new String(Files.readAllBytes(Paths.get(path)));
    } catch (IOException e) {
      Assertions.fail("Could not read file: " + path);
      return null;
    }
  }
}
