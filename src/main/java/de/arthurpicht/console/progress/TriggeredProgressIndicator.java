package de.arthurpicht.console.progress;

@SuppressWarnings("unused")
public interface TriggeredProgressIndicator {

    void display();

    void clear();

    void increase();

    void increase(int amount);

    void set(int amount);

}
