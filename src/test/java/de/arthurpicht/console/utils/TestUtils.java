package de.arthurpicht.console.utils;

import de.arthurpicht.utils.core.array.ArrayUtils;
import de.arthurpicht.utils.core.strings.Strings;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUtils {

    public static String asByteInitializationLiteral(String string) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        List<Byte> byteList = ArrayUtils.asList(bytes);
        return Strings.listing(byteList, ", ", "{", "}");
    }

    public static boolean beginsWithTimestamp(String string) {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.*");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

}
