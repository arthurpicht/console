package de.arthurpicht.console.messageChannel.fileChannel;

import de.arthurpicht.console.message.Level;

import java.nio.file.Path;

public class FileChannelConfigurationBuilder {

    private Path file = null;
    private boolean isMuted = false;
    private Level level = null;
    private boolean writeTimestamp = false;
    private boolean writeLevel = false;

    public FileChannelConfigurationBuilder withFile(Path file) {
        this.file = file;
        return this;
    }

    public FileChannelConfigurationBuilder withMuted(boolean isMuted) {
        this.isMuted = isMuted;
        return this;
    }

    public FileChannelConfigurationBuilder withLevel(Level level) {
        this.level = level;
        return this;
    }

    public FileChannelConfigurationBuilder withLevelInheritedFromConsoleConfiguration() {
        this.level = null;
        return this;
    }

    public FileChannelConfigurationBuilder withWriteTimestamp() {
        this.writeTimestamp = true;
        return this;
    }

    public FileChannelConfigurationBuilder withWriteLevel() {
        this.writeLevel = true;
        return this;
    }

    public FileChannelConfiguration build() {
        if (this.file == null)
            throw new IllegalStateException("Parameter [file] has not been set.");
        return new FileChannelConfiguration(
                this.file,
                this.isMuted,
                this.level,
                this.writeTimestamp,
                this.writeLevel
        );
    }

}
