package de.arthurpicht.console.config;

public class LoggerDelegatorConfig {

    private final String loggerName;

    public LoggerDelegatorConfig(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLoggerName() {
        return loggerName;
    }

}
