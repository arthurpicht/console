package de.arthurpicht.console;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class Console {

    public static void print(String messageString) {
        assertArgumentNotNull("messageString", messageString);
        Message message = new MessageBuilder()
                .addText(messageString)
                .withNoLineFeed()
                .build();
        process(message);
    }

    public static void println(String messageString) {
        assertArgumentNotNull("messageString", messageString);
        Message message = new MessageBuilder()
                .addText(messageString)
                .build();
        process(message);
    }

    public static void out(Message message) {
        assertArgumentNotNull("message", message);
        process(message);
    }

    private static void process(Message message) {
        // TODO
        System.out.println(message.getTextList().get(0));
    }

}
