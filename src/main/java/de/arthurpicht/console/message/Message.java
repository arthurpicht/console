package de.arthurpicht.console.message;

import java.util.Collections;
import java.util.List;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentIsEqualToOrGreaterThanZero;

public class Message {

    private final Level level;
    private final StandardStream target;
    private final List<Text> textList;
    private final boolean clearLine;
    private final boolean lineFeed;
    private final int indentation;

    public Message(
            Level level,
            StandardStream target,
            List<Text> textList,
            boolean clearLine,
            boolean lineFeed,
            int indentation

    ) {
        assertArgumentIsEqualToOrGreaterThanZero("indentation", indentation);
        this.level = level;
        this.target = target;
        this.textList = Collections.unmodifiableList(textList);
        this.clearLine = clearLine;
        this.lineFeed = lineFeed;
        this.indentation = indentation;
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

    public boolean hasIndentation() {
        return this.indentation > 0;
    }

    public int getIndentation() {
        return this.indentation;
    }
}
