package app.printer;

import app.PrinterUtils;
import app.model.Etiqueta;

import javax.print.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PplaPrinter implements TagPrinter {

    public String printTagA(List<Etiqueta> etiquetas) {
        String initialConfiguration = "\u0002L\nD11\nH14\n";

        StringBuilder buffer = new StringBuilder("");
        for (Etiqueta etiqueta : etiquetas) {
            String barcode = etiqueta.getBarcode();
            String identifier = PrinterUtils.removeNotAcceptedCharacters(etiqueta.getIdentifier());
            String price = etiqueta.getPrice();
            int amount = etiqueta.getAmount();
            buffer.append(initialConfiguration)
                    .append("1G2202000010020")
                    .append(barcode)
                    .append("\n121100000180100")
                    .append(identifier)
                    .append("\n121100000080100")
                    .append("R$")
                    .append(price)
                    .append("\n").append("Q")
                    .append(String.format("%04d", amount))
                    .append("\nE\n\n");
        }

        buffer.append("Q0001\nE\n\n");
        return buffer.toString();

//        try {
//            PrinterUtils.printString(PrinterUtils.getDefaultPrintService(), buffer.toString());
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
