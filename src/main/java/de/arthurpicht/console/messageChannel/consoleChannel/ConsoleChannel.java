package de.arthurpicht.console.messageChannel.consoleChannel;

import de.arthurpicht.console.Console;
import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.message.StandardStream;
import de.arthurpicht.console.messageChannel.MessageChannel;
import de.arthurpicht.console.processor.StringComposer;
import de.arthurpicht.console.utils.AnsiCode;

import java.io.PrintStream;

public class ConsoleChannel implements MessageChannel {

    private final ConsoleConfiguration consoleConfiguration;
    private final StringComposer stringComposer;

    public ConsoleChannel(ConsoleConfiguration consoleConfiguration) {
        this.consoleConfiguration = consoleConfiguration;
        this.stringComposer = new StringComposer(consoleConfiguration.isColors());
    }

    @Override
    public boolean isMuted() {
        return Console.getConfiguration().isMute();
    }

    @Override
    public void process(Message message) {
        if (!applies(message)) return;
        PrintStream standardOut = this.consoleConfiguration.getStandardOut();
        if (message.isClearLine() && !this.consoleConfiguration.isPlain())
            standardOut.print(AnsiCode.ERASE_LINE_CONTENT() + AnsiCode.CARRIAGE_RETURN());
        String string = this.stringComposer.compose(message);
        if (message.isLineFeed()) string += "\n";
        if (message.getTarget() == StandardStream.OUT) {
            standardOut.print(string);
        } else {
            PrintStream standardErrorOut = this.consoleConfiguration.getStandardErrorOut();
            standardErrorOut.print(string);
        }
    }

    private boolean applies(Message message) {
        return Level.applies(message.getLevel(), this.consoleConfiguration.getLevel());
    }

}
