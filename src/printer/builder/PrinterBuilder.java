package printer.builder;

import printer.font.FontEnum;

public interface PrinterBuilder {

    PrinterBuilder init(int width, int height);
    PrinterBuilder setFontFamily(FontEnum fontFamily);
    PrinterBuilder setFontSize(int fontSize);
    PrinterBuilder setLineSpacing(int lineSpacing);
    PrinterBuilder setTextAlign(String textAlign);
    PrinterBuilder jumpYPosition(int y);
    PrinterBuilder jumpToYPosition(int y);
    PrinterBuilder jumpLines(int amountOfLines);
    PrinterBuilder addText(String text, int x);
    PrinterBuilder addText(String text, int x, boolean bold);
    PrinterBuilder addBarCode(String seed, int x);
    PrinterBuilder addBarCode(String seed, int x, int height);
    PrinterBuilder addNativeZpl(String zplCode);
    public PrinterBuilder addBarCode(String seed, int x, int width, int height);
    String getZpl();

}
