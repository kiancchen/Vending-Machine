package VendingMachine.Window;

import VendingMachine.Data.Product;

public class ProductTableEntry {
<<<<<<< HEAD

    private String category;
    private String name;
    private String price;
    private String code;
    private String qty;

    public ProductTableEntry(String code, String category, String name, String price, String quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.qty = quantity;
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return qty;
    }
=======
    private Product.Category category;
    private String name;
    private double price;
    private int code;
    private int quantity;
>>>>>>> bba068b21b5ee139148ad14cad2e2c8ae23870d1
}
