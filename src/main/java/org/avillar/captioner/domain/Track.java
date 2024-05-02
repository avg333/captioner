package org.avillar.captioner.domain;

import org.json.JSONObject;

public record Track(
    String baseUrl,
    Name name,
    String vssId,
    String languageCode,
    String kind,
    Boolean isTranslatable,
    String trackName) {

  public Track(final JSONObject json) {
    this(
        json.getString("baseUrl"),
        new Name(json.getJSONObject("name")),
        json.getString("vssId"),
        json.getString("languageCode"),
        json.optString("kind"),
        json.getBoolean("isTranslatable"),
        json.getString("trackName"));
  }
}
