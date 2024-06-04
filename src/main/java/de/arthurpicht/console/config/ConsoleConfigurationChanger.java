package de.arthurpicht.console.config;

import de.arthurpicht.console.Console;

/**
 * Functionalities for changing any preexisting configuration. These methods are not thread-safe. To be used in
 * single-threaded processes.
 */
@SuppressWarnings("unused")
public class ConsoleConfigurationChanger {

    public static void mute() {
        changeMuteState(true);
    }

    public static void unmute() {
        changeMuteState(false);
    }

    public static void changeMuteState(boolean muted) {
        ConsoleConfiguration consoleConfiguration = Console.getConfiguration();
        ConsoleConfigurationBuilder consoleConfigurationBuilder = new ConsoleConfigurationBuilder(consoleConfiguration);
        consoleConfigurationBuilder.withMutedOutput(muted);
        Console.configure(consoleConfigurationBuilder.build());
    }

}
