package de.arthurpicht.console;

import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.config.ConsoleConfigurationBuilder;
import de.arthurpicht.console.progress.TriggeredProgressIndicator;

public class ExecutableDemos {

    public static void showProgressCounter() throws InterruptedException {
        Console.print("Some progress: ");
        int max = 10;
        long sleepTime = 1000;
        TriggeredProgressIndicator progress = Console.showProgressCounter(max);
        for (int i = 0; i < max; i++) {
            progress.increase();
            Thread.sleep(sleepTime);
        }
        progress.clear();
        Console.println();
    }

    public static void suppressedProgressCounter() throws InterruptedException {
        ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
                .withSuppressedProgressIndicators()
                .build();
        Console.configure(consoleConfiguration);

        Console.print("No progress indicator due to suppression: ");
        int max = 10;
        long sleepTime = 1000;
        TriggeredProgressIndicator progress = Console.showProgressCounter(max);
        for (int i = 0; i < max; i++) {
            progress.increase();
            Thread.sleep(sleepTime);
        }
        progress.clear();
        Console.println();
    }


    public static void main(String[] args) throws InterruptedException {
        showProgressCounter();
        suppressedProgressCounter();
    }

}
