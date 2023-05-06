package de.arthurpicht.console.format;

public abstract class Format {

    public static Format BOLD() {
        return new BoldFormat();
    }

    public static Format ITALIC() {
        return new ItalicFormat();
    }

    public static Format RED_TEXT() {
        return new RedTextFormat();
    }

}
