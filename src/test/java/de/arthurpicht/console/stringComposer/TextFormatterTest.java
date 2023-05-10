package de.arthurpicht.console.stringComposer;

import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.Text;
import de.arthurpicht.console.message.format.Format;
import de.arthurpicht.console.utils.TestUtils;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class TextFormatterTest {

    @Test
    public void simple() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder().build();
        TextFormatter textFormatter = new TextFormatter(consoleConfiguration);

        Text text = new Text("Hello world!");
        String formattedText = textFormatter.format(text);

        System.out.println(formattedText);
        assertEquals(12, formattedText.length());
        assertEquals(text.getTextString(), formattedText);
    }

    @Test
    public void redText() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder().build();
        TextFormatter textFormatter = new TextFormatter(consoleConfiguration);

        Text text = new Text("Hello world!", Format.RED_TEXT());
        String formattedText = textFormatter.format(text);

        byte[] bytes = formattedText.getBytes(StandardCharsets.UTF_8);
        byte[] expectedBytes = {27, 91, 51, 49, 109, 72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33, 27, 91, 48, 109};

        assertArrayEquals(expectedBytes, bytes);

        System.out.println(formattedText);
        System.out.println(TestUtils.asByteInitializationLiteral(formattedText));
    }

}