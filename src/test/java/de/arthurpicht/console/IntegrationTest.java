package de.arthurpicht.console;

import de.arthurpicht.console.message.MessageBuilder;
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
    public void colorTest2Messages() {
        Console.out(new MessageBuilder()
                .addText("Hello red world!", Format.RED_TEXT())
                .addText(" 2nd text in normal again!")
                .build());
    }


}
