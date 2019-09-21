package printer.builder;

import fr.w3blog.zpl.constant.ZebraFont;
import fr.w3blog.zpl.model.ZebraLabel;
import fr.w3blog.zpl.model.element.ZebraBarCode39;
import fr.w3blog.zpl.model.element.ZebraNativeZpl;
import fr.w3blog.zpl.model.element.ZebraText;
import printer.PrinterUtils;
import printer.font.FontEnum;

public class ZplPrinterBuilder implements PrinterBuilder {

    private ZebraLabel label;

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
        label = new ZebraLabel(width, height);

        this.width = width;
        this.height = height;

        setFontFamily(DEFAULT_FONT_FAMILY);
        setFontSize(DEFAULT_FONT_SIZE);
        setLineSpacing(DEFAULT_LINE_SPACING);
        setTextAlign(DEFAULT_TEXT_ALIGN);

        return this;
    }

    public PrinterBuilder setFontFamily(FontEnum fontFamily) {
        switch (fontFamily) {
            case ZEBRA_FONT_A:
                label.setDefaultZebraFont(ZebraFont.ZEBRA_A);
                break;
            case ZEBRA_FONT_B:
                label.setDefaultZebraFont(ZebraFont.ZEBRA_B);
                break;
            case ZEBRA_FONT_C:
                label.setDefaultZebraFont(ZebraFont.ZEBRA_C);
                break;
            case ZEBRA_FONT_D:
                label.setDefaultZebraFont(ZebraFont.ZEBRA_D);
                break;
            case ZEBRA_FONT_F:
                label.setDefaultZebraFont(ZebraFont.ZEBRA_F);
                break;
            case ZEBRA_FONT_G:
                label.setDefaultZebraFont(ZebraFont.ZEBRA_G);
                break;
            case ZEBRA_FONT_ZERO:
            default:
                label.setDefaultZebraFont(ZebraFont.ZEBRA_ZERO);
        }

        return this;
    }

    public PrinterBuilder setFontSize(int fontSize) {
        this.fontSize = fontSize;

        return this;
    }

    public PrinterBuilder setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;

        return this;
    }

    public PrinterBuilder setTextAlign(String textAlign) {
        this.textAlign = textAlign;

        return this;
    }

    public PrinterBuilder jumpYPosition(int y) {
        this.currentYPosition += y;

        return this;
    }

    public PrinterBuilder jumpToYPosition(int y) {
        this.currentYPosition = y;

        return this;
    }

    public PrinterBuilder jumpLines(int amountOfLines) {
        this.currentYPosition += (amountOfLines * 3 * fontSize) + lineSpacing;

        return this;
    }

    public PrinterBuilder addText(String text, int x) {
        label.addElement(new ZebraNativeZpl("^FB" + width + ",1,0," + textAlign));
        label.addElement(new ZebraText(x, currentYPosition, text, fontSize));

        return this;
    }

    public PrinterBuilder addText(String text, int x, boolean bold) {
        label.addElement(new ZebraNativeZpl("^FB" + width + ",1,0," + textAlign));
        label.addElement(new ZebraText(x, currentYPosition, text, fontSize));
        if (bold) {
            label.addElement(new ZebraNativeZpl("^FB" + width + ",1,0," + textAlign));
            label.addElement(new ZebraText(x + 1, currentYPosition, text, fontSize));
            label.addElement(new ZebraNativeZpl("^FB" + width + ",1,0," + textAlign));
            label.addElement(new ZebraText(x, currentYPosition + 1, text, fontSize));
        }
        return this;
    }

    public PrinterBuilder addBarCode(String seed, int x) {
        jumpYPosition((DEFAULT_BARCODE_HEIGHT / 2) + lineSpacing);

        label.addElement(new ZebraNativeZpl("^FB600,1,0," + textAlign));
        label.addElement(new ZebraBarCode39(x, currentYPosition, seed, DEFAULT_BARCODE_HEIGHT));

        return this;
    }

    public PrinterBuilder addBarCode(String seed, int x, int height) {
        jumpYPosition(height);
        label.addElement(new ZebraBarCode39(x, currentYPosition, seed, height));

        return this;
    }

    public PrinterBuilder addBarCode(String seed, int x, int width, int height) {
        jumpYPosition(height);
        label.addElement(new ZebraBarCode39(x, currentYPosition, seed, height, width, 2));

        return this;
    }

    public PrinterBuilder addNativeZpl(String zplCode) {
        label.addElement(new ZebraNativeZpl(zplCode));

        return this;
    }

    public String getZpl() {
        return label.getZplCode().replace("^B3", "^B8");
    }

}
