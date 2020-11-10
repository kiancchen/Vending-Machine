package VendingMachine.Data;

public class Product {
    private static int staticCode = 1;
    private Category category;
    private String name;
    private double price;
    private int code;
    private int quantity;

    public Product(Category category, String name, double price, int quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.code = staticCode++;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public enum Category {
        DRINK,
        CHOCOLATE,
        CHIP,
        CANDY
    }
}
