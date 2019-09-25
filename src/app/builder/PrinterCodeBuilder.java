package app.builder;

import app.font.FontEnum;

public interface PrinterCodeBuilder {

    PrinterCodeBuilder init(int width, int height);
    PrinterCodeBuilder setFontFamily(FontEnum fontFamily);
    PrinterCodeBuilder setFontSize(int fontSize);
    PrinterCodeBuilder setLineSpacing(int lineSpacing);
    PrinterCodeBuilder setTextAlign(String textAlign);
    PrinterCodeBuilder jumpYPosition(int y);
    PrinterCodeBuilder jumpToYPosition(int y);
    PrinterCodeBuilder jumpLines(int amountOfLines);
    PrinterCodeBuilder addText(String text, int x);
    PrinterCodeBuilder addText(String text, int x, boolean bold);
    PrinterCodeBuilder addBarCode(String seed, int x);
    PrinterCodeBuilder addBarCode(String seed, int x, int height);
    PrinterCodeBuilder addNativeZpl(String zplCode);
    PrinterCodeBuilder addBarCode(String seed, int x, int width, int height);
    String getNativeCode();

}
