package de.arthurpicht.console.message;

import de.arthurpicht.console.message.format.Format;
import de.arthurpicht.utils.core.assertion.MethodPreconditions;

import java.util.ArrayList;
import java.util.List;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentIsEqualToOrGreaterThanZero;

public class MessageBuilder {

    private Level level;
    private StandardStream target;
    private final List<Text> textList;
    private boolean clearLine;
    private boolean lineFeed;
    private int indentation;

    public MessageBuilder() {
        this.level = Level.NORMAL;
        this.target = StandardStream.OUT;
        this.textList = new ArrayList<>();
        this.clearLine = false;
        this.lineFeed = true;
        this.indentation = 0;
    }

    public MessageBuilder asVerbose() {
        this.level = Level.VERBOSE;
        return this;
    }

    public MessageBuilder asVeryVerbose() {
        this.level = Level.VERY_VERBOSE;
        return this;
    }

    public MessageBuilder asVeryVeryVerbose() {
        this.level = Level.VERY_VERY_VERBOSE;
        return this;
    }

    public MessageBuilder asLevel(Level level) {
        this.level = level;
        return this;
    }

    public MessageBuilder toErrorStream() {
        this.target = StandardStream.ERROR;
        return this;
    }

    public MessageBuilder toStandardOut() {
        this.target = StandardStream.OUT;
        return this;
    }

    public MessageBuilder addText(String textString) {
        Text text = new Text(textString);
        this.textList.add(text);
        return this;
    }

    public MessageBuilder addText(String textString, Format... formats) {
        Text text = new Text(textString, formats);
        this.textList.add(text);
        return this;
    }

    public MessageBuilder clearLine() {
        this.clearLine = true;
        return this;
    }

    public MessageBuilder withNoLineFeed() {
        this.lineFeed = false;
        return this;
    }

    public MessageBuilder withIndentation(int indentation) {
        this.indentation = indentation;
        return this;
    }

    public Message build() {
        return new Message(
                this.level,
                this.target,
                this.textList,
                this.clearLine,
                this.lineFeed,
                this.indentation
        );
    }

}
