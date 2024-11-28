package de.arthurpicht.console.messageChannel.recorderChannel;

import java.util.ArrayList;
import java.util.List;

public class ConsoleOutputCache {

    private final List<String> cache;
    private boolean nextLine;

    public ConsoleOutputCache() {
        this.cache = new ArrayList<>();
        this.nextLine = true;
    }

    public void println(String message) {
        if (this.nextLine) {
            this.cache.add(message);
        } else {
            addToLastLine(message);
        }
        this.nextLine = true;
    }

    public void print(String message) {
        if (this.nextLine) {
            this.cache.add(message);
        } else {
            addToLastLine(message);
        }
        this.nextLine = false;
    }

    public void clearLine() {
        if (!this.nextLine) {
            this.cache.removeLast();
            this.nextLine = true;
        }
    }

    public void clear() {
        this.cache.clear();
    }

    public List<String> getCachedOutput() {
        return this.cache;
    }

    public boolean lastLineEndsWithLinefeed() {
        return this.nextLine;
    }

    private void addToLastLine(String line) {
        String lastLine = this.cache.getLast();
        this.cache.removeLast();
        this.cache.add(lastLine + line);
    }

}
