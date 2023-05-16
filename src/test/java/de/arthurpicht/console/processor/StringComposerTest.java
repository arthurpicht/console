package de.arthurpicht.console.processor;

import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.message.MessageBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringComposerTest {

    @Test
    void compose_no_indent() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder().build();
        Message message = new MessageBuilder()
                .addText("Hello world!")
                .build();

        StringComposer stringComposer = new StringComposer(consoleConfiguration);
        String string = stringComposer.compose(message, StringComposer.Target.CONSOLE);

        assertEquals("Hello world!", string);
    }

    @Test
    void compose_indent() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder().build();
        Message message = new MessageBuilder()
                .withIndentation(2)
                .addText("Hello world!")
                .build();

        StringComposer stringComposer = new StringComposer(consoleConfiguration);
        String string = stringComposer.compose(message, StringComposer.Target.CONSOLE);

        assertEquals("  Hello world!", string);
    }

}