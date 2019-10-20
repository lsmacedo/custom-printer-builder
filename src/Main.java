import app.model.Etiqueta;
import app.printer.ZplPrinter;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Etiqueta> etiquetas = new ArrayList<Etiqueta>();

        etiquetas.add(new Etiqueta("0123456", "CSR1293", "10,00", 2, Etiqueta.BARCODE_EAN_8, "LUCAS STORE"));

        System.out.println(new ZplPrinter().printTagB(etiquetas));
    }

}