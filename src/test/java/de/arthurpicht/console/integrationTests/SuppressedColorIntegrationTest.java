package de.arthurpicht.console.integrationTests;

import de.arthurpicht.console.Console;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.message.MessageBuilder;
import de.arthurpicht.console.message.format.BlockFormat;
import de.arthurpicht.console.message.format.Format;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuppressedColorIntegrationTest {

    @Test
    public void suppressedColor() {
        // withSuppressedColor must suppress color but must not suppress blocks

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        ByteArrayOutputStream byteArrayOutputStreamError = new ByteArrayOutputStream();
        PrintStream printStreamError = new PrintStream(byteArrayOutputStreamError);

        Console.configure(
                new ConsoleConfigurationBuilder()
                        .withStandardOut(printStream)
                        .withStandardErrorOut(printStreamError)
                        .withSuppressedColors()
                        .build()
        );
        Console.error("error message", Format.RED_TEXT(), Format.BOLD());
        Console.println("Line 1", Format.RED_TEXT(), Format.BOLD());
        Console.out(new MessageBuilder()
                .addText("first text", Format.RED_TEXT())
                .addText("block",
                        Format.RED_TEXT(), Format.BRIGHT_YELLOW_BACK(),
                        new BlockFormat.Builder()
                                .withWidth(25)
                                .withAlign(BlockFormat.Align.CENTER)
                                .withExpandedTextEffects()
                                .build())
                .addText("second text", Format.BRIGHT_YELLOW_BACK())
                .build());
        Console.println(Level.VERBOSE, "Line 2", Format.RED_TEXT());
        Console.println(Level.VERY_VERBOSE,"Line 3", Format.RED_TEXT());
        Console.println(Level.VERY_VERY_VERBOSE,"Line 4", Format.RED_TEXT());
        Console.print(Level.NORMAL, "Line 5", Format.RED_TEXT());

        String consoleOutput = byteArrayOutputStream.toString();
        assertEquals("Line 1\nfirst text          block          second text\nLine 5", consoleOutput);

        String consoleOutputError = byteArrayOutputStreamError.toString();
        assertEquals("error message\n", consoleOutputError);
    }

}
