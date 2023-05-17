package de.arthurpicht.console.processor;

import de.arthurpicht.console.message.Text;
import de.arthurpicht.console.message.format.BlockFormat;
import de.arthurpicht.console.message.format.Format;
import de.arthurpicht.console.utils.TestUtils;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TextFormatterTest {

    @Test
    public void simple() {
        Text text = new Text("Hello world!");

        TextFormatter textFormatter = new TextFormatter(text);
        String formattedText = textFormatter.getFormattedString(true);

        System.out.println(formattedText);
        assertEquals(12, formattedText.length());
        assertEquals(text.getTextString(), formattedText);
    }

    @Test
    public void redText() {
        Text text = new Text("Hello world!", Format.RED_TEXT());

        TextFormatter textFormatter = new TextFormatter(text);
        String formattedText = textFormatter.getFormattedString(true);

        byte[] bytes = formattedText.getBytes(StandardCharsets.UTF_8);
        byte[] expectedBytes = {27, 91, 51, 49, 109, 72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 27, 91, 48, 109};

        assertArrayEquals(expectedBytes, bytes);

        System.out.println(formattedText);
        System.out.println(TestUtils.asByteInitializationLiteral(formattedText));
    }

    @Test
    public void redCenteredText() {
        Text text = new Text("Hello world!", Format.RED_TEXT(), Format.BLOCK_ALIGN_CENTER(25));

        TextFormatter textFormatter = new TextFormatter(text);
        String formattedText = textFormatter.getFormattedString(true);

        byte[] bytes = formattedText.getBytes(StandardCharsets.UTF_8);
        byte[] expectedBytes = {32, 32, 32, 32, 32, 32, 27, 91, 51, 49, 109, 72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 27, 91, 48, 109, 32, 32, 32, 32, 32, 32, 32};
        assertArrayEquals(expectedBytes, bytes);

        System.out.println("|" + formattedText + "|");
        System.out.println(TestUtils.asByteInitializationLiteral(formattedText));
    }

    @Test
    public void redCenteredTextWithBrightYellowBackground() {
        Text text = new Text("Hello world!", Format.RED_TEXT(), Format.BRIGHT_YELLOW_BACK(), Format.BLOCK_ALIGN_CENTER(25));

        TextFormatter textFormatter = new TextFormatter(text);
        String formattedText = textFormatter.getFormattedString(true);

        byte[] bytes = formattedText.getBytes(StandardCharsets.UTF_8);
        byte[] expectedBytes = {32, 32, 32, 32, 32, 32, 27, 91, 51, 49, 59, 49, 48, 51, 109, 72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 27, 91, 48, 109, 32, 32, 32, 32, 32, 32, 32};
        assertArrayEquals(expectedBytes, bytes);

        System.out.println("|" + formattedText + "|");
        System.out.println(TestUtils.asByteInitializationLiteral(formattedText));
    }

    @Test
    public void redCenteredTextWithBrightYellowBackgroundExpanded() {
        Text text = new Text(
                "Hello world!",
                Format.RED_TEXT(), Format.BRIGHT_YELLOW_BACK(),
                BlockFormat.builder(25)
                        .withAlign(BlockFormat.Align.CENTER)
                        .withExpandedTextEffects()
                        .build());

        TextFormatter textFormatter = new TextFormatter(text);
        String formattedText = textFormatter.getFormattedString(true);

        byte[] bytes = formattedText.getBytes(StandardCharsets.UTF_8);
        byte[] expectedBytes = {27, 91, 51, 49, 59, 49, 48, 51, 109, 32, 32, 32, 32, 32, 32, 72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 32, 32, 32, 32, 32, 32, 32, 27, 91, 48, 109};
        assertArrayEquals(expectedBytes, bytes);

        System.out.println("|" + formattedText + "|");
        System.out.println(TestUtils.asByteInitializationLiteral(formattedText));
    }

    @Test
    public void redRightAlignedTextWithBrightYellowBackgroundExpanded() {
        Text text = new Text(
                "Hello world!",
                Format.RED_TEXT(), Format.BRIGHT_YELLOW_BACK(),
                BlockFormat.builder(25)
                        .withAlign(BlockFormat.Align.RIGHT)
                        .withExpandedTextEffects()
                        .build());

        TextFormatter textFormatter = new TextFormatter(text);
        String formattedText = textFormatter.getFormattedString(true);

        byte[] bytes = formattedText.getBytes(StandardCharsets.UTF_8);
        byte[] expectedBytes = {27, 91, 51, 49, 59, 49, 48, 51, 109, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 27, 91, 48, 109};
        assertArrayEquals(expectedBytes, bytes);

        System.out.println("|" + formattedText + "|");
        System.out.println(TestUtils.asByteInitializationLiteral(formattedText));
    }

    @Test
    public void redLeftAlignedTextWithBrightYellowBackgroundExpanded() {
        Text text = new Text(
                "Hello world!",
                Format.RED_TEXT(), Format.BRIGHT_YELLOW_BACK(),
                BlockFormat.builder(25)
                        .withAlign(BlockFormat.Align.LEFT)
                        .withExpandedTextEffects()
                        .build());

        TextFormatter textFormatter = new TextFormatter(text);
        String formattedText = textFormatter.getFormattedString(true);

        byte[] bytes = formattedText.getBytes(StandardCharsets.UTF_8);
        byte[] expectedBytes = {27, 91, 51, 49, 59, 49, 48, 51, 109, 72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 27, 91, 48, 109};
        assertArrayEquals(expectedBytes, bytes);

        System.out.println("|" + formattedText + "|");
        System.out.println(TestUtils.asByteInitializationLiteral(formattedText));
    }

    @Test
    public void blockOverflowLimit() {
        Text text = new Text(
                "This is an overflowing text. It will exceed the specified width.",
                Format.BLOCK(20, BlockFormat.Align.LEFT, false, BlockFormat.OverflowStrategy.LIMIT, "~")
        );

        TextFormatter textFormatter = new TextFormatter(text);
        String string = textFormatter.getFormattedString(false);

        assertEquals("This is an overflowi", string);
    }

    @Test
    public void blockOverflowAbbreviation() {
        Text text = new Text(
                "This is an overflowing text. It will exceed the specified width.",
                Format.BLOCK(20, BlockFormat.Align.LEFT, false, BlockFormat.OverflowStrategy.ABBREVIATE, "~")
        );

        TextFormatter textFormatter = new TextFormatter(text);
        String string = textFormatter.getFormattedString(false);

        assertEquals("This is an overflow~", string);
    }

    @Test
    public void blockOverflowExpand() {
        Text text = new Text(
                "This is an overflowing text. It will exceed the specified width.",
                Format.BLOCK(20, BlockFormat.Align.LEFT, false, BlockFormat.OverflowStrategy.EXPAND, "~")
        );

        TextFormatter textFormatter = new TextFormatter(text);
        String string = textFormatter.getFormattedString(false);

        assertEquals("This is an overflowing text. It will exceed the specified width.", string);
    }

}