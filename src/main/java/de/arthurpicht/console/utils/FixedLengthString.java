package de.arthurpicht.console.utils;

import de.arthurpicht.utils.core.assertion.MethodPreconditions;
import de.arthurpicht.utils.core.strings.Strings;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class FixedLengthString {

    public enum Align { RIGHT, LEFT, CENTER }

    private final int length;
    private final Align align;
    private final String abbreviationSigns;

    public FixedLengthString(int length, Align align, String abbreviationSigns) {
        this.length = length;
        this.align = align;
        this.abbreviationSigns = abbreviationSigns;
    }

    public String create(String string) {
        assertArgumentNotNull("string", string);

        string = Strings.limit(string, this.length);

        if (this.align == Align.LEFT) {
            string = Strings.fillUpAfter(string, ' ', this.length);
        } else if (this.align == Align.RIGHT) {
            string = StringUtils.fillUpLeft(string, ' ', this.length);
        }

        // TODO

        return string;
    }

}
