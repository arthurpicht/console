package de.arthurpicht.console.messageChannel;

import de.arthurpicht.console.message.Message;

/**
 * A MessageChannel is a functionality for processing console messages like printing
 * to console, storing messages in a file, dispatching messages to a logger or writing
 * messages to a database.
 */
public interface MessageChannel {

    /**
     * If MessageChannel is muted, no processing will be initialized.
     *
     * @return muted
     */
    boolean isMuted();

    /**
     * Process message and manage output to intended destiatnion.
     *
     * @param message massage to be processed
     */
    void process(Message message);

}
