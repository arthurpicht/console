package de.arthurpicht.console;

import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.message.MessageBuilder;
import de.arthurpicht.console.message.format.Format;
import de.arthurpicht.console.processor.MessageProcessor;

import java.io.PrintWriter;
import java.io.StringWriter;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

@SuppressWarnings("unused")
public class Console {

    private static ConsoleConfiguration consoleConfiguration = null;
    private static MessageProcessor messageProcessor;

    public static synchronized void configure(ConsoleConfiguration consoleConfiguration) {
        Console.consoleConfiguration = consoleConfiguration;
        Console.messageProcessor = new MessageProcessor(consoleConfiguration);
    }

    /**
     * Use method configure instead.
     */
    @Deprecated
    public static synchronized void init(ConsoleConfiguration consoleConfiguration) {
        configure(consoleConfiguration);
    }

    public static void configureWithDefaults() {
        configure(new ConsoleConfigurationBuilder().build());
    }


    /**
     * Use configureWithDefaults instead.
     */
    @Deprecated
    public static void initWithDefaults() {
        configure(new ConsoleConfigurationBuilder().build());
    }

    /**
     * Outputs specified message to console. This method provided the most flexible way specifying messages
     * to be outputted. Use the {@link MessageBuilder} in order to build a message object. All other methods
     * of that class managing output to console are convenient methods using this one under the hood.
     *
     * @param message message object
     */
    public static void out(Message message) {
        assertArgumentNotNull("message", message);
        assureIsConfigured();
        messageProcessor.process(message);
    }

    /**
     * Prints a message to console with level NORMAL without line feed applying optional format specifications.
     *
     * @param messageString message text
     * @param formats format specifications
     */
    public static void print(String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsConfigured();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .withNoLineFeed()
                .build();
        messageProcessor.process(message);
    }

    /**
     * Prints an empty message line to console.
     */
    public static void println() {
        println("");
    }

    /**
     * Prints a message line to console with level NORMAL without line feed applying optional format specifications.
     *
     * @param messageString message text
     * @param formats format specifications
     */
    public static void println(String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsConfigured();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .build();
        messageProcessor.process(message);
    }

    /**
     * Prints a message line with level VERBOSE to console applying optional format specifications.
     *
     * @param messageString message text
     */
    public static void verbose(String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsConfigured();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .asLevel(Level.VERBOSE)
                .build();
        messageProcessor.process(message);
    }

    /**
     * Prints a message with level VERBOSE to console applying optional format specifications omitting line feed.
     *
     * @param messageString message text
     */
    public static void printVerbose(String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsConfigured();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .asLevel(Level.VERBOSE)
                .withNoLineFeed()
                .build();
        messageProcessor.process(message);
    }

    /**
     * Prints a message line with level VERY_VERBOSE to console applying optional format specifications.
     *
     * @param messageString message text
     */
    public static void veryVerbose(String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsConfigured();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .asLevel(Level.VERY_VERBOSE)
                .build();
        messageProcessor.process(message);
    }

    /**
     * Prints a message with level VERY_VERBOSE to console applying optional format specifications and
     * omitting line feed.
     *
     * @param messageString message text
     */
    public static void printVeryVerbose(String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsConfigured();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .asLevel(Level.VERY_VERBOSE)
                .withNoLineFeed()
                .build();
        messageProcessor.process(message);
    }

    /**
     * Prints a message line with level VERY_VERBOSE to console applying optional format specifications.
     *
     * @param messageString message text
     */
    public static void veryVeryVerbose(String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsConfigured();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .asLevel(Level.VERY_VERY_VERBOSE)
                .build();
        messageProcessor.process(message);
    }

    /**
     * Prints a message with level VERY_VERBOSE to console applying optional format specifications
     * and omitting line feed.
     *
     * @param messageString message text
     */
    public static void printVeryVeryVerbose(String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsConfigured();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .asLevel(Level.VERY_VERY_VERBOSE)
                .withNoLineFeed()
                .build();
        messageProcessor.process(message);
    }

    /**
     * Prints a message line to standard error stream on console applying optional format specifications.
     *
     * @param messageString message text
     */
    public static void error(String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsConfigured();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .toErrorStream()
                .build();
        messageProcessor.process(message);
    }

    /**
     * Prints a message to standard error stream on console applying optional format specifications and omitting
     * line feed.
     *
     * @param messageString message text
     */
    public static void printError(String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsConfigured();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .toErrorStream()
                .withNoLineFeed()
                .build();
        messageProcessor.process(message);
    }

    public static void printStackTrace(Throwable throwable) {
        assertArgumentNotNull("throwable", throwable);
        assureIsConfigured();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        String messageString = sw.toString();
        Message message = new MessageBuilder()
                .addText(messageString)
                .toErrorStream()
                .build();
        messageProcessor.process(message);
    }

    private static synchronized void assureIsConfigured() {
        if (consoleConfiguration == null) {
            Console.consoleConfiguration = new ConsoleConfigurationBuilder().build();
            configure(consoleConfiguration);
        }
    }

}
