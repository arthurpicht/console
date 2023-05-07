package de.arthurpicht.console.message;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LevelTest {

    @Test
    public void bothNormal() {
        Level messageLevel = Level.NORMAL;
        Level outputLevel = Level.NORMAL;
        Assertions.assertTrue(Level.applies(messageLevel, outputLevel));
    }

    @Test
    public void normalOnVerbose() {
        Level messageLevel = Level.NORMAL;
        Level outputLevel = Level.VERBOSE;
        Assertions.assertTrue(Level.applies(messageLevel, outputLevel));
    }

    @Test
    public void normalOnVeryVerbose() {
        Level messageLevel = Level.NORMAL;
        Level outputLevel = Level.VERY_VERBOSE;
        Assertions.assertTrue(Level.applies(messageLevel, outputLevel));
    }

    @Test
    public void verboseOnNormal() {
        Level messageLevel = Level.VERBOSE;
        Level outputLevel = Level.NORMAL;
        Assertions.assertFalse(Level.applies(messageLevel, outputLevel));
    }

    @Test
    public void veryVerboseOnNormal() {
        Level messageLevel = Level.VERY_VERBOSE;
        Level outputLevel = Level.NORMAL;
        Assertions.assertFalse(Level.applies(messageLevel, outputLevel));
    }


}
