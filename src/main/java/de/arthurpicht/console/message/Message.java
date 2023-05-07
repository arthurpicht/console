package de.arthurpicht.console.message;

import java.util.Collections;
import java.util.List;

public class Message {

    private final Level level;
    private final StandardStream target;
    private final List<Text> textList;
    private final boolean clearLine;
    private final boolean lineFeed;

    public Message(
            Level level,
            StandardStream target,
            List<Text> textList,
            boolean clearLine,
            boolean lineFeed

    ) {
        this.level = level;
        this.target = target;
        this.textList = Collections.unmodifiableList(textList);
        this.clearLine = clearLine;
        this.lineFeed = lineFeed;
    }

    public Level getLevel() {
        return level;
    }

    public StandardStream getTarget() {
        return target;
    }

    public List<Text> getTextList() {
        return textList;
    }

    public boolean isClearLine() {
        return clearLine;
    }

    public boolean isLineFeed() {
        return lineFeed;
    }
}
