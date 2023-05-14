package de.arthurpicht.console.utils;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentIsEqualToOrGreaterThanZero;
import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class StringUtils {

    public static void fillUpLeft(StringBuilder stringBuilder, char fillChar, int minLength) {
        assertArgumentNotNull("stringBuilder", stringBuilder);


        if (stringBuilder.length() >= minLength) return;
        int nrOfCharsToFill = minLength - stringBuilder.length();
        stringBuilder.insert(0, String.valueOf(fillChar).repeat(Math.max(0, nrOfCharsToFill)));
    }

    /**
     * Füllt den spezifizierten String durch wiederholte Hinzufügung bis zur spezifizierten Länge auf.
     * Wenn die spezifizierte Länge bereits initial gegeben oder überschritten ist, wird der spezifizierten String
     * unverändert zurückgegeben.
     *
     * @param string
     * @param fillChar
     * @param minLength
     * @return@NotNull
     */
    public static String fillUpLeft(String string, char fillChar, int minLength) {
        assertArgumentNotNull("string", string);
        assertArgumentIsEqualToOrGreaterThanZero("minLength", minLength);

        StringBuilder stringBuilder = new StringBuilder(string);
        fillUpLeft(stringBuilder, fillChar, minLength);
        return stringBuilder.toString();
    }

    public static String fillUpLeftAndRight(String string, char fillChar, int minLength) {
        assertArgumentNotNull("string", string);
        assertArgumentIsEqualToOrGreaterThanZero("minLength", minLength);

        if (string.length() >= minLength) return string;
        int nrOfCharsToFill = minLength - string.length();

        if (!CalcUtils.isEven(nrOfCharsToFill)) {
            nrOfCharsToFill = nrOfCharsToFill - 1;
            string = string + fillChar;
            if (string.length() == minLength) return string;
        }
        return fillUpRightAndLeftSame(string, fillChar, nrOfCharsToFill);
    }

    private static String fillUpRightAndLeftSame(String string, char fillChar, int nrOfCharsToFill) {
        int nrOfCharsToFillHalf = nrOfCharsToFill / 2;
        StringBuilder stringBuilder = new StringBuilder(string);
        String paddingString = String.valueOf(fillChar).repeat(Math.max(0, nrOfCharsToFillHalf));
        stringBuilder.insert(0, paddingString);
        stringBuilder.append(paddingString);
        return stringBuilder.toString();
    }

    public static String overwriteRight(String string, String overlay) {
        assertArgumentNotNull("string", string);
        assertArgumentNotNull("overlay", overlay);
        if (overlay.length() > string.length())
            throw new IllegalArgumentException("Overlay is longer than base string.");
        if (string.isEmpty() || overlay.isEmpty()) return string;

        int index = string.length() - overlay.length();
        String firstChunk = string.substring(0, index);
        return firstChunk + overlay;
    }

}
