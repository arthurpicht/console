package de.arthurpicht.console.stringComposer;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import de.arthurpicht.console.config.ConsoleConfiguration;
import de.arthurpicht.console.message.Text;
import de.arthurpicht.console.message.format.BlockFormat;
import de.arthurpicht.console.message.format.ColorFormat;
import de.arthurpicht.console.utils.StringUtils;
import de.arthurpicht.utils.core.strings.Strings;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TextFormatter {

    private final ConsoleConfiguration consoleConfiguration;
    private final Text text;
    private final BlockFormat blockFormat;
    private final List<ColorFormat> colorFormatList;

    public TextFormatter(ConsoleConfiguration consoleConfiguration, Text text) {
        this.consoleConfiguration = consoleConfiguration;
        this.text = text;
        Optional<BlockFormat> blockFormatOptional = getBlockFormat(text);
        this.blockFormat = blockFormatOptional.orElse(null);
        this.colorFormatList = getColorFormatList(text);
    }

    public String getFormattedString() {

        String blockString = hasBlockFormat() ?
                fillToBlock(this.text.getTextString(), this.blockFormat) :
                this.text.getTextString();

        if (this.consoleConfiguration.isColors()) {
            if (hasBlockFormat() && !this.blockFormat.isExpandTextEffects()) {
                String colorizedRawString = colorize(this.text.getTextString());
                return blockString.replace(this.text.getTextString(), colorizedRawString);
            } else {
                return colorize(blockString);
            }
        } else {
            return blockString;
        }
    }

    private boolean hasBlockFormat() {
        return this.blockFormat != null;
    }

    private boolean hasColorFormat() {
        return !this.colorFormatList.isEmpty();
    }

    private Optional<BlockFormat> getBlockFormat(Text text) {
        return text.getFormatList().stream()
                .filter(format -> format instanceof BlockFormat)
                .map(format -> (BlockFormat) format)
                .findFirst();
    }

    private List<ColorFormat> getColorFormatList(Text text) {
        return text.getFormatList().stream()
                .filter(format -> format instanceof ColorFormat)
                .map(format -> (ColorFormat) format)
                .collect(Collectors.toList());
    }

    private Attribute[] getJColorAttributes() {
        return this.colorFormatList.stream()
                .map(ColorFormat::asJColorAttribute)
                .toArray(Attribute[]::new);
    }

    private String colorize(String string) {
        // a call of Ansi.colorize with an empty attributes array will add PREFIX and POSTFIX control chars.
        // Hence, we check this.
        if (hasColorFormat()) {
            Attribute[] attributes = getJColorAttributes();
            return Ansi.colorize(string, attributes);
        } else {
            return string;
        }
    }

//    private String createBlock(Text text) {
//        Optional<Format> formatOptional = text.getFormatList().stream()
//                .filter(format -> format instanceof BlockFormat)
//                .findFirst();
//        if (formatOptional.isEmpty()) {
//            return text.getTextString();
//        } else {
//            BlockFormat blockFormat = (BlockFormat) formatOptional.get();
//            return createBlock(text.getTextString(), blockFormat);
//        }
//    }

    private String fillToBlock(String string, BlockFormat blockFormat) {
        int blockWidth = blockFormat.getWidth();
        string = string.trim();
        if (string.length() == blockWidth) {
            return string;
        } else if (string.length() > blockWidth) {
            string = Strings.limit(string, blockWidth);
            String abbreviationSign = Strings.limit(blockFormat.getAbbreviationSign(), blockWidth);
            return StringUtils.overwriteRight(string, abbreviationSign);
        } else {
            if (blockFormat.getAlign() == BlockFormat.Align.RIGHT) {
                return StringUtils.fillUpLeft(string, ' ', blockWidth);
            } else if (blockFormat.getAlign() == BlockFormat.Align.LEFT) {
                return Strings.fillUpAfter(string, ' ', blockWidth);
            } else {
                return StringUtils.fillUpLeftAndRight(string, ' ', blockWidth);
            }
        }
    }

}
