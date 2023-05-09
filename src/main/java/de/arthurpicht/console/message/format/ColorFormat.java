package de.arthurpicht.console.message.format;

import com.diogonunes.jcolor.Attribute;

public class ColorFormat extends Format {

    private final Attribute attribute;

    public ColorFormat(Attribute attribute) {
        this.attribute = attribute;
    }

    public Attribute asJColorAttribute() {
        return this.attribute;
    }

}
