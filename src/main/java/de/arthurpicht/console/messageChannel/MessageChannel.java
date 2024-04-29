package de.arthurpicht.console.messageChannel;

import de.arthurpicht.console.message.Message;

public interface MessageChannel {

    void process(Message message);

}
