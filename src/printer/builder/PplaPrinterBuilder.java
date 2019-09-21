package printer.builder;

import printer.font.FontEnum;

public class PplaPrinterBuilder implements PrinterBuilder {

    private String label;

    private int currentYPosition;
    private int fontSize;
    private int lineSpacing;
    private int width;
    private int height;
    private String textAlign;

    public static final String TEXT_ALIGN_LEFT = "L";
    public static final String TEXT_ALIGN_CENTER = "C";
    public static final String TEXT_ALIGN_RIGHT = "R";

    public static final FontEnum DEFAULT_FONT_FAMILY = FontEnum.ZEBRA_FONT_ZERO;
    public static final int DEFAULT_FONT_SIZE = 10;
    public static final int DEFAULT_LINE_SPACING = 5;
    public static final String DEFAULT_TEXT_ALIGN = TEXT_ALIGN_CENTER;
    public static final int DEFAULT_BARCODE_HEIGHT = DEFAULT_FONT_SIZE * 7;

    public PrinterBuilder init(int width, int height) {
        this.label = "\u0002L\nD11\nH14\n";

        return this;
    }

    public PrinterBuilder setFontFamily(FontEnum fontFamily) {
        return null;
    }

    public PrinterBuilder setFontSize(int fontSize) {
        return null;
    }

    public PrinterBuilder setLineSpacing(int lineSpacing) {
        return null;
    }

    public PrinterBuilder setTextAlign(String textAlign) {
        return null;
    }

    public PrinterBuilder jumpYPosition(int y) {
        return null;
    }

    public PrinterBuilder jumpToYPosition(int y) {
        return null;
    }

    public PrinterBuilder jumpLines(int amountOfLines) {
        return null;
    }

    public PrinterBuilder addText(String text, int x) {
        return null;
    }

    public PrinterBuilder addText(String text, int x, boolean bold) {
        return null;
    }

    public PrinterBuilder addBarCode(String seed, int x) {
        return null;
    }

    public PrinterBuilder addBarCode(String seed, int x, int height) {
        return null;
    }

    public PrinterBuilder addNativeZpl(String zplCode) {
        return null;
    }

    public PrinterBuilder addBarCode(String seed, int x, int width, int height) {
        return null;
    }

    public String getZpl() {
        return null;
    }
}
