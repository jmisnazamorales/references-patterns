package com.belatrix.references.patterns.models;

import java.util.regex.Pattern;

public final class FileConstants {

    public static final String BREAK = "\n";

    public static final String DELIMITER = " ";

    public static final String DELIMITER_NAME = "\\.";

    public static final String FILE_EXTENSION = ".txt";

    public static final String FILE_FAILURES = "failure_urls.txt";

    public static final Pattern TAG_REGEX = Pattern.compile("<style>(.+?)</style>", Pattern.DOTALL);

    public static final Pattern TAG_REGEX_SCRIPT = Pattern.compile("<p(.+?)</p>", Pattern.DOTALL);

    public static final String PATTERN_TAG = "(?:\\s|^)#[A-Za-z0-9\\-\\.\\_]+(?:\\s|$)";
}
