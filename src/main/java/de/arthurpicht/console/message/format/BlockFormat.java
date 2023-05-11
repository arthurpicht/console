package de.arthurpicht.console.message.format;

public class BlockFormat extends Format {

    public enum Align { RIGHT, LEFT, CENTER }

    public static class Builder {
        private final int width;
        private Align align;
        private String abbreviationSign;
        private boolean expandTextEffects;

        public Builder(int width) {
            this.width = width;
            this.align = Align.LEFT;
            this.abbreviationSign = "";
            this.expandTextEffects = false;
        }

        public Builder withAlign(Align align) {
            this.align = align;
            return this;
        }

        public Builder withAbbreviationSign(String string) {
            this.abbreviationSign = string;
            return this;
        }

        public Builder withExpandedTextEffects() {
            this.expandTextEffects = true;
            return this;
        }

        public BlockFormat build() {
            return new BlockFormat(this.width, this.align, this.abbreviationSign, this.expandTextEffects);
        }
    }

    private final int width;
    private final Align align;
    private final String abbreviationSign;
    private final boolean expandTextEffects;

    public static Builder builder(int width) {
        return new Builder(width);
    }

    public BlockFormat(int width, Align align, String abbreviationSign, boolean expandTextEffects) {
        this.width = width;
        this.align = align;
        this.abbreviationSign = abbreviationSign;
        this.expandTextEffects = expandTextEffects;
    }

    public int getWidth() {
        return this.width;
    }

    public Align getAlign() {
        return this.align;
    }

    public String getAbbreviationSign() {
        return this.abbreviationSign;
    }

    public boolean isExpandTextEffects() {
        return this.expandTextEffects;
    }

}
