package VendingMachine.Window.ProductManagement;

public class ProductTableEntry {

    private String code;
    private String name;
    private String category;
    private String price;
    private String quantity;
    private int id;


    public ProductTableEntry(String code, String name, String category, String price,
                             String quantity, int id) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }
}
