package com.belatrix.references.patterns.models;

import java.util.regex.Pattern;

public final class FileConstants {

    public static final String BREAK = "\n";

    public static final String DELIMITER = " ";

    public static final Pattern TAG_REGEX = Pattern.compile("<body(.+?)</body>", Pattern.CASE_INSENSITIVE);

    public static final String PATTERN_TAG = "(?:\\s|^)#[A-Za-z0-9\\-\\.\\_]+(?:\\s|$)";
}
