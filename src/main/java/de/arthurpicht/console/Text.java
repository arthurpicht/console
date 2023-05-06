package de.arthurpicht.console;

import de.arthurpicht.console.format.Format;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Text {

    private final String textString;
    private final List<Format> formatList;

    public Text(String textString, List<Format> formatList) {
        this.textString = textString;
        this.formatList = Collections.unmodifiableList(formatList);
    }

    public Text(String textString, Format[] formats) {
        this.textString = textString;
        this.formatList = Collections.unmodifiableList(Arrays.asList(formats));
    }

    public Text(String textString) {
        this.textString = textString;
        this.formatList = Collections.unmodifiableList(new ArrayList<>());
    }

    public String getTextString() {
        return textString;
    }

    public List<Format> getFormatList() {
        return formatList;
    }

}
