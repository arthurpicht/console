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
        // a call of Ansi.colorize with an empty attributes array will add PREFIX and POSTFIX control chars.
        // Hence, we check this.
        if (attributes.length > 0) {
            return Ansi.colorize(text.getTextString(), attributes);
        } else {
            return text.getTextString();
        }
    }

}
