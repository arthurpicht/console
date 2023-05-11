package de.arthurpicht.console.stringComposer;

import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.message.Level;
import de.arthurpicht.console.message.Message;
import de.arthurpicht.utils.core.strings.Strings;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StringComposer {

    private final ConsoleConfiguration consoleConfiguration;

    public StringComposer(ConsoleConfiguration consoleConfiguration) {
        this.consoleConfiguration = consoleConfiguration;
    }

    public Optional<String> compose(Message message) {
        if (!applies(message)) return Optional.empty();

        List<String> formattedTextStrings = message.getTextList().stream()
                .map(text -> new TextFormatter(this.consoleConfiguration, text).getFormattedString())
                .collect(Collectors.toList());

        String formattedString = Strings.concat(formattedTextStrings);
        return Optional.of(formattedString);
    }

    private boolean applies(Message message) {
        return Level.applies(message.getLevel(), this.consoleConfiguration.getLevel());
    }

}
