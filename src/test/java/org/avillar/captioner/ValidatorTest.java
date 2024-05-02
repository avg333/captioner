package org.avillar.captioner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class ValidatorTest {

  @Test
  void shouldNotThrowExceptionWhenVideoIdIsValid() {
    String videoId = Instancio.create(String.class);
    assertDoesNotThrow(() -> Validator.videoIdValidation(videoId));
  }

  @Test
  void shouldNotThrowExceptionWhenLangIsValid() {
    String lang = Instancio.create(String.class);
    assertDoesNotThrow(() -> Validator.langValidation(lang));
  }

  @Test
  void shouldNotThrowExceptionWhenLangsIsValid() {
    List<String> langs = Instancio.createList(String.class);
    assertDoesNotThrow(() -> Validator.langsValidation(langs));
  }

  @ParameterizedTest
  @EmptySource
  @NullSource
  void shouldThrowIllegalArgumentExceptionWhenVideoIdIsNullOrEmpty(String videoId) {
    assertThatThrownBy(() -> Validator.videoIdValidation(videoId))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Video ID cannot be null or empty");
  }

  @ParameterizedTest
  @EmptySource
  @NullSource
  void shouldThrowIllegalArgumentExceptionWhenLangIsNullOrEmpty(String lang) {
    assertThatThrownBy(() -> Validator.langValidation(lang))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Language cannot be null or empty");
  }

  @ParameterizedTest
  @EmptySource
  @NullSource
  void shouldThrowIllegalArgumentExceptionWhenLangsIsNullOrEmpty(List<String> langs) {
    assertThatThrownBy(() -> Validator.langsValidation(langs))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Languages cannot be null or empty");
  }

  @ParameterizedTest
  @EmptySource
  @NullSource
  void shouldThrowIllegalArgumentExceptionWhenLangsContainsNullOrEmpty(String lang) {
    List<String> langs = new ArrayList<>(1);
    langs.add(lang);
    assertThatThrownBy(() -> Validator.langsValidation(langs))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Language cannot be null or empty");
  }
}
