package de.arthurpicht.console.integrationTests;

import de.arthurpicht.console.Console;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MutingIntegrationTest {

    @BeforeEach
    public void resetConfiguration() {
        Console.configureWithDefaults();
    }

    @Test
    public void muted() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        ByteArrayOutputStream byteArrayOutputStreamError = new ByteArrayOutputStream();
        PrintStream printStreamError = new PrintStream(byteArrayOutputStreamError);

        Console.configure(
                new ConsoleConfigurationBuilder()
                        .withStandardOut(printStream)
                        .withStandardErrorOut(printStreamError)
                        .withMutedOutput()
                        .build()
        );
        Console.error("error message");
        Console.println("Line 1");
        Console.println(Level.VERBOSE, "Line 2");
        Console.println(Level.VERY_VERBOSE,"Line 3");
        Console.println(Level.VERY_VERY_VERBOSE,"Line 4");
        Console.print(Level.NORMAL, "Line 5");

        String consoleOutput = byteArrayOutputStream.toString();
        assertEquals("", consoleOutput);

        String consoleOutputError = byteArrayOutputStreamError.toString();
        assertEquals("", consoleOutputError);
    }

}
