package de.arthurpicht.console.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    public void fillUpLeft() {
        String string = "123";
        String processed = StringUtils.fillUpLeft(string, ' ', 5);
        assertEquals("  123", processed);
    }

    @Test
    public void fillUpLeftLengthOK() {
        String string = "123";
        String processed = StringUtils.fillUpLeft(string, ' ', 3);
        assertEquals("123", processed);
    }

    @Test
    public void fillUpLeftNoActionNeeded() {
        String string = "12345";
        String processed = StringUtils.fillUpLeft(string, ' ', 5);
        assertEquals("12345", processed);
    }

    @Test
    public void fillUpLeftAndRight() {
        String string = "123";
        String processed = StringUtils.fillUpLeftAndRight(string, ' ', 5);
        assertEquals(" 123 ", processed);
    }

    @Test
    public void fillUpLeftAndRightMore() {
        String string = "1234";
        String processed = StringUtils.fillUpLeftAndRight(string, ' ', 7);
        assertEquals(" 1234  ", processed);
    }

    @Test
    public void fillUpLeftAndRightMoreEqual() {
        String string = "1234";
        String processed = StringUtils.fillUpLeftAndRight(string, ' ', 8);
        assertEquals("  1234  ", processed);
    }

    @Test
    public void fillUpLeftAndRightNoAction() {
        String string = "1234";
        String processed = StringUtils.fillUpLeftAndRight(string, ' ', 4);
        assertEquals("1234", processed);
    }

    @Test
    public void fillUpLeftAndRightEmptyString() {
        String string = "";
        String processed = StringUtils.fillUpLeftAndRight(string, ' ', 4);
        assertEquals("    ", processed);
    }

    @Test
    public void fillUpLeftAndRightNoActionTooLong() {
        String string = "1234";
        String processed = StringUtils.fillUpLeftAndRight(string, ' ', 3);
        assertEquals("1234", processed);
    }



}