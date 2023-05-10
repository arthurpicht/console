package de.arthurpicht.console.utils;

import de.arthurpicht.utils.core.assertion.MethodPreconditions;

import java.util.Arrays;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class Boxing {

    public static Integer[] wrap(int[] intArray) {
        assertArgumentNotNull("intArray", intArray);
        return Arrays.stream(intArray)
                .boxed()
                .toArray(Integer[]::new);
    }

    public static int[] unwrap(Integer[] intArray) {
        assertArgumentNotNull("intArray", intArray);
        return Arrays.stream(intArray)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public static Long[] wrap(long[] longArray) {
        assertArgumentNotNull("longArray", longArray);
        return Arrays.stream(longArray)
                .boxed()
                .toArray(Long[]::new);
    }

    public static long[] unwrap(Long[] longArray) {
        assertArgumentNotNull("longArray", longArray);
        return Arrays.stream(longArray)
                .mapToLong(Long::longValue)
                .toArray();
    }

    public static Double[] wrap(double[] doubleArray) {
        assertArgumentNotNull("doubleArray", doubleArray);
        return Arrays.stream(doubleArray)
                .boxed()
                .toArray(Double[]::new);
    }

    public static double[] unwrap(Double[] doubleArray) {
        assertArgumentNotNull("doubleArray", doubleArray);
        return Arrays.stream(doubleArray)
                .mapToDouble(Double::doubleValue)
                .toArray();
    }

    public static Byte[] wrap(byte[] byteArray) {
        assertArgumentNotNull("byteArray", byteArray);
        final Byte[] result = new Byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            result[i] = byteArray[i];
        }
        return result;
    }

}
