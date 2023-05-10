package de.arthurpicht.console.utils;

import de.arthurpicht.utils.core.strings.Strings;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentIsEqualToOrGreaterThanZero;
import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class StringUtils {

    public static boolean isControlCharacter(char c) {
        return Character.getType(c) == Character.CONTROL;
    }

    public static int getLengthNonControlCharacters(String string) {
        int length = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (!isControlCharacter(c)) length++;
        }
        return length;
    }

    public static void fillUpLeft(StringBuilder stringBuilder, char fillChar, int minLength) {
        assertArgumentNotNull("stringBuilder", stringBuilder);

        if (stringBuilder.length() >= minLength) return;
        int nrOfCharsToFill = minLength - stringBuilder.length();
        stringBuilder.insert(0, String.valueOf(fillChar).repeat(Math.max(0, nrOfCharsToFill)));
    }

    /**
     * Füllt den spez. String durch wiederholte Hinzufügung bis zur spez. Länge auf.
     * Wenn die spez. Länge bereits initial gegeben oder überschritten ist, wird der spez. String
     * unverändert zurück gegeben.
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

        if (CalcUtils.isEven(nrOfCharsToFill)) {
            return fillUpRightAndLeftSame(string, fillChar, nrOfCharsToFill);
        } else {
            nrOfCharsToFill = nrOfCharsToFill - 1;
            string = string + fillChar;
            if (string.length() == minLength) return string;
            return fillUpRightAndLeftSame(string, fillChar, nrOfCharsToFill);
        }
    }

    private static String fillUpRightAndLeftSame(String string, char fillChar, int nrOfCharsToFill) {
        int nrOfCharsToFillHalf = nrOfCharsToFill / 2;
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.insert(0, String.valueOf(fillChar).repeat(Math.max(0, nrOfCharsToFillHalf)));
        stringBuilder.append(String.valueOf(fillChar).repeat(Math.max(0, nrOfCharsToFillHalf)));
        return stringBuilder.toString();
    }

}
