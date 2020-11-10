package VendingMachine.Window;

public class ProductTableEntry {

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
}
