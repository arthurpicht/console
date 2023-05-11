package de.arthurpicht.console;

import de.arthurpicht.console.message.MessageBuilder;
import de.arthurpicht.console.message.format.BlockFormat;
import de.arthurpicht.console.message.format.Format;
import org.junit.jupiter.api.Test;

public class IntegrationTest {

    @Test
    public void test() {
        Console.print("Hello world!");
    }

    @Test
    public void colorTest() {
        Console.out(new MessageBuilder().addText("Hello world!", Format.RED_TEXT()).build());
    }

    @Test
    public void colorTestTwoMessages() {
        Console.out(new MessageBuilder()
                .addText("Hello red world!", Format.RED_TEXT())
                .addText(" 2nd text in normal again!")
                .build());
    }

    @Test
    public void colorBlockTestTwoMessages() {
        Console.out(new MessageBuilder()
                .addText("First text.", Format.RED_TEXT())
                .addText("Hello world!",
                        Format.RED_TEXT(), Format.BRIGHT_YELLOW_BACK(),
                        BlockFormat.builder(25)
                                .withAlign(BlockFormat.Align.CENTER)
                                .withExpandedTextEffects()
                                .build())
                .addText("normal again")
                .build());
    }

    @Test
    public void abbreviation() {
        Console.out(new MessageBuilder()
                .addText("|", Format.BOLD())
                .addText("1234567890abcdefghijklmnopqrstuvwxyz",
                        Format.RED_TEXT(), Format.BRIGHT_YELLOW_BACK(),
                        BlockFormat.builder(10)
                                .withAbbreviationSign("...")
                                .build())
                .addText("|", Format.BOLD())
                .build());
    }

}
