package de.arthurpicht.console;

import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.message.MessageBuilder;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class Console {

    private static ConsoleConfiguration consoleConfiguration = null;

    public static void init(ConsoleConfiguration consoleConfiguration) {
        Console.consoleConfiguration = consoleConfiguration;
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
        }
    }

    private static void process(Message message) {
        // TODO
        System.out.println(message.getTextList().get(0));
    }



}
