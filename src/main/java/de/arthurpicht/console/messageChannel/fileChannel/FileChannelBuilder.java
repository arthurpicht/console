package de.arthurpicht.console.messageChannel.fileChannel;

import de.arthurpicht.console.message.Level;

import java.nio.file.Path;

@SuppressWarnings("unused")
public class FileChannelBuilder {

    private Path file = null;
    private boolean isMuted = false;
    private Level level = null;
    private boolean writeTimestamp = false;
    private boolean writeLevel = false;

    public FileChannelBuilder withFile(Path file) {
        this.file = file;
        return this;
    }

    public FileChannelBuilder withMuted(boolean isMuted) {
        this.isMuted = isMuted;
        return this;
    }

    public FileChannelBuilder withLevel(Level level) {
        this.level = level;
        return this;
    }

    public FileChannelBuilder withLevelInheritedFromConsoleConfiguration() {
        this.level = null;
        return this;
    }

    public FileChannelBuilder withWriteTimestamp() {
        this.writeTimestamp = true;
        return this;
    }

    public FileChannelBuilder withWriteLevel() {
        this.writeLevel = true;
        return this;
    }

    public FileChannel build() {
        if (this.file == null)
            throw new IllegalStateException("Parameter [file] has not been set.");
        FileChannelConfiguration fileChannelConfiguration = new FileChannelConfiguration(
                this.file,
                this.isMuted,
                this.level,
                this.writeTimestamp,
                this.writeLevel
        );
        return new FileChannel(fileChannelConfiguration);
    }

}
