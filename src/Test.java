import printer.builder.PrinterBuilder;
import printer.builder.ZplPrinterBuilder;

public class Test {

    public static void main(String[] args) {
        PrinterBuilder printerBuilder = new ZplPrinterBuilder();

        String string = printerBuilder.init(600, 400)
        .setFontSize(12)
        .jumpLines(2)
        .setLineSpacing(10)
        .setTextAlign(ZplPrinterBuilder.TEXT_ALIGN_CENTER)
        .addText("LOJA DE TESTE", 0, true)
        .jumpLines(1)
        .jumpYPosition(1)
        .addText("17/09/2019 22:19", 0)
        .setFontSize(8)
        .jumpLines(1)
        .addText("Operador: Lucas Macedo", 0)
        .setFontSize(12)
        .jumpLines(2)
        .setFontSize(16)
        .addText("WHISKY RED", 0, true)
        .jumpLines(1)
        .addText("R$18,00", 0, true)
        .setFontSize(12)
        .jumpLines(1)
        .setFontSize(8)
        .addBarCode("1234567", 0, 6, 100)
        .jumpLines(1)
        .addText("1234567", 0)
        .setFontSize(12)
        .jumpLines(2)
        .setFontSize(10)
        .addText("lucas.macedo@teknisa.com", 0)
        .jumpLines(2)
        .getZpl();

        System.out.println(string);
    }

}