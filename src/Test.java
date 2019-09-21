import printer.builder.PrinterBuilder;
import printer.builder.ZplPrinterBuilder;
import printer.model.Etiqueta;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.JobSheets;
import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        PrinterBuilder printerBuilder = new ZplPrinterBuilder();

        String zpl = printerBuilder.init(280, 100)
                // Right
                // CSR123
                .jumpLines(1)
                .jumpYPosition(3)
                .setTextAlign("R")
                .setFontSize(7)
                .addText("CSR123", 0)
                // R$98,00
                .jumpLines(1)
                .addText("R$98,00", 0, false)

                // Left
                .setTextAlign("L")
                .jumpToYPosition(7)
                .addBarCode("21213113", 0, 2, 50)
                .getZpl();

        try {
            try {
                ArrayList<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
                etiquetas.add(new Etiqueta("1234567", "TST0001", "3,99"));
                etiquetas.add(new Etiqueta("1234568", "TST0002", "4,99", 2));
                etiquetas.add(new Etiqueta("1234569", "TST0003", "5,99"));

                imprimirEtiquetas(etiquetas);
            } catch (PrintException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printFunction1(PrintService printService, String text) throws IOException, PrintException {
        //create a print job
        DocPrintJob job = printService.createPrintJob();

        //define the format of print document
        InputStream is = new ByteArrayInputStream(text.getBytes());
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

        //print the data
        Doc doc = new SimpleDoc(is, flavor, null);
        job.print(doc, null);
        is.close();
    }

    private static void imprimirEtiquetas(List<Etiqueta> etiquetas) throws IOException, PrintException {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Number of print services: " + printServices.length);

        PrintService printService = printServices[0];
        System.out.println("Imprimindo em impressora " + printService.getName());

        String initialConfiguration = "\u0002L\nD11\nH14\n";

        StringBuilder buffer = new StringBuilder("");
        for (Etiqueta etiqueta : etiquetas) {
            String barcode = etiqueta.getBarcode();
            String identifier = etiqueta.getIdentifier();
            String price = etiqueta.getPrice();
            buffer.append(initialConfiguration)
                    .append("1G2202000010020")
                    .append(barcode)
                    .append("\n121100000180100")
                    .append(identifier)
                    .append("\n121100000080100R$")
                    .append(price)
                    .append("\n")
                    .append("Q" + String.format("%04d", etiqueta.getAmount()) + "\nE\n\n");
        }

        printFunction1(printService, buffer.append("Q0001\nE\n\n").toString());
    }

}