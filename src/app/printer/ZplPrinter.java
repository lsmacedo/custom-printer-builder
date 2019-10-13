package app.printer;

import app.PrinterUtils;
import app.builder.PrinterCodeBuilder;
import app.builder.ZplCodeBuilder;
import app.model.Etiqueta;

import javax.print.PrintException;
import java.io.IOException;
import java.util.List;

public class ZplPrinter implements TagPrinter {

    public String printTagA(List<Etiqueta> etiquetas) {
        PrinterCodeBuilder printerCodeBuilder = new ZplCodeBuilder();

        StringBuilder buffer = new StringBuilder("");
        for (Etiqueta etiqueta : etiquetas) {
            String zpl = printerCodeBuilder.init(280, 100)
                    // Right, Top (Identificador do Item)
                    .setTextAlign("R").jumpLines(1).jumpYPosition(3).setFontSize(7)
                    .addText(PrinterUtils.removeNotAcceptedCharacters(etiqueta.getIdentifier()), 0)
                    // Right, Bottom (Preço do Item)
                    .jumpLines(1)
                    .addText("R$" + etiqueta.getPrice(), 0, false)

                    // Left (Código de Barras)
                    .setTextAlign("L").jumpToYPosition(7)
                    .addBarCode(etiqueta.getBarcode(), 0, 2, 50)
                    .jumpLines(2)

                    /* Obtendo código zpl */
                    .getNativeCode();

            buffer.append(zpl);
        }

        return buffer.toString();

//        try {
//            PrinterUtils.printString(PrinterUtils.getDefaultPrintService(), zpl);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return 1;
//        } catch (PrintException e) {
//            e.printStackTrace();
//            return 2;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 3;
//        }
//
//        return 0;
    }

}
