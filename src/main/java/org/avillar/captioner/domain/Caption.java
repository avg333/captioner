package org.avillar.captioner.domain;

/**
 * Represents a caption in a video.
 *
 * @param start instant in seconds when the caption appears
 * @param duration duration in seconds of the caption appearance
 * @param text text of the caption
 */
public record Caption(double start, double duration, String text) {}
