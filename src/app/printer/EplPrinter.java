package app.printer;

import app.PrinterUtils;
import app.model.Etiqueta;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

public class EplPrinter implements TagPrinter {

    @Override
    public String printTagA(List<Etiqueta> etiquetas) {
        String initialConfiguration = "\nN\nq814\nQ203,26\n";
        StringBuilder buffer = new StringBuilder("");

        /* Handling labels with multiple amounts */
        explodeLabelsByAmount(etiquetas);

        /* Creating print buffer */
        for (int i = 0; i < etiquetas.size(); i++) {
            Etiqueta etiqueta = etiquetas.get(i);
            String barcode = etiqueta.getBarcode();
            int barcodeType = etiqueta.getBarcodeType();
            String price = etiqueta.getPrice();
            String identifier = PrinterUtils.removeNotAcceptedCharacters(etiqueta.getIdentifier());
            buffer.append(initialConfiguration)
                    .append("B70,5,0,")
                    .append(barcodeType == Etiqueta.BARCODE_EAN_8 ? "E80" : "E30").append(",2,10,50,B,\"")
                    .append(barcode).append("\"\n")
                    .append("A255,15,0,2,1,1,N,\"")
                    .append(identifier).append("\"\n")
                    .append("A255,35,0,2,1,1,N,\"")
                    .append("R$ ").append(price).append("\"\n")
                    .append("A254,34,0,2,1,1,N,\"")
                    .append("ZT\n")
                    .append("R$ ").append(price).append("\"\n");

            if (i == etiquetas.size() - 1) buffer.append("\nP1,1\n");
        }

        return buffer.toString();
    }

    @Override
    public String printTagB(List<Etiqueta> etiquetas) {
        String initialConfiguration = "\nN\nq814\nQ203,26\n";
        StringBuilder buffer = new StringBuilder("");

        /* Handling labels with multiple amounts */
        explodeLabelsByAmount(etiquetas);

        /* Creating print buffer */
        for (int i = 0; i < etiquetas.size(); i++) {
            Etiqueta etiqueta = etiquetas.get(i);
            boolean isRight = i % 2 == 0;

            String barcode = etiqueta.getBarcode();
            int barcodeType = etiqueta.getBarcodeType();
            String price = etiqueta.getPrice();
            String storeName = etiqueta.getStoreName();
            String productDescription = PrinterUtils.removeNotAcceptedCharacters(etiqueta.getIdentifier());
            buffer.append(isRight ? initialConfiguration : "")
                    .append(isRight ? "A7" : "A440").append(",0,0,3,2,2,N,\"")
                    .append(storeName).append("\"\n")
                    .append(isRight ? "A0" : "A433").append(",50,0,1,1,1,N,\"")
                    .append(productDescription).append("\"\n")
                    .append(isRight ? "B70" : "B510").append(",80,0,")
                    .append(barcodeType == Etiqueta.BARCODE_EAN_8 ? "E80" : "E30").append(",2,10,55,B,\"")
                    .append(barcode).append("\"\n")
                    .append(isRight ? "A0" : "A440").append(",165,0,3,1,1,N,\"Troca somente com esta\"\n")
                    .append(isRight ? "A1" : "A441").append(",166,0,3,1,1,N,\"Troca somente com esta\"\n")
                    .append(isRight ? "A0" : "A440").append(",195,0,3,1,1,N,\"etiqueta - 10 Dias\"\n")
                    .append(isRight ? "A50" : "A490").append(",270,0,3,2,2,N,\"R$ ")
                    .append(price).append("\"\n")
                    .append(isRight ? "A0" : "A440").append(",340,0,1,1,1,N,\"")
                    .append(productDescription).append("\"\n")
                    .append(isRight ? "B70" : "B510").append(",360,0,E30,2,10,55,B,\"")
                    .append(barcode).append("\"\n");

            if (!isRight || i == etiquetas.size() - 1) buffer.append("\nP1,1\n");
        }

        return buffer.toString();
    }

    private void explodeLabelsByAmount(List<Etiqueta> etiquetas) {
        int numEtiquetas = etiquetas.size();
        for (int i = 0; i < numEtiquetas; i++) {
            Etiqueta etiqueta = etiquetas.get(i);
            int amount = etiqueta.getAmount();
            if (amount > 1) {
                if (amount == 2) i++;
                numEtiquetas++;
                etiqueta.setAmount(amount - 1);
                etiquetas.add(i, etiqueta);
            }
        }
    }

}
