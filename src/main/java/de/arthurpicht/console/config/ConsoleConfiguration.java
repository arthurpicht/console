package de.arthurpicht.console.config;

import de.arthurpicht.console.message.Level;

import java.util.Collections;
import java.util.List;

public class ConsoleConfiguration {

    private final Level level;
    private final boolean colors;
    private final boolean plain;
    private final boolean muteOutput;
    private final boolean muteLoggerDelegation;
    private final List<LoggerDelegatorConfig> loggerDelegatorConfigList;

    public ConsoleConfiguration(
            Level level,
            boolean colors,
            boolean plain,
            boolean muteOutput,
            List<LoggerDelegatorConfig> loggerDelegatorConfigList,
            boolean muteLoggerDelegation) {
        this.level = level;
        this.colors = colors;
        this.plain = plain;
        this.muteOutput = muteOutput;
        this.muteLoggerDelegation = muteLoggerDelegation;
        this.loggerDelegatorConfigList = Collections.unmodifiableList(loggerDelegatorConfigList);
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

}
