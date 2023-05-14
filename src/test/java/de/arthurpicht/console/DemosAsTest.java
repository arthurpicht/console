package de.arthurpicht.console;

import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.MessageBuilder;
import de.arthurpicht.console.message.format.BlockFormat;
import de.arthurpicht.console.message.format.Format;
import de.arthurpicht.console.testUtils.Logging;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemosAsTest {

    @Test
    @Order(1)
    public void test() {
        Console.print("Hello world!");
    }

    @Test
    @Order(2)
    public void colorTest() {
        Console.out(new MessageBuilder().addText("Hello world!", Format.RED_TEXT()).build());
    }

    @Test
    @Order(3)
    public void colorTestTwoMessages() {
        Console.out(new MessageBuilder()
                .addText("Hello red world!", Format.RED_TEXT())
                .addText(" 2nd text in normal again!")
                .build());
    }

    @Test
    @Order(4)
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
    @Order(5)
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

    @Test
    @Order(6)
    public void noLineFeed() {
        Console.out(new MessageBuilder()
                .addText("some text")
                .addText("...some other text...")
                .withNoLineFeed()
                .build());
        Console.println("some third text");
    }

    @Test
    @Order(7)
    public void simpleColoredLineWithLoggerDelegation() {
        Logging.initLoggerToConsole("CONSOLE");

        Console.init(new ConsoleConfigurationBuilder()
                .addLoggerDelegation("CONSOLE")
                .build());

        Console.println("red Text", Format.RED_TEXT());

        Console.initWithDefaults();
    }

    @Test
    @Order(8)
    public void deleteLine() {
        Console.print("Some text that will be deleted ... .... very long ..... !");
        Console.out(new MessageBuilder()
                .clearLine()
                .addText("This text should appear!")
                .build());
    }

}
