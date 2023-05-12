package de.arthurpicht.console.config;

import de.arthurpicht.console.message.Level;

import java.util.ArrayList;
import java.util.List;

public class ConsoleConfigurationBuilder {

    private Level level;
    private boolean colors;
    private boolean plain;
    private boolean muteOutput;
    private boolean muteLoggerDelegation;
    private final List<LoggerDelegatorConfig> loggerDelegatorConfigList;

    public ConsoleConfigurationBuilder() {
        this.level = Level.NORMAL;
        this.colors = true;
        this.plain = false;
        this.muteOutput = false;
        this.muteLoggerDelegation = false;
        this.loggerDelegatorConfigList = new ArrayList<>();
    }

    /**
     * Specifies level of console output.
     * This configuration has no effect to any logger delegation.
     *
     * @param level
     * @return
     */
    public ConsoleConfigurationBuilder asLevel(Level level) {
        this.level = level;
        return this;
    }

    /**
     * Colors are suppressed on console.
     *
     * @return
     */
    public ConsoleConfigurationBuilder withSuppressedColors() {
        this.colors = false;
        return this;
    }

    /**
     * Any control os previously written console messages is ignored, e.g. clearLine.
     *
     * @return
     */
    public ConsoleConfigurationBuilder withPlainOutput() {
        this.plain = true;
        return this;
    }

    /**
     * Mutes output to console.
     *
     * @return
     */
    public ConsoleConfigurationBuilder withMutedOutput() {
        this.muteOutput = true;
        return this;
    }

    /**
     * Mutes delegation of console output to loggers.
     *
     * @return
     */
    public ConsoleConfigurationBuilder withMutedLoggerDelegation() {
        this.muteLoggerDelegation = true;
        return this;
    }

    /**
     * Adds a delegation of console output to slf4j logger by specified name.
     *
     * @param loggerName name of sl4j logger
     * @return
     */
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
