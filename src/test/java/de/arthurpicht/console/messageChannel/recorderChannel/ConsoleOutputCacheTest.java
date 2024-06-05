package de.arthurpicht.console.messageChannel.recorderChannel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ConsoleOutputCacheTest {

    @Test
    void println() {
        ConsoleOutputCache consoleOutputCache = new ConsoleOutputCache();
        consoleOutputCache.println("line1");
        consoleOutputCache.println("line2");

        List<String> lines = consoleOutputCache.getCachedOutput();
        Assertions.assertEquals(2, lines.size());
        Assertions.assertEquals("line1", lines.get(0));
        Assertions.assertEquals("line2", lines.get(1));
    }

    @Test
    void print() {
        ConsoleOutputCache consoleOutputCache = new ConsoleOutputCache();
        consoleOutputCache.print("message1");
        consoleOutputCache.print("message2");

        List<String> lines = consoleOutputCache.getCachedOutput();
        Assertions.assertEquals(1, lines.size());
        Assertions.assertEquals("message1message2", lines.get(0));
    }

    @Test
    void printPrintln() {
        ConsoleOutputCache consoleOutputCache = new ConsoleOutputCache();
        consoleOutputCache.print("message1");
        consoleOutputCache.println("message2");
        consoleOutputCache.print("message3");

        List<String> lines = consoleOutputCache.getCachedOutput();
        Assertions.assertEquals(2, lines.size());
        Assertions.assertEquals("message1message2", lines.get(0));
        Assertions.assertEquals("message3", lines.get(1));
    }

    @Test
    void clearLine1() {
        ConsoleOutputCache consoleOutputCache = new ConsoleOutputCache();
        consoleOutputCache.println("message1");
        consoleOutputCache.print("message2");
        consoleOutputCache.clearLine();

        List<String> lines = consoleOutputCache.getCachedOutput();
        Assertions.assertEquals(1, lines.size());
        Assertions.assertEquals("message1", lines.get(0));
    }

    @Test
    void clearLine2() {
        ConsoleOutputCache consoleOutputCache = new ConsoleOutputCache();
        consoleOutputCache.println("message1");
        consoleOutputCache.clearLine();

        List<String> lines = consoleOutputCache.getCachedOutput();
        Assertions.assertEquals(1, lines.size());
        Assertions.assertEquals("message1", lines.get(0));
    }

    @Test
    void clear() {
        ConsoleOutputCache consoleOutputCache = new ConsoleOutputCache();
        consoleOutputCache.println("message1");
        consoleOutputCache.clear();

        List<String> lines = consoleOutputCache.getCachedOutput();
        Assertions.assertEquals(0, lines.size());
    }

}