package de.arthurpicht.console.stringComposer;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.message.Text;
import de.arthurpicht.console.message.format.ColorFormat;

public class TextFormatter {

    private final ConsoleConfiguration consoleConfiguration;

    public TextFormatter(ConsoleConfiguration consoleConfiguration) {
        this.consoleConfiguration = consoleConfiguration;
    }

    public String format(Text text) {

        String formattedTextString;

        if (this.consoleConfiguration.isColors()) {
            formattedTextString = colorize(text);
        } else {
            formattedTextString = text.getTextString();
        }

        return formattedTextString;
    }

    private String colorize(Text text) {
        Attribute[] attributes = text.getFormatList().stream()
                .filter(format -> format instanceof ColorFormat)
                .map(format -> (ColorFormat) format)
                .map(ColorFormat::asJColorAttribute)
                .toArray(Attribute[]::new);
        return Ansi.colorize(text.getTextString(), attributes);
    }

}
