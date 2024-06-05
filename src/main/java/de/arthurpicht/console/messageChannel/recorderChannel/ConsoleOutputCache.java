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
            this.cache.remove(this.cache.size() - 1);
            this.nextLine = true;
        }
    }

    public void clear() {
        this.cache.clear();
    }

    public List<String> getCachedOutput() {
        return this.cache;
    }

    private void addToLastLine(String line) {
        String lastLine = this.cache.get(this.cache.size() - 1);
        this.cache.remove(this.cache.size() - 1);
        this.cache.add(lastLine + line);
    }

}
