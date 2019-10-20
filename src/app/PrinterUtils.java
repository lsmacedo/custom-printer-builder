package app;

import javax.print.*;
import java.io.*;
import java.text.Normalizer;

public class PrinterUtils {

    public static String removeNotAcceptedCharacters(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static PrintService getDefaultPrintService() {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            System.out.println(printService.getName());
        }

        return printServices != null ? printServices[0] : null;
    }

    public static void printString(PrintService printService, String commands) throws IOException, PrintException, Exception {
        if (printService == null) throw new Exception();

        FileOutputStream fos = null;
        try {
            PrintStream ps = new PrintStream(new FileOutputStream("/dev/usb/lp0"));

            ps.println(commands);
            ps.print("\f");
            ps.flush();
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
