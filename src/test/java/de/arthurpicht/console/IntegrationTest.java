package de.arthurpicht.console;

import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.format.Format;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    @BeforeEach
    public void resetConfiguration() {
        Console.configureWithDefaults();
    }

    @Test
    public void helloWorld() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);

        Console.configure(
                new ConsoleConfigurationBuilder()
                        .withStandardOut(printStream)
                        .build()
        );
        Console.println("Hello World!");

        String consoleOutput = byteArrayOutputStream.toString();
        assertEquals("Hello World!\n", consoleOutput);
    }

    @Test
    public void red() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);

        Console.configure(
                new ConsoleConfigurationBuilder()
                        .withStandardOut(printStream)
                        .build()
        );
        Console.println("Hello World!", Format.RED_TEXT());

        String consoleOutput = byteArrayOutputStream.toString();
        assertEquals("\u001B[31mHello World!\u001B[0m\n", consoleOutput);
    }

    @Test
    public void yellowBackgroundBold() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);

        Console.configure(
                new ConsoleConfigurationBuilder()
                        .withStandardOut(printStream)
                        .build()
        );
        Console.println("Hello World!", Format.YELLOW_BACK(), Format.BOLD());

        String consoleOutput = byteArrayOutputStream.toString();
        assertEquals("\u001B[43;1mHello World!\u001B[0m\n", consoleOutput);
    }

}
