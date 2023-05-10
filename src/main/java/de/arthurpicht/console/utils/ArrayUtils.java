package de.arthurpicht.console.utils;

import java.util.Arrays;
import java.util.List;

public class ArrayUtils {

    public static List<Byte> asList(byte[] bytes) {
        Byte[] wrappedArray = Boxing.wrap(bytes);
        return Arrays.asList(wrappedArray);
    }

}
