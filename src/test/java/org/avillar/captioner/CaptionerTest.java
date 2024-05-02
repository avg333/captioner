package org.avillar.captioner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.avillar.captioner.Captioner.getTrack;
import static org.avillar.captioner.Constants.ALL_LANGUAGES;
import static org.avillar.captioner.Constants.YOUTUBE_VIDEO_URL;
import static org.mockito.Mockito.mockStatic;

import java.util.Arrays;
import java.util.List;
import org.avillar.captioner.domain.Caption;
import org.avillar.captioner.domain.Name;
import org.avillar.captioner.domain.Track;
import org.avillar.captioner.errors.ConnectionException;
import org.avillar.captioner.errors.NoCaptionsException;
import org.avillar.captioner.errors.NoLangCaptionsException;
import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class CaptionerTest {

  private MockedStatic<JsonParser> mockJsonParser;
  private MockedStatic<Fetcher> mockFetcher;

  @BeforeEach
  public void setUp() {
    mockJsonParser = mockStatic(JsonParser.class);
    mockFetcher = mockStatic(Fetcher.class);
  }

  @AfterEach
  public void tearDown() {
    mockJsonParser.close();
    mockFetcher.close();
  }

  @Test
  void shouldGetCaptionsFromVideoSuccessfullyWithoutLanguage() {
    String videoId = Instancio.create(String.class);
    String videoData = Instancio.create(String.class);
    List<Track> tracks = Instancio.createList(Track.class);
    Track okTrack = new Track("1", new Name("2"), "3", ALL_LANGUAGES.getLast(), "4", true, "5");
    tracks.add(okTrack);
    String transcript = Instancio.create(String.class);
    List<Caption> captions = Instancio.createList(Caption.class);

    mockFetcher.when(() -> Fetcher.fetch(YOUTUBE_VIDEO_URL + videoId)).thenReturn(videoData);
    mockJsonParser.when(() -> JsonParser.extractTracksFromData(videoData)).thenReturn(tracks);
    mockFetcher.when(() -> Fetcher.fetch(okTrack.baseUrl())).thenReturn(transcript);
    mockJsonParser
        .when(() -> JsonParser.extractCaptionsFromTranscript(transcript))
        .thenReturn(captions);

    assertThat(Captioner.getCaptionsFromVideo(videoId)).isEqualTo(captions);
  }

  @Test
  void shouldGetCaptionsFromVideoSuccessfullyWithLanguage() {
    String videoId = Instancio.create(String.class);
    String videoData = Instancio.create(String.class);
    List<Track> tracks = Instancio.createList(Track.class);
    String transcript = Instancio.create(String.class);
    List<Caption> captions = Instancio.createList(Caption.class);

    mockFetcher.when(() -> Fetcher.fetch(YOUTUBE_VIDEO_URL + videoId)).thenReturn(videoData);
    mockJsonParser.when(() -> JsonParser.extractTracksFromData(videoData)).thenReturn(tracks);
    mockFetcher.when(() -> Fetcher.fetch(tracks.getFirst().baseUrl())).thenReturn(transcript);
    mockJsonParser
        .when(() -> JsonParser.extractCaptionsFromTranscript(transcript))
        .thenReturn(captions);

    assertThat(Captioner.getCaptionsFromVideo(videoId, tracks.getFirst().languageCode()))
        .isEqualTo(captions);
  }

  @Test
  void shouldGetCaptionsFromVideoSuccessfullyWithLanguages() {
    String videoId = Instancio.create(String.class);
    String videoData = Instancio.create(String.class);
    List<Track> tracks = Instancio.createList(Track.class);
    String transcript = Instancio.create(String.class);
    List<Caption> captions = Instancio.createList(Caption.class);

    mockFetcher.when(() -> Fetcher.fetch(YOUTUBE_VIDEO_URL + videoId)).thenReturn(videoData);
    mockJsonParser.when(() -> JsonParser.extractTracksFromData(videoData)).thenReturn(tracks);
    mockFetcher.when(() -> Fetcher.fetch(tracks.getLast().baseUrl())).thenReturn(transcript);
    mockJsonParser
        .when(() -> JsonParser.extractCaptionsFromTranscript(transcript))
        .thenReturn(captions);

    assertThat(Captioner.getCaptionsFromVideo(videoId, List.of(tracks.getLast().languageCode())))
        .isEqualTo(captions);
  }

  @Test
  void shouldThrowNoCaptionsExceptionWhenNoTracksFound() {
    String videoId = Instancio.create(String.class);
    String videoData = Instancio.create(String.class);

    mockFetcher.when(() -> Fetcher.fetch(YOUTUBE_VIDEO_URL + videoId)).thenReturn(videoData);
    mockJsonParser.when(() -> JsonParser.extractTracksFromData(videoData)).thenReturn(List.of());

    assertThatThrownBy(() -> Captioner.getCaptionsFromVideo(videoId))
        .isInstanceOf(NoCaptionsException.class)
        .hasMessageContaining(videoId);
  }

  @Test
  void shouldThrowNoCaptionsExceptionWhenNoTracksFoundWithLanguage() {
    String videoId = Instancio.create(String.class);
    String videoData = Instancio.create(String.class);
    List<Track> tracks = Instancio.createList(Track.class);
    String transcript = Instancio.create(String.class);

    mockFetcher.when(() -> Fetcher.fetch(YOUTUBE_VIDEO_URL + videoId)).thenReturn(videoData);
    mockJsonParser.when(() -> JsonParser.extractTracksFromData(videoData)).thenReturn(tracks);
    mockFetcher.when(() -> Fetcher.fetch(tracks.getLast().baseUrl())).thenReturn(transcript);

    assertThatThrownBy(() -> Captioner.getCaptionsFromVideo(videoId))
        .isInstanceOf(NoLangCaptionsException.class);
  }

  @Test
  void shouldThrowConnectionExceptionWhenErrorFetchingData() {
    String videoId = Instancio.create(String.class);
    ConnectionException exception = Instancio.create(ConnectionException.class);

    mockFetcher.when(() -> Fetcher.fetch(YOUTUBE_VIDEO_URL + videoId)).thenThrow(exception);

    assertThatThrownBy(() -> Captioner.getCaptionsFromVideo(videoId)).isEqualTo(exception);
  }

  @Test
  void getTrack_returnsTrackWhenLanguageMatchFound() {
    List<Track> tracks = Instancio.createList(Track.class);
    List<String> langs =
        Arrays.asList(tracks.getLast().languageCode(), tracks.getFirst().languageCode());

    assertThat(getTrack(tracks, langs)).isPresent().contains(tracks.getLast());
  }

  @Test
  void getTrack_returnsEmptyOptionalWhenNoLanguageMatchFound() {
    List<Track> tracks = Instancio.createList(Track.class);
    List<String> langs = Instancio.createList(String.class);

    assertThat(getTrack(tracks, langs)).isEmpty();
  }
}
