package VendingMachine.Processor;

import VendingMachine.Data.Product;
import VendingMachine.DatabaseHandler;

import java.io.IOException;
import java.util.List;

public class ProductProcessor {
    private List<Product> products;

    public ProductProcessor() throws IOException {
        products = DatabaseHandler.loadProductData();
    }

    public boolean addProduct(Product.Category category, String name, double price, int quantity) {
        return false;
    }

    public boolean setProductQuantity(int code, int quantity) {
        return false;
    }

    public boolean setProductName(int code, String newName) {
        return false;
    }

    public boolean setProductCategory(int code, String category) {
        return false;
    }

    public boolean setProductPrice(int code, double price) {
        return false;
    }

}
