package VendingMachine.Processor;

import VendingMachine.Data.Product;
import VendingMachine.DatabaseHandler;

import java.io.IOException;
import java.util.Map;

public class ProductProcessor {
    private static ProductProcessor productProcessor;
    private final Map<Integer, Product> productMap;

    private ProductProcessor() throws IOException {
        productMap = DatabaseHandler.loadProductData();
    }

    public static ProductProcessor getInstance() throws IOException {
        if (productProcessor == null) {
            productProcessor = new ProductProcessor();
        }
        return productProcessor;
    }

    public static ProductProcessor reload() throws IOException {
        productProcessor = new ProductProcessor();
        return productProcessor;
    }

    public Product getProduct(int id) {
        return this.productMap.get(id);
    }

    public boolean addProduct(String code, String category, String name, double price,
                              int stock) throws IOException {
        if (stock > 15 || stock < 0) {
            return false;
        }
        for (Product product : this.productMap.values()) {
            if (product.getCode().equals(code) || product.getName().equals(name)) {
                return false;
            }
        }

        Product product = new Product(code, Product.Category.valueOf(category), name, price, stock);
        productMap.put(product.getId(), product);
        save();
        return true;
    }

    public boolean removeProduct(int id) throws IOException {
        if (!this.productMap.containsKey(id)) {
            return false;
        }
        this.productMap.remove(id);
        save();
        return true;
    }

    public boolean setProductStock(int id, int stock) throws IOException {
        if (stock < 0 || stock > 15) {
            return false;
        }
        this.productMap.get(id).setStock(stock);
        save();
        return true;
    }

    public boolean setProductName(int id, String newName) throws IOException {
        for (Product product : this.productMap.values()) {
            if (product.getId() != id && product.getName().equals(newName)) {
                return false;
            }
        }
        this.productMap.get(id).setName(newName);
        save();
        return true;
    }

    public boolean setProductCategory(int id, String newCategory) throws IOException {
        this.productMap.get(id).setCategory(Product.Category.valueOf(newCategory));
        save();
        return true;
    }

    public boolean setProductPrice(int id, double price) throws IOException {
        this.productMap.get(id).setPrice(price);
        save();
        return true;
    }

    public boolean setProductCode(int id, String newCode) throws IOException {
        for (Product product : this.productMap.values()) {
            if (product.getId() != id && product.getCode().equals(newCode)) {
                return false;
            }
        }
        this.productMap.get(id).setCode(newCode);
        save();
        return true;
    }

    public void save() throws IOException {
        DatabaseHandler.saveProductData(productMap);
    }

    public Map<Integer, Product> getProductMap() {
        return productMap;
    }
}