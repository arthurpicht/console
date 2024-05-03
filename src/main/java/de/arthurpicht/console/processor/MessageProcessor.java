package de.arthurpicht.console.processor;

import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.messageChannel.MessageChannel;
import de.arthurpicht.console.messageChannel.consoleChannel.ConsoleChannel;

import java.util.ArrayList;
import java.util.List;

public class MessageProcessor {

    private final List<MessageChannel> messageChannels;

    public MessageProcessor(ConsoleConfiguration consoleConfiguration) {
        List<MessageChannel> messageChannelList = new ArrayList<>(consoleConfiguration.getMessageChannelList());
        messageChannelList.add(0, new ConsoleChannel(consoleConfiguration));
        this.messageChannels = messageChannelList;
    }

    public void process(Message message) {
        this.messageChannels.stream()
                .filter(messageChannel -> !messageChannel.isMuted())
                .forEach(messageChannel -> messageChannel.process(message));
    }

}
