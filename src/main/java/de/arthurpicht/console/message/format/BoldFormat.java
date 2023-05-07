package de.arthurpicht.console.message.format;

import com.diogonunes.jcolor.Attribute;

public class BoldFormat extends ColorFormat {

    @Override
    public Attribute asJColorAttribute() {
        return Attribute.BOLD();
    }

}
