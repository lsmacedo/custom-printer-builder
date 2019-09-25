package app;

import javax.print.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PrinterUtils {

    public static PrintService getDefaultPrintService() {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

        return printServices != null ? printServices[0] : null;
    }

    public static void printString(PrintService printService, String text) throws IOException, PrintException, Exception {
        if (printService == null) throw new Exception();

        // Create a print job
        DocPrintJob job = printService.createPrintJob();

        // Define the format of print document
        InputStream is = new ByteArrayInputStream(text.getBytes());
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

        // Print the data
        Doc doc = new SimpleDoc(is, flavor, null);
        job.print(doc, null);
        is.close();
    }

}
