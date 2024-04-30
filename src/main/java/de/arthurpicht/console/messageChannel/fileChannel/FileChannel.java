package de.arthurpicht.console.messageChannel.fileChannel;

import de.arthurpicht.console.Console;
import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.messageChannel.MessageChannel;
import de.arthurpicht.console.processor.StringComposer;
import de.arthurpicht.utils.io.nio2.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileChannel implements MessageChannel {

    private final FileChannelConfiguration fileChannelConfiguration;
    private final StringComposer stringComposer;

    public FileChannel(FileChannelConfiguration fileChannelConfiguration) {
        this.fileChannelConfiguration = fileChannelConfiguration;
        assertParentDirectoryExists(fileChannelConfiguration.file());
        assureFileExists(fileChannelConfiguration.file());
        this.stringComposer = new StringComposer(false);
    }

    @Override
    public void process(Message message) {
        if (!applies(message)) return;
        String string = "";
        if (this.fileChannelConfiguration.writeTimestamp())
            string = getCurrentTimestamp();
        if (this.fileChannelConfiguration.writeLevel())
            string += getLevelTag(message.getLevel());
        if (message.isClearLine()) {
            write("<last line deleted>\n");
        }
        string += this.stringComposer.compose(message);
        if (!message.isLineFeed()) string += "<truncated>";
        write(string + "\n");
    }

    @Override
    public boolean isMuted() {
        return this.fileChannelConfiguration.isMuted();
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
        Path parentDirectory = file.getParent();
        if (!FileUtils.isExistingDirectory(parentDirectory)) {
            throw new RuntimeException("File [" + file + "] does not exist or is not a directory");
        }
    }

    private void assureFileExists(Path file) {
        try {
            if (!Files.exists(file))
                Files.createFile(file);
        } catch (IOException e) {
            throw new RuntimeException("Error on creating file [" + file + "]: " + e.getMessage(), e);
        }
    }

    private boolean applies(Message message) {
        Level level = fileChannelConfiguration.hasLevel() ?
                fileChannelConfiguration.level() :
                Console.getConfiguration().getLevel();
        return Level.applies(message.getLevel(), level);
    }

    private String getCurrentTimestamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()) + " ";
    }

    private String getLevelTag(Level level) {
        return "[" + level.name() + "] ";
    }

}
