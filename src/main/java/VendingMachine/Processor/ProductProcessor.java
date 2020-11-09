package VendingMachine.Processor;

import VendingMachine.Data.Product;
import VendingMachine.DatabaseHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductProcessor {
    private Map<Product.Category, List<Product>> productMap;

    public ProductProcessor() throws IOException {
        productMap = new HashMap<>();
        productMap.put(Product.Category.DRINK, new ArrayList<>());
        productMap.put(Product.Category.CHOCOLATE, new ArrayList<>());
        productMap.put(Product.Category.CHIP, new ArrayList<>());
        productMap.put(Product.Category.CANDY, new ArrayList<>());
    }

    public boolean addProduct(String category, String name, double price, int quantity) throws IOException {
        List<Product> products = productMap.get(Product.Category.valueOf(category));
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return false;
            }
        }
        products.add(new Product(Product.Category.valueOf(category), name, price, quantity));
        DatabaseHandler.saveProductData(productMap);
        return true;
    }

    public boolean setProductQuantity(String category, int code, int quantity) throws IOException {
        if (quantity > 15){
            return false;
        }

        List<Product> products = productMap.get(Product.Category.valueOf(category));
        for (Product product : products) {
            if (product.getCode() == code) {
                product.setQuantity(quantity);
                DatabaseHandler.saveProductData(productMap);
                return true;
            }
        }
        return false;
    }

    public boolean setProductName(String category, int code, String newName) throws IOException {
        List<Product> products = productMap.get(Product.Category.valueOf(category));
        for (Product product : products) {
            if (product.getCode() == code) {
                product.setName(newName);
                DatabaseHandler.saveProductData(productMap);
                return true;
            }
        }
        return false;
    }

    public boolean setProductCategory(String category, int code, String newCategory) throws IOException {
        List<Product> products = productMap.get(Product.Category.valueOf(category));
        for (Product product : products) {
            if (product.getCode() == code) {
                // remove from the old category
                products.remove(product);
                product.setCategory(Product.Category.valueOf(newCategory));
                // add to the new category
                products = productMap.get(Product.Category.valueOf(newCategory));
                products.add(product);
                DatabaseHandler.saveProductData(productMap);
                return true;
            }
        }
        return false;
    }

    public boolean setProductPrice(String category, int code, double price) throws IOException {
        List<Product> products = productMap.get(Product.Category.valueOf(category));
        for (Product product : products) {
            if (product.getCode() == code) {
                product.setPrice(price);
                DatabaseHandler.saveProductData(productMap);
                return true;
            }
        }
        return false;
    }

    public Map<Product.Category, List<Product>> getProductMap() {
        return productMap;
    }
}
