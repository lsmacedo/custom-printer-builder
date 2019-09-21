package printer;

public class PrinterUtils {

    public static int arithmeticProgressionAtPositionN(int a1, int d, int n) {
        if (n == 1) return a1;
        else return a1 + ((n - 1) * d);
    }

}
