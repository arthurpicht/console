package de.arthurpicht.console.processor;

import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.config.LoggerDelegatorConfig;
import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.message.StandardStream;
import de.arthurpicht.console.utils.AnsiCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;

public class MessageProcessor {

    private final ConsoleConfiguration consoleConfiguration;
    private final StringComposer stringComposer;

    public MessageProcessor(ConsoleConfiguration consoleConfiguration) {
        this.consoleConfiguration = consoleConfiguration;
        this.stringComposer = new StringComposer(consoleConfiguration);
    }

    public void process(Message message) {
        if (!consoleConfiguration.isMuteOutput())
            processConsoleOutput(message);
        if (!consoleConfiguration.isMuteLoggerDelegation())
            processLoggerOutput(message);
    }

    private void processConsoleOutput(Message message) {
        if (!applies(message)) return;
        PrintStream standardOut = this.consoleConfiguration.getStandardOut();
        if (message.isClearLine() && !this.consoleConfiguration.isPlain())
            standardOut.print(AnsiCode.ERASE_LINE_CONTENT() + AnsiCode.CARRIAGE_RETURN());
        String string = this.stringComposer.compose(message, StringComposer.Target.CONSOLE);
        if (message.isLineFeed()) string += "\n";
        if (message.getTarget() == StandardStream.OUT) {
            standardOut.print(string);
        } else {
            PrintStream standardErrorOut = this.consoleConfiguration.getStandardErrorOut();
            standardErrorOut.print(string);
        }
    }

    private void processLoggerOutput(Message message) {
        if (this.consoleConfiguration.getLoggerDelegatorConfigList().isEmpty()) return;
        org.slf4j.event.Level loggerLevel = getLoggerLevel(message);
        String string = this.stringComposer.compose(message, StringComposer.Target.LOGGER);
        if (!message.isLineFeed()) string += "<truncated>";
        for (LoggerDelegatorConfig loggerDelegatorConfig : this.consoleConfiguration.getLoggerDelegatorConfigList()) {
            Logger logger = LoggerFactory.getLogger(loggerDelegatorConfig.getLoggerName());
            if (message.isClearLine())
                logger.atLevel(loggerLevel).log("<last line deleted>");
            logger.atLevel(loggerLevel).log(string);
        }
    }

    private boolean applies(Message message) {
        return Level.applies(message.getLevel(), this.consoleConfiguration.getLevel());
    }

    private org.slf4j.event.Level getLoggerLevel(Message message) {
        if (message.getTarget() == StandardStream.ERROR) return org.slf4j.event.Level.ERROR;
        if (message.getLevel() == Level.VERBOSE) return org.slf4j.event.Level.DEBUG;
        if (message.getLevel() == Level.VERY_VERY_VERBOSE) return org.slf4j.event.Level.TRACE;
        return org.slf4j.event.Level.INFO;
    }

}
