package de.arthurpicht.console.integrationTests;

import de.arthurpicht.console.Console;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.MessageBuilder;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClearLineIntegrationTest {

    @Test
    public void clearLine() {
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

        Console.print("overridden");
        Console.out(new MessageBuilder()
                .addText("some text here")
                .clearLine()
                .build()
        );
        Console.println("next line");

        String consoleOutput = byteArrayOutputStream.toString();
        System.out.println(consoleOutput);
        assertEquals("""
                overridden\u001B[2K\rsome text here
                next line
                """, consoleOutput);

        String consoleOutputError = byteArrayOutputStreamError.toString();
        assertEquals("", consoleOutputError);
    }

    @Test
    public void clearLinePlain() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        ByteArrayOutputStream byteArrayOutputStreamError = new ByteArrayOutputStream();
        PrintStream printStreamError = new PrintStream(byteArrayOutputStreamError);

        Console.configure(
                new ConsoleConfigurationBuilder()
                        .withStandardOut(printStream)
                        .withStandardErrorOut(printStreamError)
                        .withPlainOutput()
                        .build()
        );

        Console.print("overridden");
        Console.out(new MessageBuilder()
                .addText("some text here")
                .clearLine()
                .build()
        );
        Console.println("next line");

        String consoleOutput = byteArrayOutputStream.toString();
        System.out.println(consoleOutput);
        assertEquals("""
                overriddensome text here
                next line
                """, consoleOutput);

        String consoleOutputError = byteArrayOutputStreamError.toString();
        assertEquals("", consoleOutputError);
    }

}
