package de.arthurpicht.console.messageChannel.recorderChannel;

import de.arthurpicht.console.config.ConsoleConfiguration;

import java.util.List;

public class ConsoleRecorder {

    private static RecorderChannel recorderChannel;

    public static void initialize(ConsoleConfiguration consoleConfiguration) {
        recorderChannel = new RecorderChannel(consoleConfiguration);
    }

    public static void destroy() {
        recorderChannel = null;
    }

    public static RecorderChannel getRecorderChannel() {
        return recorderChannel;
    }

    public static void mute() {
        if (recorderChannel == null) throw new IllegalStateException("RecorderChannel not initialized.");
        recorderChannel.mute();
    }

    public static void unmute() {
        if (recorderChannel == null) throw new IllegalStateException("RecorderChannel not initialized.");
        recorderChannel.unmute();
    }

    public static List<String> getConsoleOutput() {
        if (recorderChannel == null) throw new IllegalStateException("RecorderChannel not initialized.");
        return recorderChannel.getConsoleOutput();
    }

    public static void clear() {
        if (recorderChannel == null) throw new IllegalStateException("RecorderChannel not initialized.");
        recorderChannel.clear();
    }

}
