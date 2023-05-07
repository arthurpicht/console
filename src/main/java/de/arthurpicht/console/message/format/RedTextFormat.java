package de.arthurpicht.console.message.format;

import com.diogonunes.jcolor.Attribute;

public class RedTextFormat extends ColorFormat {

    @Override
    public Attribute asJColorAttribute() {
        return Attribute.RED_TEXT();
    }

}
