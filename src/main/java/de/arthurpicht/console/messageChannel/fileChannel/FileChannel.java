package de.arthurpicht.console.messageChannel.fileChannel;

import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.messageChannel.MessageChannel;
import de.arthurpicht.console.processor.StringComposer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileChannel implements MessageChannel {

    private final FileChannelConfiguration fileChannelConfiguration;
    private final Level level;
    private final StringComposer stringComposer;

    public FileChannel(FileChannelConfiguration fileChannelConfiguration, ConsoleConfiguration consoleConfiguration) {
        this.fileChannelConfiguration = fileChannelConfiguration;
        assertParentDirectoryExists(fileChannelConfiguration.file());
        this.level = fileChannelConfiguration.level() != null ?
                        fileChannelConfiguration.level() :
                        consoleConfiguration.getLevel();
        this.stringComposer = new StringComposer(false);
    }

    @Override
    public void process(Message message) {
        if (this.fileChannelConfiguration.isMuted()) return;
        if (!applies(message)) return;
        String string = "";
        if (this.fileChannelConfiguration.writeTimestamp())
            string = getCurrentTimestamp();
        if (this.fileChannelConfiguration.writeLevel())
            string += getLevelTag(message.getLevel());
        if (message.isClearLine()) {
            string += "<last line deleted>";
        } else {
            string += this.stringComposer.compose(message);
            if (!message.isLineFeed()) string += "<truncated>";
        }
        write(string + "\n");
    }

    private void write(String message) {
        try {
            Files.writeString(
                    fileChannelConfiguration.file(),
                    message,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException("Could not write message to file [" + fileChannelConfiguration.file() + "]: "
                    + e.getMessage(), e);
        }
    }

    private void assertParentDirectoryExists(Path file) {
        if (!Files.exists(file) || !Files.isDirectory(file)) {
            throw new RuntimeException("File [" + file + "] does not exist or is not a directory");
        }
    }

    private boolean applies(Message message) {
        return Level.applies(message.getLevel(), this.level);
    }

    private String getCurrentTimestamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()) + " ";
    }

    private String getLevelTag(Level level) {
        return "[" + level.name() + "] ";
    }

}
