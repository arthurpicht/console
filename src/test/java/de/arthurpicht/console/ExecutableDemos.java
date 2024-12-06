package de.arthurpicht.console;

import de.arthurpicht.console.progress.TriggeredProgressIndicator;

public class ExecutableDemos {

    public static void showProgressCounter() throws InterruptedException {
        Console.print("Some progress: ");
        int max = 10;
        long sleepTime = 1000;
        TriggeredProgressIndicator progress = Console.showProgressCounter(max);
        progress.display();
        for (int i = 0; i < max; i++) {
            progress.increase();
            Thread.sleep(sleepTime);
        }
        progress.clear();
    }

    public static void main(String[] args) throws InterruptedException {
        showProgressCounter();
    }

}
