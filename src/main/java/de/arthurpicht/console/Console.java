package de.arthurpicht.console;

import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.message.MessageBuilder;
import de.arthurpicht.console.stringComposer.StringComposer;

import java.util.Optional;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class Console {

    private static ConsoleConfiguration consoleConfiguration = null;
    private static StringComposer stringComposer;

    public static void init(ConsoleConfiguration consoleConfiguration) {
        Console.consoleConfiguration = consoleConfiguration;
        Console.stringComposer = new StringComposer(consoleConfiguration);
        // TODO
    }

    public static void print(String messageString) {
        assertArgumentNotNull("messageString", messageString);
        assureIsInitialized();
        Message message = new MessageBuilder()
                .addText(messageString)
                .withNoLineFeed()
                .build();
        process(message);
    }

    public static void println(String messageString) {
        assertArgumentNotNull("messageString", messageString);
        assureIsInitialized();
        Message message = new MessageBuilder()
                .addText(messageString)
                .build();
        process(message);
    }

    public static void out(Message message) {
        assertArgumentNotNull("message", message);
        assureIsInitialized();
        process(message);
    }

    private static void assureIsInitialized() {
        if (consoleConfiguration == null) {
            Console.consoleConfiguration = new ConsoleConfigurationBuilder().build();
            Console.stringComposer = new StringComposer(consoleConfiguration);
        }
    }

    private static void process(Message message) {
        Optional<String> out = stringComposer.compose(message);
        out.ifPresent(System.out::println);
    }

}
