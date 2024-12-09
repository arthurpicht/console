package de.arthurpicht.console.progress;

import de.arthurpicht.console.config.ConsoleConfiguration;

import static de.arthurpicht.console.progress.ProcessIndicators.suppressOutput;

public class ProgressCounter implements TriggeredProgressIndicator {

    private final ConsoleConfiguration consoleConfiguration;
    private final String maxString;
    private final int displayLength;
    private int current;

    public ProgressCounter(ConsoleConfiguration consoleConfiguration, int max) {
        this.consoleConfiguration = consoleConfiguration;
        this.maxString = Integer.toString(max);
        this.displayLength = maxString.length() * 2 + 3;
        this.current = 0;
    }

    @Override
    public void display() {
        if (suppressOutput(this.consoleConfiguration)) return;
        this.consoleConfiguration.getStandardOut()
                .print("[" + getCurrentAsString() + "/" + this.maxString + "]");
    }

    @Override
    public void clear() {
        if (suppressOutput(this.consoleConfiguration)) return;
        this.consoleConfiguration.getStandardOut()
                .print("\b".repeat(this.displayLength));
    }

    @Override
    public void increase() {
        this.current++;
        updateOutput();
    }

    @Override
    public void increase(int amount) {
        this.current += amount;
        updateOutput();
    }

    @Override
    public void set(int amount) {
        this.current = amount;
        updateOutput();
    }

    private String getCurrentAsString() {
        String currentString = Integer.toString(current);
        return " ".repeat(this.maxString.length() - currentString.length()) + currentString;
    }

    private void updateOutput() {
        clear();
        display();
    }

}
