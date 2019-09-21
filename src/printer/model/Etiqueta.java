package printer.model;

public class Etiqueta {

    private String barcode;
    private String identifier;
    private String price;
    private int amount = 1;

    public Etiqueta(String barcode, String identifier, String price) {
        this.barcode = barcode;
        this.identifier = identifier;
        this.price = price;
    }

    public Etiqueta(String barcode, String identifier, String price, int amount) {
        this.barcode = barcode;
        this.identifier = identifier;
        this.price = price;
        this.amount = amount;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
