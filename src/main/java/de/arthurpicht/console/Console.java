package de.arthurpicht.console;

import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.message.MessageBuilder;
import de.arthurpicht.console.message.format.Format;
import de.arthurpicht.console.processor.MessageProcessor;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class Console {

    private static ConsoleConfiguration consoleConfiguration = null;
    private static MessageProcessor messageProcessor;

    public static synchronized void init(ConsoleConfiguration consoleConfiguration) {
        Console.consoleConfiguration = consoleConfiguration;
        Console.messageProcessor = new MessageProcessor(consoleConfiguration);
    }

    public static void initWithDefaults() {
        init(new ConsoleConfigurationBuilder().build());
    }

    public static void print(String messageString) {
        assertArgumentNotNull("messageString", messageString);
        assureIsInitialized();
        Message message = new MessageBuilder()
                .addText(messageString)
                .withNoLineFeed()
                .build();
        messageProcessor.process(message);
    }

    public static void print(String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsInitialized();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .withNoLineFeed()
                .build();
        messageProcessor.process(message);
    }

    public static void println(String messageString) {
        assertArgumentNotNull("messageString", messageString);
        assureIsInitialized();
        Message message = new MessageBuilder()
                .addText(messageString)
                .build();
        messageProcessor.process(message);
    }

    public static void println() {
        println("");
    }

    public static void println(String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsInitialized();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .build();
        messageProcessor.process(message);
    }

    public static void out(Message message) {
        assertArgumentNotNull("message", message);
        assureIsInitialized();
        messageProcessor.process(message);
    }

    private static synchronized void assureIsInitialized() {
        if (consoleConfiguration == null) {
            Console.consoleConfiguration = new ConsoleConfigurationBuilder().build();
            init(consoleConfiguration);
        }
    }

}
