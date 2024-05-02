package org.avillar.captioner.domain;

import org.json.JSONObject;

public record Name(String simpleText) {

  public Name(final JSONObject json) {
    this(json.getString("simpleText"));
  }
}
