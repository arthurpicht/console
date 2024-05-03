package de.arthurpicht.console.integrationTests;

import de.arthurpicht.console.Console;
import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.messageChannel.fileChannel.FileChannel;
import de.arthurpicht.console.messageChannel.fileChannel.FileChannelBuilder;
import de.arthurpicht.console.utils.TestUtils;
import de.arthurpicht.utils.io.file.TextFileUtils;
import de.arthurpicht.utils.io.nio2.FileUtils;
import de.arthurpicht.utils.io.tempDir.TempDir;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileChannelIntegrationTest {

    private static TempDir tempDir;

    @BeforeAll
    public static void setup() {
        tempDir = new TempDir.Creator()
                .withAutoRemove(true)
                .withTempDirPrefix("java-console-test-IntegrationTest-")
                .create();
    }

    @Test
    public void simple() throws IOException {
        Path logFile = tempDir.asPath().resolve("simple.log");
        FileChannel fileChannel = new FileChannelBuilder()
                        .withFile(logFile)
                        .build();
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
                .addMessageChannel(fileChannel)
                .build();
        Console.configure(consoleConfiguration);

        Console.println("Hello World! (simple)");
        Console.println("A second line. (simple)");

        assertTrue(FileUtils.isExistingRegularFile(logFile));
        List<String> lines = TextFileUtils.readLinesAsStrings(logFile);
        assertEquals(2, lines.size());
        assertEquals("Hello World! (simple)", lines.getFirst());
        assertEquals("A second line. (simple)", lines.getLast());
    }

    @Test
    public void withTimestamp() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        Path logFile = tempDir.asPath().resolve("timestamp.log");
        FileChannel fileChannel = new FileChannelBuilder()
                .withFile(logFile)
                .withWriteTimestamp()
                .build();
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
                .withStandardOut(printStream)
                .addMessageChannel(fileChannel)
                .build();
        Console.configure(consoleConfiguration);

        Console.println("Hello World! (Timestamp)");
        Console.println("A second line. (Timestamp)");

        String consoleMessages = byteArrayOutputStream.toString();
        assertEquals("Hello World! (Timestamp)\nA second line. (Timestamp)\n", consoleMessages);

        assertTrue(FileUtils.isExistingRegularFile(logFile));
        List<String> lines = TextFileUtils.readLinesAsStrings(logFile);
        assertEquals(2, lines.size());
        assertTrue(TestUtils.beginsWithTimestamp(lines.getFirst()));
        assertEquals("Hello World! (Timestamp)", lines.getFirst().substring(20));
        assertTrue(TestUtils.beginsWithTimestamp(lines.getLast()));
        assertEquals("A second line. (Timestamp)", lines.getLast().substring(20));
    }


    @Test
    public void withTimestampAndLevel() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        Path logFile = tempDir.asPath().resolve("timestampAndLevel.log");
        FileChannel fileChannel = new FileChannelBuilder()
                .withFile(logFile)
                .withWriteTimestamp()
                .withWriteLevel()
                .withLevel(Level.VERY_VERY_VERBOSE)
                .build();
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
                .withStandardOut(printStream)
                .addMessageChannel(fileChannel)
                .build();
        Console.configure(consoleConfiguration);

        Console.println("Hello World! (TimestampAndLevel)");
        Console.printlnVerbose("A second line. (TimestampAndLevel)");

        String consoleOutput = byteArrayOutputStream.toString();
        assertEquals("Hello World! (TimestampAndLevel)\n", consoleOutput);

        assertTrue(FileUtils.isExistingRegularFile(logFile));
        List<String> lines = TextFileUtils.readLinesAsStrings(logFile);
        assertEquals(2, lines.size());
        assertTrue(TestUtils.beginsWithTimestamp(lines.getFirst()));
        assertEquals("[NORMAL] Hello World! (TimestampAndLevel)", lines.getFirst().substring(20));
        assertTrue(TestUtils.beginsWithTimestamp(lines.getLast()));
        assertEquals("[VERBOSE] A second line. (TimestampAndLevel)", lines.getLast().substring(20));
    }

}
