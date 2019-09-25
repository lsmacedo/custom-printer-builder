package app.builder;

import app.font.FontEnum;

public class PplaCodeBuilder implements PrinterCodeBuilder {

    private String ppla;

    public PrinterCodeBuilder init(int width, int height) {
        return this;
    }

    public PrinterCodeBuilder setFontFamily(FontEnum fontFamily) {
        return this;
    }

    public PrinterCodeBuilder setFontSize(int fontSize) {
        return this;
    }

    public PrinterCodeBuilder setLineSpacing(int lineSpacing) {
        return this;
    }

    public PrinterCodeBuilder setTextAlign(String textAlign) {
        return this;
    }

    public PrinterCodeBuilder jumpYPosition(int y) {
        return this;
    }

    public PrinterCodeBuilder jumpToYPosition(int y) {
        return this;
    }

    public PrinterCodeBuilder jumpLines(int amountOfLines) {
        return this;
    }

    public PrinterCodeBuilder addText(String text, int x) {
        return this;
    }

    public PrinterCodeBuilder addText(String text, int x, boolean bold) {
        return this;
    }

    public PrinterCodeBuilder addBarCode(String seed, int x) {
        return this;
    }

    public PrinterCodeBuilder addBarCode(String seed, int x, int height) {
        return this;
    }

    public PrinterCodeBuilder addNativeZpl(String zplCode) {
        return this;
    }

    public PrinterCodeBuilder addBarCode(String seed, int x, int width, int height) {
        return this;
    }

    public String getNativeCode() {
        return ppla;
    }
}
