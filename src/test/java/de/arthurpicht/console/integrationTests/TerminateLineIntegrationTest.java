package de.arthurpicht.console.integrationTests;

import de.arthurpicht.console.Console;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.MessageBuilder;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TerminateLineIntegrationTest {

    @Test
    public void terminatePreviousLine() {
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

        Console.println("First line.");
        Console.print("A");
        Console.println("B");
        Console.print("Second line w/o lf.");
        Console.out(new MessageBuilder()
                .terminatePreviousLine()
                .addText("some text here")
                .build()
        );
        Console.println("next line");

        String consoleOutput = byteArrayOutputStream.toString();
        System.out.println(consoleOutput);
        assertEquals("""
                First line.
                AB
                Second line w/o lf.
                some text here
                next line
                """, consoleOutput);

        String consoleOutputError = byteArrayOutputStreamError.toString();
        assertEquals("", consoleOutputError);
    }

    @Test
    public void noOutputBeforeTerminatePreviousLine() {
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

        Console.out(new MessageBuilder()
                .terminatePreviousLine()
                .addText("some text here")
                .build()
        );
        Console.println("next line");

        String consoleOutput = byteArrayOutputStream.toString();
        System.out.println(consoleOutput);
        assertEquals("""
                some text here
                next line
                """, consoleOutput);

        String consoleOutputError = byteArrayOutputStreamError.toString();
        assertEquals("", consoleOutputError);
    }

    @Test
    public void terminatePreviousLineError() {
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

        Console.println("First line.");
        Console.print("A");
        Console.println("B");
        Console.print("Second line w/o lf.");
        Console.out(new MessageBuilder()
                .terminatePreviousLine()
                .addText("some error message here")
                .toErrorStream()
                .build()
        );
        Console.println("next line");

        String consoleOutput = byteArrayOutputStream.toString();
        System.out.println(consoleOutput);
        assertEquals("""
                First line.
                AB
                Second line w/o lf.
                next line
                """, consoleOutput);

        String consoleOutputError = byteArrayOutputStreamError.toString();
        assertEquals("""
                some error message here
                """, consoleOutputError);
    }

}
