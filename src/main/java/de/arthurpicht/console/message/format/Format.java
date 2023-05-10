package de.arthurpicht.console.message.format;

import com.diogonunes.jcolor.Attribute;
import de.arthurpicht.console.message.format.BlockFormat.Align;

public abstract class Format {

    // text effects

    public static Format BOLD() {
        return new ColorFormat(Attribute.BOLD());
    }

    public static Format ITALIC() {
        return new ColorFormat(Attribute.ITALIC());
    }

    public static Format UNDERLINE() {
        return new ColorFormat(Attribute.UNDERLINE());
    }

    public static Format SLOW_BLINK() {
        return new ColorFormat(Attribute.SLOW_BLINK());
    }

    public static Format RAPID_BLINK() {
        return new ColorFormat(Attribute.RAPID_BLINK());
    }

    public static Format REVERSE() {
        return new ColorFormat(Attribute.REVERSE());
    }

    public static Format HIDDEN() {
        return new ColorFormat(Attribute.HIDDEN());
    }

    public static Format STRIKETHROUGH() {
        return new ColorFormat(Attribute.STRIKETHROUGH());
    }

    public static Format FRAMED() {
        return new ColorFormat(Attribute.FRAMED());
    }

    public static Format ENCIRCLED() {
        return new ColorFormat(Attribute.ENCIRCLED());
    }

    public static Format OVERLINED() {
        return new ColorFormat(Attribute.OVERLINED());
    }

    // foreground colors

    public static Format BLACK_TEXT() {
        return new ColorFormat(Attribute.BLACK_TEXT());
    }

    public static Format RED_TEXT() {
        return new ColorFormat(Attribute.RED_TEXT());
    }

    public static Format GREEN_TEXT() {
        return new ColorFormat(Attribute.GREEN_TEXT());
    }

    public static Format YELLOW_TEXT() {
        return new ColorFormat(Attribute.YELLOW_TEXT());
    }

    public static Format BLUE_TEXT() {
        return new ColorFormat(Attribute.BLUE_TEXT());
    }

    public static Format MAGENTA_TEXT() {
        return new ColorFormat(Attribute.MAGENTA_TEXT());
    }

    public static Format CYAN_TEXT() {
        return new ColorFormat(Attribute.CYAN_TEXT());
    }

    public static Format WHITE_TEXT() {
        return new ColorFormat(Attribute.WHITE_TEXT());
    }

    // background colors

    public static Format BLACK_BACK() {
        return new ColorFormat(Attribute.BLACK_BACK());
    }

    public static Format RED_BACK() {
        return new ColorFormat(Attribute.RED_BACK());
    }

    public static Format GREEN_BACK() {
        return new ColorFormat(Attribute.GREEN_BACK());
    }

    public static Format YELLOW_BACK() {
        return new ColorFormat(Attribute.YELLOW_BACK());
    }

    public static Format BLUE_BACK() {
        return new ColorFormat(Attribute.BLUE_BACK());
    }

    public static Format MAGENTA_BACK() {
        return new ColorFormat(Attribute.MAGENTA_BACK());
    }

    public static Format CYAN_BACK() {
        return new ColorFormat(Attribute.CYAN_BACK());
    }

    public static Format WHITE_BACK() {
        return new ColorFormat(Attribute.WHITE_BACK());
    }

    // foreground bright colors

    public static Format BRIGHT_BLACK_TEXT() {
        return new ColorFormat(Attribute.BRIGHT_BLACK_TEXT());
    }

    public static Format BRIGHT_RED_TEXT() {
        return new ColorFormat(Attribute.BRIGHT_RED_TEXT());
    }

    public static Format BRIGHT_GREEN_TEXT() {
        return new ColorFormat(Attribute.BRIGHT_GREEN_TEXT());
    }

    public static Format BRIGHT_YELLOW_TEXT() {
        return new ColorFormat(Attribute.BRIGHT_YELLOW_TEXT());
    }

    public static Format BRIGHT_BLUE_TEXT() {
        return new ColorFormat(Attribute.BRIGHT_BLUE_TEXT());
    }

    public static Format BRIGHT_MAGENTA_TEXT() {
        return new ColorFormat(Attribute.BRIGHT_MAGENTA_TEXT());
    }

    public static Format BRIGHT_CYAN_TEXT() {
        return new ColorFormat(Attribute.BRIGHT_CYAN_TEXT());
    }

    public static Format BRIGHT_WHITE_TEXT() {
        return new ColorFormat(Attribute.BRIGHT_WHITE_TEXT());
    }

    // background bright colors

    public static Format BRIGHT_BLACK_BACK() {
        return new ColorFormat(Attribute.BRIGHT_BLACK_BACK());
    }

    public static Format BRIGHT_RED_BACK() {
        return new ColorFormat(Attribute.BRIGHT_RED_BACK());
    }

    public static Format BRIGHT_GREEN_BACK() {
        return new ColorFormat(Attribute.BRIGHT_GREEN_BACK());
    }

    public static Format BRIGHT_YELLOW_BACK() {
        return new ColorFormat(Attribute.BRIGHT_YELLOW_BACK());
    }

    public static Format BRIGHT_BLUE_BACK() {
        return new ColorFormat(Attribute.BRIGHT_BLUE_BACK());
    }

    public static Format BRIGHT_MAGENTA_BACK() {
        return new ColorFormat(Attribute.BRIGHT_MAGENTA_BACK());
    }

    public static Format BRIGHT_CYAN_BACK() {
        return new ColorFormat(Attribute.BRIGHT_CYAN_BACK());
    }

    public static Format BRIGHT_WHITE_BACK() {
        return new ColorFormat(Attribute.BRIGHT_WHITE_BACK());
    }

    // parameterized colors

    /**
     * @param colorNumber a number representing an 8-bit color (0-255).
     */
    public static Format TEXT_COLOR(int colorNumber) {
        return new ColorFormat(Attribute.TEXT_COLOR(colorNumber));
    }

    public static Format TEXT_COLOR(int r, int g, int b) {
        return new ColorFormat(Attribute.TEXT_COLOR(r, g, b));
    }

    /**
     * @param colorNumber a number representing an 8-bit color (0-255).
     */
    public static Format BACK_COLOR(int colorNumber) {
        return new ColorFormat(Attribute.BACK_COLOR(colorNumber));
    }

    public static Format BACK_COLOR(int r, int g, int b) {
        return new ColorFormat(Attribute.BACK_COLOR(r, g, b));
    }

    public static Format BLOCK_ALIGN_LEFT(int width) {
        return BlockFormat.builder(width).build();
    }

    public static Format BLOCK_ALIGN_CENTER(int width) {
        return BlockFormat.builder(width).withAlign(Align.CENTER).build();
    }

    public static Format BLOCK_ALIGN_RIGHT(int width) {
        return BlockFormat.builder(width).withAlign(Align.RIGHT).build();
    }

    public static Format BLOCK(int width, Align align, String abbreviationSign) {
        return new BlockFormat(width, align, abbreviationSign);
    }

}
