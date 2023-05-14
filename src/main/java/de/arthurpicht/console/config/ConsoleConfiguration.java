package de.arthurpicht.console.config;

import de.arthurpicht.console.message.Level;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

public class ConsoleConfiguration {

    private final Level level;
    private final boolean colors;
    private final boolean plain;
    private final boolean muteOutput;
    private final boolean muteLoggerDelegation;
    private final List<LoggerDelegatorConfig> loggerDelegatorConfigList;
    private final PrintStream standardOut;
    private final PrintStream standardErrorOut;

    public ConsoleConfiguration(
            Level level,
            boolean colors,
            boolean plain,
            boolean muteOutput,
            List<LoggerDelegatorConfig> loggerDelegatorConfigList,
            boolean muteLoggerDelegation,
            PrintStream standardOut,
            PrintStream standardErrorOut) {
        this.level = level;
        this.colors = colors;
        this.plain = plain;
        this.muteOutput = muteOutput;
        this.muteLoggerDelegation = muteLoggerDelegation;
        this.loggerDelegatorConfigList = Collections.unmodifiableList(loggerDelegatorConfigList);
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

    public boolean isMuteOutput() {
        return muteOutput;
    }

    public boolean isMuteLoggerDelegation() {
        return muteLoggerDelegation;
    }

    public List<LoggerDelegatorConfig> getLoggerDelegatorConfigList() {
        return loggerDelegatorConfigList;
    }

    public PrintStream getStandardOut() {
        return this.standardOut;
    }

    public PrintStream getStandardErrorOut() {
        return this.standardErrorOut;
    }

}
