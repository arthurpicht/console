package de.arthurpicht.console.config;

import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.messageChannel.MessageChannel;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ConsoleConfigurationBuilder {

    private Level level;
    private boolean colors;
    private boolean plain;
    private boolean muteOutput;
    private final List<MessageChannel> messageChannelList;
    private PrintStream standardOut;
    private PrintStream standardErrorOut;

    public ConsoleConfigurationBuilder() {
        this.level = Level.NORMAL;
        this.colors = true;
        this.plain = false;
        this.muteOutput = false;
        this.messageChannelList = new ArrayList<>();
        this.standardOut = System.out;
        this.standardErrorOut = System.err;
    }

    /**
     * Specifies level of console output.
     *
     * @param level level of console output
     */
    public ConsoleConfigurationBuilder asLevel(Level level) {
        this.level = level;
        return this;
    }

    /**
     * Colors are suppressed on console.
     */
    public ConsoleConfigurationBuilder withSuppressedColors() {
        this.colors = false;
        return this;
    }

    /**
     * Specify if colors will be suppressed.
     */
    public ConsoleConfigurationBuilder withSuppressedColors(boolean suppressedColors) {
        this.colors = !suppressedColors;
        return this;
    }

    /**
     * Any control characters are ignored, e.g. clearLine.
     */
    public ConsoleConfigurationBuilder withPlainOutput() {
        this.plain = true;
        return this;
    }

    /**
     * Specify if any control characters are ignored.
     */
    public ConsoleConfigurationBuilder withPlainOutput(boolean plainOutput) {
        this.plain = plainOutput;
        return this;
    }

    /**
     * Mutes output to console. This has no effect to potentially specified channels. Those have an own
     * flag for muting.
     */
    public ConsoleConfigurationBuilder withMutedOutput() {
        this.muteOutput = true;
        return this;
    }

    /**
     * Specify if output is muted.
     */
    public ConsoleConfigurationBuilder withMutedOutput(boolean mutedOutput) {
        this.muteOutput = mutedOutput;
        return this;
    }

    /**
     * Adds a channel of console output.
     *
     * @param messageChannel MessageChannel implementation
     */
    public ConsoleConfigurationBuilder addMessageChannel(MessageChannel messageChannel) {
        this.messageChannelList.add(messageChannel);
        return this;
    }

    /**
     * Redirect standard output to specified PrintStream.
     */
    public ConsoleConfigurationBuilder withStandardOut(PrintStream printStream) {
        this.standardOut = printStream;
        return this;
    }

    /**
     * Redirect standard error to specified PrintStream.
     */
    public ConsoleConfigurationBuilder withStandardErrorOut(PrintStream printStream) {
        this.standardErrorOut = printStream;
        return this;
    }

    public ConsoleConfiguration build() {
        return new ConsoleConfiguration(
                this.level,
                this.colors,
                this.plain,
                this.muteOutput,
                this.messageChannelList,
                this.standardOut,
                this.standardErrorOut
        );
    }

}
