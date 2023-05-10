package de.arthurpicht.console.message.format;

public class BlockFormat extends Format {

    public enum Align { RIGHT, LEFT, CENTER }

    public static class Builder {
        private final int width;
        private BlockFormat.Align align;
        private String abbreviationSign;

        public Builder(int width) {
            this.width = width;
            this.align = BlockFormat.Align.LEFT;
            this.abbreviationSign = "";
        }

        public BlockFormat.Builder withAlign(BlockFormat.Align align) {
            this.align = align;
            return this;
        }

        public BlockFormat.Builder withAbbreviationSign(String string) {
            this.abbreviationSign = string;
            return this;
        }

        public BlockFormat build() {
            return new BlockFormat(this.width, this.align, this.abbreviationSign);
        }
    }

    private final int width;
    private final BlockFormat.Align align;
    private final String abbreviationSign;

    public static Builder builder(int width) {
        return new Builder(width);
    }

    public BlockFormat(int width, BlockFormat.Align align, String abbreviationSign) {
        this.width = width;
        this.align = align;
        this.abbreviationSign = abbreviationSign;
    }

    public int getWidth() {
        return this.width;
    }

    public BlockFormat.Align getAlign() {
        return this.align;
    }

    public String getAbbreviationSign() {
        return this.abbreviationSign;
    }

}
