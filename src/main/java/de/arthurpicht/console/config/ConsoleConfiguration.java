package de.arthurpicht.console.config;

import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.messageChannel.MessageChannel;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

public class ConsoleConfiguration {

    private final Level level;
    private final boolean colors;
    private final boolean plain;
    private final boolean mute;
    private final List<MessageChannel> messageChannelList;
    private final PrintStream standardOut;
    private final PrintStream standardErrorOut;

    public ConsoleConfiguration(
            Level level,
            boolean colors,
            boolean plain,
            boolean mute,
            List<MessageChannel> messageChannelList,
            PrintStream standardOut,
            PrintStream standardErrorOut) {
        this.level = level;
        this.colors = colors;
        this.plain = plain;
        this.mute = mute;
        this.messageChannelList = Collections.unmodifiableList(messageChannelList);
        this.standardOut = standardOut;
        this.standardErrorOut = standardErrorOut;
    }

    public Level getLevel() {
        return level;
    }

    public boolean isColors() {
        return colors;
    }

    public boolean isPlain() {
        return plain;
    }

    public boolean isMute() {
        return mute;
    }

    public List<MessageChannel> getMessageChannelList() {
        return messageChannelList;
    }

    public PrintStream getStandardOut() {
        return this.standardOut;
    }

    public PrintStream getStandardErrorOut() {
        return this.standardErrorOut;
    }

}
