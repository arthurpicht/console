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

    /**
     * Configure console functionality. This method should be called at the entry point
     * of the application. If this method is not called before the first usage of Console
     * methods that prints messages a default configuration is established.
     *
     * @param consoleConfiguration console configuration
     */
    public static synchronized void configure(ConsoleConfiguration consoleConfiguration) {
        Console.consoleConfiguration = consoleConfiguration;
        Console.messageProcessor = new MessageProcessor(consoleConfiguration);
    }

    /**
     * Configure console functionality with default configuration. Like {@link #configure(ConsoleConfiguration)}
     * this method should also be called at the entry point of the application. A default configuration is
     * established anyway, if no configuration is done.
     */
    public static synchronized void configureWithDefaults() {
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
     * Prints message without line feed for specified level and optionally specified formats.
     *
     * @param level level
     * @param messageString message text
     * @param formats text effects
     */
    public static void print(Level level, String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsConfigured();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .asLevel(level)
                .withNoLineFeed()
                .build();
        messageProcessor.process(message);
    }

    /**
     * Prints message for specified level and optionally specified formats.
     *
     * @param level level
     * @param messageString message text
     * @param formats text effects
     */
    public static void println(Level level, String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsConfigured();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .asLevel(level)
                .build();
        messageProcessor.process(message);
    }

    /**
     * Prints a message to console with level NORMAL without line feed applying optional format specifications.
     *
     * @param messageString message text
     * @param formats       text effects
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
     * Prints an empty message line.
     */
    public static void println() {
        assureIsConfigured();
        Message message = new MessageBuilder()
                .addText("")
                .build();
        messageProcessor.process(message);
    }

    /**
     * Prints a message line to console with level NORMAL without line feed applying optional format specifications.
     *
     * @param messageString message text
     * @param formats       text effects;
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
     * @param formats       text effects
     */
    public static void printlnVerbose(String messageString, Format... formats) {
        println(Level.VERBOSE, messageString, formats);
    }

    /**
     * Alias for {@link #printlnVerbose(String, Format...)}.
     *
     * @param messageString message text
     * @param formats       text effects
     */
    public static void verbose(String messageString, Format... formats) {
        println(Level.VERBOSE, messageString, formats);
    }

    /**
     * Prints a message with level VERBOSE to console applying optional format specifications omitting line feed.
     *
     * @param messageString message text
     * @param formats       text effects
     */
    public static void printVerbose(String messageString, Format... formats) {
        print(Level.VERBOSE, messageString, formats);
    }

    /**
     * Prints a message line with level VERY_VERBOSE to console applying optional format specifications.
     *
     * @param messageString message text
     * @param formats       text effects
     */
    public static void printlnVeryVerbose(String messageString, Format... formats) {
        println(Level.VERY_VERBOSE, messageString, formats);
    }

    /**
     * Alias for {@link #printlnVeryVerbose(String, Format...)}.
     *
     * @param messageString message text
     * @param formats       text effects
     */
    public static void veryVerbose(String messageString, Format... formats) {
        println(Level.VERY_VERBOSE, messageString, formats);
    }

    /**
     * Prints a message with level VERY_VERBOSE to console applying optional format specifications and
     * omitting line feed.
     *
     * @param messageString message text
     */
    public static void printVeryVerbose(String messageString, Format... formats) {
        print(Level.VERY_VERBOSE, messageString, formats);
    }

    /**
     * Prints a message line with level VERY_VERY_VERBOSE to console applying optional format specifications.
     *
     * @param messageString message text
     * @param formats       text effects
     */
    public static void printlnVeryVeryVerbose(String messageString, Format... formats) {
        println(Level.VERY_VERY_VERBOSE, messageString, formats);
    }

    /**
     * An alias for {@link #printlnVeryVeryVerbose(String, Format...)}.
     *
     * @param messageString message text
     * @param formats       text effects
     */
    public static void veryVeryVerbose(String messageString, Format... formats) {
        println(Level.VERY_VERY_VERBOSE, messageString, formats);
    }

    /**
     * Prints a message with level VERY_VERBOSE to console applying optional format specifications
     * and omitting line feed.
     *
     * @param messageString message text
     */
    public static void printVeryVeryVerbose(String messageString, Format... formats) {
        print(Level.VERY_VERY_VERBOSE, messageString, formats);
    }

    /**
     * Prints a message line to standard error stream on console applying optional format specifications.
     *
     * @param messageString message text
     * @param formats       text effects
     */
    public static void printlnError(String messageString, Format... formats) {
        assertArgumentNotNull("messageString", messageString);
        assureIsConfigured();
        Message message = new MessageBuilder()
                .addText(messageString, formats)
                .toErrorStream()
                .build();
        messageProcessor.process(message);
    }

    /**
     * Alias to {@link #printlnError(String, Format...)}.
     *
     * @param messageString message text
     * @param formats       text effects
     */
    public static void error(String messageString, Format... formats) {
        printlnError(messageString, formats);
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

    /**
     * Prints stack trace to error stream.
     *
     * @param throwable to be printed
     */
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

    /**
     * Returns current configuration.
     *
     * @return configuration
     */
    public static ConsoleConfiguration getConfiguration() {
        assureIsConfigured();
        return consoleConfiguration;
    }

    private static synchronized void assureIsConfigured() {
        if (consoleConfiguration == null) {
            Console.consoleConfiguration = new ConsoleConfigurationBuilder().build();
            configure(consoleConfiguration);
        }
    }

}
