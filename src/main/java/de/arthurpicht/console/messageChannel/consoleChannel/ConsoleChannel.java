package de.arthurpicht.console.messageChannel.consoleChannel;

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
    private final PrintStreamMonitor printStreamMonitor;
    private final PrintStream stdout;
    private final PrintStream stderr;

    public ConsoleChannel(ConsoleConfiguration consoleConfiguration) {
        this.consoleConfiguration = consoleConfiguration;
        this.stringComposer = new StringComposer(withColor(consoleConfiguration));
        this.printStreamMonitor
                = new PrintStreamMonitor(consoleConfiguration.getStandardOut(), consoleConfiguration.getStandardErrorOut());
        this.stdout = printStreamMonitor.getPrintStreamStdOut();
        this.stderr = printStreamMonitor.getPrintStreamStdErr();
    }

    @Override
    public boolean isMuted() {
        return this.consoleConfiguration.isMute();
    }

    @Override
    public void process(Message message) {
        if (!applies(message)) return;
        if (message.isClearLine() && !this.consoleConfiguration.isPlain())
            stdout.print(AnsiCode.ERASE_LINE_CONTENT() + AnsiCode.CARRIAGE_RETURN());
        if (message.isTerminatePreviousLine() && !this.printStreamMonitor.lastOutputEndsWithNewline())
            stdout.println();
        String string = this.stringComposer.compose(message);
        if (message.isLineFeed()) string += "\n";
        if (message.getTarget() == StandardStream.OUT) {
            stdout.print(string);
        } else {
            stderr.print(string);
        }
    }

    @SuppressWarnings("RedundantIfStatement")
    private boolean withColor(ConsoleConfiguration consoleConfiguration) {
        if (!consoleConfiguration.isColors()) {
            return false;
        } else if (System.getenv("NO_COLOR") != null && !consoleConfiguration.isIgnoreNoColorEnvVar()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean applies(Message message) {
        return Level.applies(message.getLevel(), this.consoleConfiguration.getLevel());
    }

}
