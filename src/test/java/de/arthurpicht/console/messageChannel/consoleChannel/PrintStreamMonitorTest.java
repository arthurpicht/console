package de.arthurpicht.console.messageChannel.consoleChannel;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PrintStreamMonitorTest {

    @Test
    public void plausibilityCheck() {
        PrintStreamMonitor printStreamMonitor = new PrintStreamMonitor(System.out, System.err);
        PrintStream out = printStreamMonitor.getPrintStreamStdOut();

        out.println("Some text");
        assertTrue(printStreamMonitor.lastOutputEndsWithNewline());

        out.print("Some other text");
        assertFalse(printStreamMonitor.lastOutputEndsWithNewline());
    }

    @Test
    public void emptyMonitor() {
        PrintStreamMonitor printStreamMonitor = new PrintStreamMonitor(System.out, System.err);

        assertTrue(printStreamMonitor.lastOutputEndsWithNewline());
    }

}