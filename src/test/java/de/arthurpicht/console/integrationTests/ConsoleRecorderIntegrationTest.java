package de.arthurpicht.console.integrationTests;

import de.arthurpicht.console.Console;
import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.message.MessageBuilder;
import de.arthurpicht.console.message.format.Format;
import de.arthurpicht.console.messageChannel.recorderChannel.ConsoleRecorder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsoleRecorderIntegrationTest {

    @Test
    public void simple() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
                .withRecorder()
                .build();
        Console.configure(consoleConfiguration);

        Console.println("Hello World!");
        Console.println("A simple recorder test.");

        List<String> output = ConsoleRecorder.getConsoleOutput();

        assertEquals(2, output.size());
        assertEquals("Hello World!", output.get(0));
        assertEquals("A simple recorder test.", output.get(1));
    }

    @Test
    public void simplePrint() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
                .withRecorder()
                .build();
        Console.configure(consoleConfiguration);

        Console.println("Hello World!");
        Console.print("Message 1 ...");
        Console.println("Message 2");

        List<String> output = ConsoleRecorder.getConsoleOutput();

        assertEquals(2, output.size());
        assertEquals("Hello World!", output.get(0));
        assertEquals("Message 1 ...Message 2", output.get(1));
    }

    @Test
    public void clearLine1() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
                .withRecorder()
                .build();
        Console.configure(consoleConfiguration);

        Console.println("Hello World!");
        Console.out(new MessageBuilder()
                .addText("second Line")
                .clearLine()
                .build());

        List<String> output = ConsoleRecorder.getConsoleOutput();

        assertEquals(2, output.size());
        assertEquals("Hello World!", output.get(0));
        assertEquals("second Line", output.get(1));
    }

    @Test
    public void clearLine2() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
                .withRecorder()
                .build();
        Console.configure(consoleConfiguration);

        Console.println("Hello World!");
        Console.print("Some message to be deleted ...");
        Console.out(new MessageBuilder()
                .addText("second Line")
                .clearLine()
                .build());

        List<String> output = ConsoleRecorder.getConsoleOutput();

        assertEquals(2, output.size());
        assertEquals("Hello World!", output.get(0));
        assertEquals("second Line", output.get(1));
    }

    @Test
    public void plain() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
                .withPlainOutput()
                .withRecorder()
                .build();
        Console.configure(consoleConfiguration);

        Console.println("Hello World!");
        Console.print("Some message to be deleted ...");
        Console.out(new MessageBuilder()
                .addText("second Line")
                .clearLine()
                .build());

        List<String> output = ConsoleRecorder.getConsoleOutput();

        assertEquals(2, output.size());
        assertEquals("Hello World!", output.get(0));
        assertEquals("Some message to be deleted ...second Line", output.get(1));
    }

    @Test
    public void muteAndUnmute() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
                .withRecorder()
                .build();
        Console.configure(consoleConfiguration);

        Console.println("Hello World!");
        ConsoleRecorder.mute();
        Console.println("Message to be muted ...");
        ConsoleRecorder.unmute();
        Console.println("A simple recorder test.");

        List<String> output = ConsoleRecorder.getConsoleOutput();

        assertEquals(2, output.size());
        assertEquals("Hello World!", output.get(0));
        assertEquals("A simple recorder test.", output.get(1));
    }

    @Test
    /*
     * Since 0.4.5 ConsoleRecorder mute state is independent of console mute state.
     */
    public void mutedConsole() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
                .withMutedOutput()
                .withRecorder()
                .build();
        Console.configure(consoleConfiguration);

        Console.println("Hello World!");

        List<String> output = ConsoleRecorder.getConsoleOutput();

        assertEquals(1, output.size());
        assertEquals("Hello World!", output.getFirst());
    }

    @Test
    public void ignoreColors() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
                .withRecorder()
                .build();
        Console.configure(consoleConfiguration);

        Console.println("Hello World!", Format.RED_TEXT());
        Console.println("A simple recorder test.", Format.BOLD());

        List<String> output = ConsoleRecorder.getConsoleOutput();

        assertEquals(2, output.size());
        assertEquals("Hello World!", output.get(0));
        assertEquals("A simple recorder test.", output.get(1));
    }

    @Test
    public void throwExceptionOnNonConfigured() {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder().build();
        Console.configure(consoleConfiguration);

        Assertions.assertThrows(IllegalStateException.class, ConsoleRecorder::mute);
    }

}
