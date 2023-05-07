package de.arthurpicht.console.config;

import de.arthurpicht.console.message.Level;

import java.util.List;

public class ConsoleConfigurationBuilder {

    private Level level;
    private boolean colors;
    private boolean plain;
    private boolean muteOutput;
    private boolean muteLoggerDelegation;
    private List<LoggerDelegatorConfig> loggerDelegatorConfigList;

    public ConsoleConfigurationBuilder() {
        this.level = Level.NORMAL;
        this.colors = true;
        this.plain = false;
        this.muteOutput = false;
        this.muteLoggerDelegation = false;
    }

    public ConsoleConfigurationBuilder asLevel(Level level) {
        this.level = level;
        return this;
    }

    public ConsoleConfigurationBuilder withSuppressedColors() {
        this.colors = false;
        return this;
    }

    public ConsoleConfigurationBuilder withPlainOutput() {
        this.plain = true;
        return this;
    }

    public ConsoleConfigurationBuilder withMutedOutput() {
        this.muteOutput = true;
        return this;
    }

    public ConsoleConfigurationBuilder withMutedLoggerDelegation() {
        this.muteLoggerDelegation = true;
        return this;
    }

    public ConsoleConfigurationBuilder addLoggerDelegation(String loggerName) {
        LoggerDelegatorConfig loggerDelegatorConfig = new LoggerDelegatorConfig(loggerName);
        this.loggerDelegatorConfigList.add(loggerDelegatorConfig);
        return this;
    }

    public ConsoleConfiguration build() {
        return new ConsoleConfiguration(
                this.level,
                this.colors,
                this.plain,
                this.muteOutput,
                this.loggerDelegatorConfigList,
                this.muteLoggerDelegation
        );
    }


}
