package de.arthurpicht.console.message.format;

public abstract class Format {

    // text effects
    
    public static Format BOLD() {
        return new ColorFormat(new JColorSimpleAttribute("1"));
    }

    public static Format ITALIC() {
        return new ColorFormat(new JColorSimpleAttribute("3"));
    }

    public static Format UNDERLINE() {
        return new ColorFormat(new JColorSimpleAttribute("4"));
    }

    public static Format SLOW_BLINK() {
        return new ColorFormat(new JColorSimpleAttribute("5"));
    }

    public static Format RAPID_BLINK() {
        return new ColorFormat(new JColorSimpleAttribute("6"));
    }

    public static Format REVERSE() {
        return new ColorFormat(new JColorSimpleAttribute("7"));
    }

    public static Format HIDDEN() {
        return new ColorFormat(new JColorSimpleAttribute("8"));
    }

    public static Format STRIKETHROUGH() {
        return new ColorFormat(new JColorSimpleAttribute("9"));
    }

    public static Format FRAMED() {
        return new ColorFormat(new JColorSimpleAttribute("51"));
    }

    public static Format ENCIRCLED() {
        return new ColorFormat(new JColorSimpleAttribute("52"));
    }

    public static Format OVERLINED() {
        return new ColorFormat(new JColorSimpleAttribute("53"));
    }

    // foreground colors

    public static Format BLACK_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("30"));
    }

    public static Format RED_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("31"));
    }

    public static Format GREEN_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("32"));
    }

    public static Format YELLOW_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("33"));
    }

    public static Format BLUE_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("34"));
    }

    public static Format MAGENTA_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("35"));
    }

    public static Format CYAN_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("36"));
    }

    public static Format WHITE_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("37"));
    }

    // background colors

    public static Format BLACK_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("40"));
    }

    public static Format RED_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("41"));
    }

    public static Format GREEN_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("42"));
    }

    public static Format YELLOW_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("43"));
    }

    public static Format BLUE_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("44"));
    }

    public static Format MAGENTA_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("45"));
    }

    public static Format CYAN_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("46"));
    }

    public static Format WHITE_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("47"));
    }

    // foreground bright colors

    public static Format BRIGHT_BLACK_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("90"));
    }

    public static Format BRIGHT_RED_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("91"));
    }

    public static Format BRIGHT_GREEN_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("92"));
    }

    public static Format BRIGHT_YELLOW_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("93"));
    }

    public static Format BRIGHT_BLUE_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("94"));
    }

    public static Format BRIGHT_MAGENTA_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("95"));
    }

    public static Format BRIGHT_CYAN_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("96"));
    }

    public static Format BRIGHT_WHITE_TEXT() {
        return new ColorFormat(new JColorSimpleAttribute("97"));
    }

    // background bright colors

    public static Format BRIGHT_BLACK_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("100"));
    }

    public static Format BRIGHT_RED_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("101"));
    }

    public static Format BRIGHT_GREEN_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("102"));
    }

    public static Format BRIGHT_YELLOW_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("103"));
    }

    public static Format BRIGHT_BLUE_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("104"));
    }

    public static Format BRIGHT_MAGENTA_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("105"));
    }

    public static Format BRIGHT_CYAN_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("106"));
    }

    public static Format BRIGHT_WHITE_BACK() {
        return new ColorFormat(new JColorSimpleAttribute("107"));
    }

}
