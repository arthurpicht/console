package de.arthurpicht.console.integrationTests;

import de.arthurpicht.console.Console;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelIntegrationTest {

    @BeforeEach
    public void resetConfiguration() {
        Console.configureWithDefaults();
    }

    @Test
    public void normal() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        ByteArrayOutputStream byteArrayOutputStreamError = new ByteArrayOutputStream();
        PrintStream printStreamError = new PrintStream(byteArrayOutputStreamError);

        Console.configure(
                new ConsoleConfigurationBuilder()
                        .withStandardOut(printStream)
                        .withStandardErrorOut(printStreamError)
                        .build()
        );
        Console.println("Line 1");
        Console.println(Level.VERBOSE, "Line 2");
        Console.println(Level.VERY_VERBOSE,"Line 3");
        Console.error("error message");
        Console.println(Level.VERY_VERY_VERBOSE,"Line 4");
        Console.print(Level.NORMAL, "Line 5");

        String consoleOutput = byteArrayOutputStream.toString();
        assertEquals("Line 1\nLine 5", consoleOutput);
        String consoleError = byteArrayOutputStreamError.toString();
        assertEquals("error message\n", consoleError);
    }

    @Test
    public void verbose() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        ByteArrayOutputStream byteArrayOutputStreamError = new ByteArrayOutputStream();
        PrintStream printStreamError = new PrintStream(byteArrayOutputStreamError);

        Console.configure(
                new ConsoleConfigurationBuilder()
                        .withStandardOut(printStream)
                        .withStandardErrorOut(printStreamError)
                        .withLevel(Level.VERBOSE)
                        .build()
        );
        Console.println("Line 1");
        Console.println(Level.VERBOSE, "Line 2");
        Console.println(Level.VERY_VERBOSE,"Line 3");
        Console.error("error message");
        Console.println(Level.VERY_VERY_VERBOSE,"Line 4");
        Console.print(Level.NORMAL, "Line 5");

        String consoleOutput = byteArrayOutputStream.toString();
        assertEquals("Line 1\nLine 2\nLine 5", consoleOutput);
        String consoleError = byteArrayOutputStreamError.toString();
        assertEquals("error message\n", consoleError);
    }

}
