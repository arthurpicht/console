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

    /**
     * Path to file. Parent directory must exist. Mandatory.
     *
     * @param file file to be written to.
     */
    public FileChannelBuilder withFile(Path file) {
        this.file = file;
        return this;
    }

    /**
     * Mute output.
     */
    public FileChannelBuilder withMuted(boolean isMuted) {
        this.isMuted = isMuted;
        return this;
    }

    /**
     * Verbosity level in which output should be made. If no level is specified (default case), then the level
     * is inherited from Console configuration.
     */
    public FileChannelBuilder withLevel(Level level) {
        this.level = level;
        return this;
    }

    /**
     * Let every message line begin with a current timestamp.
     */
    public FileChannelBuilder withWriteTimestamp() {
        this.writeTimestamp = true;
        return this;
    }

    /**
     * Let every message line contain level name.
     */
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
