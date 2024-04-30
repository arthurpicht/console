package de.arthurpicht.console.integration;

import de.arthurpicht.console.Console;
import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.messageChannel.fileChannel.FileChannel;
import de.arthurpicht.console.messageChannel.fileChannel.FileChannelBuilder;
import de.arthurpicht.utils.io.file.TextFileUtils;
import de.arthurpicht.utils.io.nio2.FileUtils;
import de.arthurpicht.utils.io.tempDir.TempDir;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTest {

    @Test
    public void test() throws IOException {

        TempDir tempDir = new TempDir.Creator()
                .withAutoRemove(true)
                .withTempDirPrefix("test-IntegrationTest")
                .create();

        Path logFile = tempDir.asPath().resolve("console.log");

        FileChannel fileChannel = new FileChannelBuilder()
                        .withFile(logFile)
                        .build();

        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
                .addMessageChannel(fileChannel)
                .build();

        Console.configure(consoleConfiguration);

        Console.println("Hello World!");
        Console.println("A second line.");

        assertTrue(FileUtils.isExistingRegularFile(tempDir.asPath().resolve("console.log")));
        List<String> lines = TextFileUtils.readLinesAsStrings(logFile);

        assertEquals(2, lines.size());
        assertEquals("Hello World!", lines.getFirst());
        assertEquals("A second line.", lines.getLast());

    }

}
