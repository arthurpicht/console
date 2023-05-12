package de.arthurpicht.console.processor;

import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.message.Message;
import de.arthurpicht.console.message.Text;
import de.arthurpicht.utils.core.strings.Strings;

import java.util.ArrayList;
import java.util.List;

public class StringComposer {

    public enum Target { CONSOLE, LOGGER }

    private final ConsoleConfiguration consoleConfiguration;

    public StringComposer(ConsoleConfiguration consoleConfiguration) {
        this.consoleConfiguration = consoleConfiguration;
    }

    public String compose(Message message, Target target) {
        List<String> formattedTextStrings = new ArrayList<>();
        for (Text text : message.getTextList()) {
            TextFormatter textFormatter = new TextFormatter(text);
            boolean colorization = determineColorization(textFormatter, target);
            String formattedString = textFormatter.getFormattedString(colorization);
            formattedTextStrings.add(formattedString);
        }
        return Strings.concat(formattedTextStrings);
    }

    private boolean determineColorization(TextFormatter textFormatter, Target target) {
        if (target == Target.LOGGER) return false;
        if (!this.consoleConfiguration.isColors()) return false;
        return textFormatter.hasColorFormat();
    }

}
