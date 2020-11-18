package VendingMachine.Window.SoldHistory;

public class SoldTableEntry {
    private String code;
    private String name;
    private String category;
    private String price;
    private String sold;
    private int id;


    public SoldTableEntry(String code, String name, String category, String price,
                             String sold, int id) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.sold = sold;
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

    public String getSold() {
        return sold;
    }
}
