package de.arthurpicht.console.config;

import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.messageChannel.MessageChannel;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

public class ConsoleConfiguration {

    private final Level level;
    private final boolean colors;
    private final boolean ignoreNoColorEnvVar;
    private final boolean plain;
    private final boolean mute;
    private final boolean suppressProgressIndicators;
    private final boolean recorder;
    private final List<MessageChannel> messageChannelList;
    private final PrintStream standardOut;
    private final PrintStream standardErrorOut;

    public ConsoleConfiguration(
            Level level,
            boolean colors,
            boolean ignoreNoColorEnvVar,
            boolean plain,
            boolean mute,
            boolean suppressProgressIndicators,
            boolean recorder,
            List<MessageChannel> messageChannelList,
            PrintStream standardOut,
            PrintStream standardErrorOut) {
        this.level = level;
        this.colors = colors;
        this.ignoreNoColorEnvVar = ignoreNoColorEnvVar;
        this.plain = plain;
        this.mute = mute;
        this.suppressProgressIndicators = suppressProgressIndicators;
        this.recorder = recorder;
        this.messageChannelList = Collections.unmodifiableList(messageChannelList);
        this.standardOut = standardOut;
        this.standardErrorOut = standardErrorOut;
    }

    public Level getLevel() {
        return this.level;
    }

    public boolean isColors() {
        return this.colors;
    }

    public boolean isIgnoreNoColorEnvVar() {
        return this.ignoreNoColorEnvVar;
    }

    public boolean isPlain() {
        return this.plain;
    }

    public boolean isMute() {
        return this.mute;
    }

    public boolean isSuppressProgressIndicators() {
        return this.suppressProgressIndicators;
    }

    public boolean hasRecorder() {
        return this.recorder;
    }

    public List<MessageChannel> getMessageChannelList() {
        return this.messageChannelList;
    }

    public PrintStream getStandardOut() {
        return this.standardOut;
    }

    public PrintStream getStandardErrorOut() {
        return this.standardErrorOut;
    }

}
