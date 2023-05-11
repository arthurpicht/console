package de.arthurpicht.console.stringComposer;

import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.Text;
import de.arthurpicht.console.message.format.BlockFormat;
import de.arthurpicht.console.message.format.Format;
import de.arthurpicht.console.utils.TestUtils;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class TextFormatterTest {

    @Test
    public void simple() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder().build();
        Text text = new Text("Hello world!");

        TextFormatter textFormatter = new TextFormatter(consoleConfiguration, text);
        String formattedText = textFormatter.getFormattedString();

        System.out.println(formattedText);
        assertEquals(12, formattedText.length());
        assertEquals(text.getTextString(), formattedText);
    }

    @Test
    public void redText() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder().build();
        Text text = new Text("Hello world!", Format.RED_TEXT());

        TextFormatter textFormatter = new TextFormatter(consoleConfiguration, text);
        String formattedText = textFormatter.getFormattedString();

        byte[] bytes = formattedText.getBytes(StandardCharsets.UTF_8);
        byte[] expectedBytes = {27, 91, 51, 49, 109, 72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 27, 91, 48, 109};

        assertArrayEquals(expectedBytes, bytes);

        System.out.println(formattedText);
        System.out.println(TestUtils.asByteInitializationLiteral(formattedText));
    }

    @Test
    public void redCenteredText() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder().build();
        Text text = new Text("Hello world!", Format.RED_TEXT(), Format.BLOCK_ALIGN_CENTER(25));

        TextFormatter textFormatter = new TextFormatter(consoleConfiguration, text);
        String formattedText = textFormatter.getFormattedString();

        byte[] bytes = formattedText.getBytes(StandardCharsets.UTF_8);
        byte[] expectedBytes = {32, 32, 32, 32, 32, 32, 27, 91, 51, 49, 109, 72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 27, 91, 48, 109, 32, 32, 32, 32, 32, 32, 32};
        assertArrayEquals(expectedBytes, bytes);

        System.out.println("|" + formattedText + "|");
        System.out.println(TestUtils.asByteInitializationLiteral(formattedText));
    }

    @Test
    public void redCenteredTextWithBrightYellowBackground() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder().build();
        Text text = new Text("Hello world!", Format.RED_TEXT(), Format.BRIGHT_YELLOW_BACK(), Format.BLOCK_ALIGN_CENTER(25));

        TextFormatter textFormatter = new TextFormatter(consoleConfiguration, text);
        String formattedText = textFormatter.getFormattedString();

        byte[] bytes = formattedText.getBytes(StandardCharsets.UTF_8);
        byte[] expectedBytes = {32, 32, 32, 32, 32, 32, 27, 91, 51, 49, 59, 49, 48, 51, 109, 72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 27, 91, 48, 109, 32, 32, 32, 32, 32, 32, 32};
        assertArrayEquals(expectedBytes, bytes);

        System.out.println("|" + formattedText + "|");
        System.out.println(TestUtils.asByteInitializationLiteral(formattedText));
    }

    @Test
    public void redCenteredTextWithBrightYellowBackgroundExpanded() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder().build();
        Text text = new Text(
                "Hello world!",
                Format.RED_TEXT(), Format.BRIGHT_YELLOW_BACK(),
                BlockFormat.builder(25)
                        .withAlign(BlockFormat.Align.CENTER)
                        .withExpandedTextEffects()
                        .build());

        TextFormatter textFormatter = new TextFormatter(consoleConfiguration, text);
        String formattedText = textFormatter.getFormattedString();

        byte[] bytes = formattedText.getBytes(StandardCharsets.UTF_8);
        byte[] expectedBytes = {27, 91, 51, 49, 59, 49, 48, 51, 109, 32, 32, 32, 32, 32, 32, 72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 32, 32, 32, 32, 32, 32, 32, 27, 91, 48, 109};
        assertArrayEquals(expectedBytes, bytes);

        System.out.println("|" + formattedText + "|");
        System.out.println(TestUtils.asByteInitializationLiteral(formattedText));
    }

    @Test
    public void redRightAlignedTextWithBrightYellowBackgroundExpanded() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder().build();
        Text text = new Text(
                "Hello world!",
                Format.RED_TEXT(), Format.BRIGHT_YELLOW_BACK(),
                BlockFormat.builder(25)
                        .withAlign(BlockFormat.Align.RIGHT)
                        .withExpandedTextEffects()
                        .build());

        TextFormatter textFormatter = new TextFormatter(consoleConfiguration, text);
        String formattedText = textFormatter.getFormattedString();

        byte[] bytes = formattedText.getBytes(StandardCharsets.UTF_8);
        byte[] expectedBytes = {27, 91, 51, 49, 59, 49, 48, 51, 109, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 27, 91, 48, 109};
        assertArrayEquals(expectedBytes, bytes);

        System.out.println("|" + formattedText + "|");
        System.out.println(TestUtils.asByteInitializationLiteral(formattedText));
    }

    @Test
    public void redLeftAlignedTextWithBrightYellowBackgroundExpanded() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder().build();
        Text text = new Text(
                "Hello world!",
                Format.RED_TEXT(), Format.BRIGHT_YELLOW_BACK(),
                BlockFormat.builder(25)
                        .withAlign(BlockFormat.Align.LEFT)
                        .withExpandedTextEffects()
                        .build());

        TextFormatter textFormatter = new TextFormatter(consoleConfiguration, text);
        String formattedText = textFormatter.getFormattedString();

        byte[] bytes = formattedText.getBytes(StandardCharsets.UTF_8);
        byte[] expectedBytes = {27, 91, 51, 49, 59, 49, 48, 51, 109, 72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 27, 91, 48, 109};
        assertArrayEquals(expectedBytes, bytes);

        System.out.println("|" + formattedText + "|");
        System.out.println(TestUtils.asByteInitializationLiteral(formattedText));
    }


}