package de.arthurpicht.console.messageChannel.fileChannel;

import de.arthurpicht.console.message.Level;

import java.nio.file.Path;

public record FileChannelConfiguration(
        Path file,
        boolean isMuted,
        Level level,
        boolean writeTimestamp,
        boolean writeLevel
) {

    public boolean hasLevel() {
        return this.level != null;
    }

}
