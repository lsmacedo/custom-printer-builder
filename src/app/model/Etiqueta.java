package app.model;

public class Etiqueta {

    private String barcode;
    private int barcodeType;
    private String identifier;
    private String price;
    private String storeName;
    private int amount = 1;

    public static final int BARCODE_EAN_8 = 1;
    public static final int BARCODE_EAN_13 = 2;

    public Etiqueta(String barcode, String identifier, String price, int amount, int barcodeType) {
        this.barcode = barcode;
        this.identifier = identifier;
        this.price = price;
        this.amount = amount;
        this.barcodeType = barcodeType;
    }

    public Etiqueta(String barcode, String identifier, String price, int amount, int barcodeType, String storeName) {
        this.barcode = barcode;
        this.price = price;
        this.amount = amount;
        this.barcodeType = barcodeType;
        this.storeName = storeName;
        this.identifier = identifier;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getBarcodeType() {
        return barcodeType;
    }

    public void setBarcodeType(int barcodeType) {
        this.barcodeType = barcodeType;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
