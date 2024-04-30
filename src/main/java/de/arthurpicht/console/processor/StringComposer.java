package de.arthurpicht.console.processor;

import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.message.Text;
import de.arthurpicht.utils.core.strings.Strings;

import java.util.ArrayList;
import java.util.List;

public class StringComposer {

    private final boolean colorization;

    public StringComposer(boolean colorization) {
        this.colorization = colorization;
    }

    public String compose(Message message) {
        List<String> formattedTextStrings = new ArrayList<>();
        if (message.hasIndentation()) {
            String indentString = getIndentation(message.getIndentation());
            formattedTextStrings.add(indentString);
        }
        for (Text text : message.getTextList()) {
            TextFormatter textFormatter = new TextFormatter(text);
            String formattedString = textFormatter.getFormattedString(this.colorization && textFormatter.hasColorFormat());
            formattedTextStrings.add(formattedString);
        }
        return Strings.concat(formattedTextStrings);
    }

    private String getIndentation(int indentation) {
        return " ".repeat(indentation);
    }

}
