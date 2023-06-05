package de.arthurpicht.console.utils;

import de.arthurpicht.utils.core.array.ArrayUtils;
import de.arthurpicht.utils.core.strings.Strings;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestUtils {

    public static String asByteInitializationLiteral(String string) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        List<Byte> byteList = ArrayUtils.asList(bytes);
        return Strings.listing(byteList, ", ", "{", "}");
    }

}
