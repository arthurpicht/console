package de.arthurpicht.console.processor;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import de.arthurpicht.console.message.Text;
import de.arthurpicht.console.message.format.BlockFormat;
import de.arthurpicht.console.message.format.ColorFormat;
import de.arthurpicht.console.utils.StringUtils;
import de.arthurpicht.utils.core.strings.Strings;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TextFormatter {

    private final Text text;
    private final BlockFormat blockFormat;
    private final List<ColorFormat> colorFormatList;

    private String formattedStringWithColors;
    private String formattedStringNoColors;

    public TextFormatter(Text text) {
        this.text = text;
        Optional<BlockFormat> blockFormatOptional = getBlockFormat(text);
        this.blockFormat = blockFormatOptional.orElse(null);
        this.colorFormatList = getColorFormatList(text);
        this.formattedStringWithColors = null;
        this.formattedStringNoColors = null;
    }

    public boolean hasBlockFormat() {
        return this.blockFormat != null;
    }

    public boolean hasColorFormat() {
        return !this.colorFormatList.isEmpty();
    }

    public String getFormattedStringWithColors() {
        if (this.formattedStringWithColors == null)
            this.formattedStringWithColors = createFormattedStringWithColors();
        return this.formattedStringWithColors;
    }

    public String getFormattedStringNoColors() {
        if (this.formattedStringNoColors == null)
            this.formattedStringNoColors = createFormattedStringNoColors();
        return this.formattedStringNoColors;
    }

    public String getFormattedString(boolean colorized) {
        if (colorized) {
            return getFormattedStringWithColors();
        } else {
            return getFormattedStringNoColors();
        }
    }

    private String createFormattedStringWithColors() {
        String blockString = expandToBlock(this.text.getTextString());
        if (hasBlockFormat() && !this.blockFormat.isExpandTextEffects()) {
            String colorizedRawString = colorize(this.text.getTextString());
            return blockString.replace(this.text.getTextString(), colorizedRawString);
        } else {
            return colorize(blockString);
        }
    }

    private String createFormattedStringNoColors() {
        return expandToBlock(this.text.getTextString());
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

    private String expandToBlock(String string) {
        if (!hasBlockFormat()) return string;
        int blockWidth = this.blockFormat.getWidth();
        string = string.trim();
        if (string.length() == blockWidth) {
            return string;
        } else if (string.length() > blockWidth) {
            string = Strings.limit(string, blockWidth);
            String abbreviationSign = Strings.limit(this.blockFormat.getAbbreviationSign(), blockWidth);
            return StringUtils.overwriteRight(string, abbreviationSign);
        } else {
            if (this.blockFormat.getAlign() == BlockFormat.Align.RIGHT) {
                return StringUtils.fillUpLeft(string, ' ', blockWidth);
            } else if (this.blockFormat.getAlign() == BlockFormat.Align.LEFT) {
                return Strings.fillUpAfter(string, ' ', blockWidth);
            } else {
                return StringUtils.fillUpLeftAndRight(string, ' ', blockWidth);
            }
        }
    }

}
