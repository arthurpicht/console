package de.arthurpicht.console.config;

import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.messageChannel.MessageChannel;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ConsoleConfigurationBuilder {

    private Level level;
    private boolean colors;
    private boolean plain;
    private boolean muteOutput;
    private boolean muteMessageChannelling;
    private final List<MessageChannel> messageChannelList;
    private PrintStream standardOut;
    private PrintStream standardErrorOut;

    public ConsoleConfigurationBuilder() {
        this.level = Level.NORMAL;
        this.colors = true;
        this.plain = false;
        this.muteOutput = false;
        this.muteMessageChannelling = false;
        this.messageChannelList = new ArrayList<>();
        this.standardOut = System.out;
        this.standardErrorOut = System.err;
    }

    /**
     * Specifies level of console output.
     * This configuration has no effect to any logger delegation.
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
     * Any control os previously written console messages is ignored, e.g. clearLine.
     */
    public ConsoleConfigurationBuilder withPlainOutput() {
        this.plain = true;
        return this;
    }

    /**
     * Mutes output to console.
     */
    public ConsoleConfigurationBuilder withMutedOutput() {
        this.muteOutput = true;
        return this;
    }

    /**
     * Mutes channelling of console output.
     */
    public ConsoleConfigurationBuilder withMutedChannelling() {
        this.muteMessageChannelling = true;
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

    public ConsoleConfigurationBuilder withStandardOut(PrintStream printStream) {
        this.standardOut = printStream;
        return this;
    }

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
                this.muteMessageChannelling,
                this.standardOut,
                this.standardErrorOut
        );
    }

}
