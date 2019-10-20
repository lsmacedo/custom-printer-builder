package app.printer;

import app.PrinterUtils;
import app.builder.PrinterCodeBuilder;
import app.builder.ZplCodeBuilder;
import app.model.Etiqueta;

import java.util.List;

import static app.builder.ZplCodeBuilder.*;

public class ZplPrinter implements TagPrinter {

    public String printTagA(List<Etiqueta> etiquetas) {
        PrinterCodeBuilder printerCodeBuilder = new ZplCodeBuilder();
        explodeLabelsByAmount(etiquetas);

        StringBuilder buffer = new StringBuilder("");
        for (Etiqueta etiqueta : etiquetas) {
            String zpl = printerCodeBuilder.init(280, 100)
                    // Right, Top (Identificador do Item)
                    .setTextAlign(TEXT_ALIGN_RIGHT).jumpLines(1).jumpYPosition(3).setFontSize(5)
                    .addText(PrinterUtils.removeNotAcceptedCharacters(etiqueta.getIdentifier()), 0)
                    // Right, Bottom (Preço do Item)
                    .jumpLines(1)
                    .addText("R$ " + etiqueta.getPrice(), 0, false)

                    // Left (Código de Barras)
                    .setTextAlign(TEXT_ALIGN_LEFT).jumpToYPosition(7)
                    .addBarCode(etiqueta.getBarcode(), 0, 2, 50)
                    .jumpLines(2)

                    /* Obtendo código zpl */
                    .getNativeCode();

            buffer.append(zpl);
        }

        return buffer.toString();
    }

    @Override
    public String printTagB(List<Etiqueta> etiquetas) {
        PrinterCodeBuilder printerCodeBuilder = new ZplCodeBuilder();
        explodeLabelsByAmount(etiquetas);

        StringBuilder buffer = new StringBuilder("");
        for (Etiqueta etiqueta : etiquetas) {
            String zpl = printerCodeBuilder.init(350, 700)
                    // Parte 1
                    // Center, Top (Nome da Loja)
                    .setTextAlign(TEXT_ALIGN_CENTER).setFontSize(14).jumpLines(1).jumpYPosition(3)
                    .addText(PrinterUtils.removeNotAcceptedCharacters(etiqueta.getStoreName()), 0)
                    // Left, Top (Identificador do Item)
                    .setTextAlign(TEXT_ALIGN_LEFT).jumpLines(1).setFontSize(5)
                    .addText(PrinterUtils.removeNotAcceptedCharacters(etiqueta.getIdentifier()), 0, false)

                    // Center, Middle (Código de Barras)
                    .setTextAlign(TEXT_ALIGN_CENTER).jumpLines(1)
                    .addBarCode(etiqueta.getBarcode(), 120, 2, 50)
                    .jumpLines(2)

                    // Bottom, Left (Troca somente com esta etiqueta)
                    .setTextAlign(TEXT_ALIGN_LEFT).jumpYPosition(50).setFontSize(7)
                    .addText("Troca somente com esta", 0, true).jumpLines(1)
                    .addText("etiqueta - 10 dias", 0, true)

                    // Parte 2
                    // Center, Middle (Preço)
                    .setTextAlign(TEXT_ALIGN_CENTER).jumpLines(1).jumpYPosition(80).setFontSize(14)
                    .addText("R$ " + etiqueta.getPrice(), 0, true)

                    // Parte 3
                    // Top, Left (Identificador do Item)
                    .setTextAlign(TEXT_ALIGN_LEFT).jumpLines(1).setFontSize(7).jumpLines(1)
                    .addText(PrinterUtils.removeNotAcceptedCharacters(etiqueta.getIdentifier()), 0, false)
                    // Bottom, Middle (Código de Barras)
                    .setTextAlign(TEXT_ALIGN_CENTER).jumpLines(1)
                    .addBarCode(etiqueta.getBarcode(), 120, 2, 50)
                    .jumpLines(2)

                    /* Obtendo código zpl */
                    .getNativeCode();

            buffer.append(zpl);
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
