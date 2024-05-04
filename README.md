# 📚 Captioner

Captioner is a Java library for extracting subtitles from YouTube videos.

## Quick Start

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.msgpack/msgpack-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.msgpack/msgpack-core/)

For Maven users:

```
<dependency>
   <groupId>org.avillar</groupId>
   <artifactId>captioner</artifactId>
   <version>0.1-SNAPSHOT</version>
</dependency>
```

## Example

To get the subtitles from a YouTube video, simply provide the video ID and call
the `getCaptionsFromVideo` method from the `Captioner` class:

```java
import org.avillar.captioner.Captioner;

public class Main {

  public static void main(String[] args) {
    String videoId = "uM_6r68f6wY";
    List<Caption> captions = Captioner.getCaptionsFromVideo(videoId);
    for (Caption caption : captions) {
      log.info(caption);
    }
  }
}
```

If you want to get the subtitles from a YouTube video in a specific language, provide the video ID
and the language code and call the `getCaptionsFromVideo` method from the `Captioner` class:

```java
import org.avillar.captioner.Captioner;

public class Main {

  public static void main(String[] args) {
    String videoId = "uM_6r68f6wY";
    String lang = "en";
    List<Caption> captions = Captioner.getCaptionsFromVideo(videoId, lang);
    for (Caption caption : captions) {
      log.info(caption.toString());
    }
  }
}
```

If you want to get the subtitles from a YouTube video in a language in a list of languages, provide
the video ID and the list of language codes and call the `getCaptionsFromVideo` method from
the `Captioner` class:

```java
import org.avillar.captioner.Captioner;

public class Main {

  public static void main(String[] args) {
    String videoId = "uM_6r68f6wY";
    List<String> langs = Arrays.asList("en", "es", "fr");
    List<Caption> captions = Captioner.getCaptionsFromVideo(videoId, langs);
    for (Caption caption : captions) {
      log.info(caption.toString());
    }
  }
}
```

## Error Handling

Whenever an error occurs while trying to get the subtitles from a YouTube video, an exception that
extends `CaptionerException` will be thrown.

Exists the following exceptions:

- `CaptionerException`: Base exception for all Captioner exceptions.
- `ConnectionException`: Exception thrown when an error occurs connecting to YouTube.
- `NoCaptionsException`: Exception thrown when the video has no subtitles.
- `NoLangCaptionsException`:  Exception thrown when the video does not have subtitles in the
  specified language or languages.

## Domain objects

### Caption

Represents a caption of a YouTube video.

- `text`: Text of the caption.
- `start`: Instant in seconds when the caption appears.
- `end`: Duration in seconds since the caption appears until it disappears.

### videoId

Represents the YouTube video identifier.

- `videoId`: YouTube video ID.

### lang

Represents the language code desired for the subtitles.

- `lang`: Language code.

### langs

Represents a list of language codes desired for the subtitles in priority order.

- `langs`: List of language codes.