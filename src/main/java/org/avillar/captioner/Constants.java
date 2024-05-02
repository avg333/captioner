package org.avillar.captioner;

import java.util.Arrays;
import java.util.List;

/** Constants used in the application. */
final class Constants {

  /** All supported languages. */
  static final List<String> ALL_LANGUAGES =
      Arrays.asList(
          "en", "es", "hi", "ar", "pt", "bn", "ru", "ur", "fr", "id", "pa", "de", "ja", "te", "mr",
          "tr", "ta", "vi", "it", "gu", "ko", "th", "jv", "pl", "uk", "ms", "fil", "nl", "ku", "fa",
          "sv", "sr", "km", "cs", "hu", "fa", "ml", "ro", "be", "yo", "uz", "az", "am", "my", "si",
          "or", "af", "hr", "ps", "tk", "lg", "ht", "mk", "bg", "ha", "hy", "sd", "kri", "ca", "ay",
          "kk", "mt", "ka", "gl", "ga", "dv", "la", "sk", "gn", "om", "zh-Hant", "ti", "haw", "ug",
          "mn", "lt", "el", "qu", "sm", "ee", "et", "lb", "yi", "eu", "sn", "lv", "bs", "zu", "kn",
          "mi", "ky", "sl", "ts", "as", "su", "is", "tg", "co", "no", "iw", "ny", "mg", "sa", "cy",
          "ceb", "nso", "so", "sw", "ne", "bho", "zh-Hans", "eo", "fy", "hmn", "ig", "xh", "ln",
          "ak", "rw", "da", "lo", "sq", "tt", "fi", "st", "gd");
  /** The URL of a YouTube video. */
  static final String YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v=";

  private Constants() {}
}
