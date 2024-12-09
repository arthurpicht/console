package de.arthurpicht.console.progress;

import de.arthurpicht.console.config.ConsoleConfiguration;

public class ProcessIndicators {

    static boolean suppressOutput(ConsoleConfiguration consoleConfiguration) {
        return consoleConfiguration.isSuppressProgressIndicators() ||
                consoleConfiguration.isMute() ||
                consoleConfiguration.isPlain();
    }

}
