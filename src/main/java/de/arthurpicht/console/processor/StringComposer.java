package de.arthurpicht.console.processor;

import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.message.Text;
import de.arthurpicht.utils.core.strings.Strings;

import java.util.ArrayList;
import java.util.List;

public class StringComposer {

//    public enum Target { CONSOLE, LOGGER }

    private final boolean colorization;
//    private final ConsoleConfiguration consoleConfiguration;

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
//            boolean colorization = determineColorization(textFormatter, target);
            String formattedString = textFormatter.getFormattedString(this.colorization && textFormatter.hasColorFormat());
            formattedTextStrings.add(formattedString);
        }
        return Strings.concat(formattedTextStrings);
    }

    private String getIndentation(int indentation) {
        return " ".repeat(indentation);
    }

//    private boolean determineColorization(TextFormatter textFormatter, Target target) {
////        if (target == Target.LOGGER) return false;
//        if (!this.consoleConfiguration.isColors()) return false;
//        return textFormatter.hasColorFormat();
//    }

}
