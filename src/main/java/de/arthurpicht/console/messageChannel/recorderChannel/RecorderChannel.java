package de.arthurpicht.console.messageChannel.recorderChannel;

import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.messageChannel.MessageChannel;
import de.arthurpicht.console.processor.StringComposer;

import java.util.ArrayList;
import java.util.List;

public class RecorderChannel implements MessageChannel {

    private final ConsoleConfiguration consoleConfiguration;
    private final List<String> consoleOutputCache;
    private final StringComposer stringComposer;
    private boolean muted;

    public RecorderChannel(ConsoleConfiguration consoleConfiguration) {
        this.consoleConfiguration = consoleConfiguration;
        this.consoleOutputCache = new ArrayList<>();
        this.stringComposer = new StringComposer(false);
        this.muted = false;
    }

    @Override
    public void process(Message message) {
        if (!applies(message)) return;
        String string = "";
        if (message.isClearLine()) {
            this.consoleOutputCache.add("<last line deleted>\n");
        }
        string += this.stringComposer.compose(message);
        if (!message.isLineFeed()) string += "<truncated>";
        this.consoleOutputCache.add(string + "\n");
    }

    @Override
    public boolean isMuted() {
        return this.muted;
    }

    public void mute() {
        this.muted = true;
    }

    public void unmute() {
        this.muted = false;
    }

    public List<String> getConsoleOutput() {
       return this.consoleOutputCache;
    }

    private boolean applies(Message message) {
        return Level.applies(message.getLevel(), this.consoleConfiguration.getLevel());
    }

}
