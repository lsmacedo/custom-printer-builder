import app.model.Etiqueta;
import app.printer.PplaPrinter;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
        etiquetas.add(new Etiqueta("1234567", "TST0001", "3,99", 1, Etiqueta.BARCODE_EAN_8));
        etiquetas.add(new Etiqueta("1234568", "TST0002", "4,99", 2, Etiqueta.BARCODE_EAN_8));
        etiquetas.add(new Etiqueta("1234569", "TST0003", "5,99", 1, Etiqueta.BARCODE_EAN_8));

        int status = new PplaPrinter().printTagA(etiquetas);
        if (status != 0) System.err.println("Erro ao imprimir etiquetas");
        else System.err.println("Etiquetas impressas com sucesso");
    }

}