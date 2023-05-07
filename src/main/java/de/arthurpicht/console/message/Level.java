package de.arthurpicht.console.message;

public enum Level {

    NORMAL, VERBOSE, VERY_VERBOSE, VERY_VERY_VERBOSE;

    public static boolean applies(Level messageLevel, Level outputLevel) {
        return outputLevel.ordinal() >= messageLevel.ordinal();
    }

}
