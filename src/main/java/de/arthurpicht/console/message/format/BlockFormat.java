package de.arthurpicht.console.message.format;

public class BlockFormat extends Format {

    public enum Align { RIGHT, LEFT, CENTER }
    public enum OverflowStrategy { LIMIT, ABBREVIATE, EXPAND }

    public static class Builder {
        private int width;
        private Align align;
        private boolean expandTextEffects;
        private OverflowStrategy overflowStrategy;
        private String abbreviationSign;

        public Builder() {
            this.width = -1;
            this.align = Align.LEFT;
            this.expandTextEffects = false;
            this.overflowStrategy = OverflowStrategy.LIMIT;
            this.abbreviationSign = "";
        }

        public Builder withWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder withAlign(Align align) {
            this.align = align;
            return this;
        }

        public Builder withAbbreviationSign(String string) {
            this.overflowStrategy = OverflowStrategy.ABBREVIATE;
            this.abbreviationSign = string;
            return this;
        }

        public Builder withExpandedTextEffects() {
            this.expandTextEffects = true;
            return this;
        }

        public Builder withOverflowStrategy(OverflowStrategy overflowStrategy) {
            this.overflowStrategy = overflowStrategy;
            return this;
        }

        public BlockFormat build() {
            if (this.width == -1) throw new IllegalArgumentException("Width must be set");
            return new BlockFormat(this.width, this.align, this.expandTextEffects, this.overflowStrategy, this.abbreviationSign);
        }
    }

    private final int width;
    private final Align align;
    private final boolean expandTextEffects;
    private final OverflowStrategy overflowStrategy;
    private final String abbreviationSign;

    public BlockFormat(int width, Align align, boolean expandTextEffects, OverflowStrategy overflowStrategy, String abbreviationSign) {
        this.width = width;
        this.align = align;
        this.abbreviationSign = abbreviationSign;
        this.overflowStrategy = overflowStrategy;
        this.expandTextEffects = expandTextEffects;
    }

    public int getWidth() {
        return this.width;
    }

    public Align getAlign() {
        return this.align;
    }

    public boolean isExpandTextEffects() {
        return this.expandTextEffects;
    }

    public OverflowStrategy getOverflowStrategy() {
        return this.overflowStrategy;
    }

    public String getAbbreviationSign() {
        return this.abbreviationSign;
    }

}
