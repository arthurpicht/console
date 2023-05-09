package de.arthurpicht.console.message.format;

import com.diogonunes.jcolor.Attribute;

public class JColorSimpleAttribute extends Attribute {

    private final String code;

    public JColorSimpleAttribute(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }

}
