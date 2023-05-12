package de.arthurpicht.console.utils;

/**
 * See <a href="https://en.wikipedia.org/wiki/ANSI_escape_code">Wikipedia ANSI-Escape-Codes</a>
 */
public class AnsiCode {

    public static String ERASE_LINE_CONTENT() {
        return "\033[2K";
    }

    public static String CARRIAGE_RETURN() {
        return "\r";
    }

    public static String MOVE_LINES_UP(int lines) {
        return "\033[" + lines + "A";
    }

}
