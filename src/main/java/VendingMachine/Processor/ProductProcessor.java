package VendingMachine.Processor;

import VendingMachine.Data.Product;
import VendingMachine.DatabaseHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ProductProcessor {
    private Map<Product.Category, List<Product>> productMap;

    public ProductProcessor() throws IOException {
        productMap = DatabaseHandler.loadProductData();
    }

    public Product getProduct(String category, int code) {
        List<Product> products = productMap.get(Product.Category.valueOf(category));
        for (Product product : products) {
            if (product.getCode() == code) {
                return product;
            }
        }
        return null;
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

    public boolean addProduct(int code, String category, String name, double price, int quantity) throws IOException {
        for (List<Product> products : productMap.values()) {
            for (Product product : products) {
                if (product.getCode() == code || (product.getCategory().equals(Product.Category.valueOf(category)) && product.getName().equals(name))) {
                    return false;
                }
            }
        }

        List<Product> products = productMap.get(Product.Category.valueOf(category));
        products.add(new Product(code, Product.Category.valueOf(category), name, price, quantity));
        DatabaseHandler.saveProductData(productMap);
        return true;
    }

    public boolean removeProduct(String category, int code) throws IOException {
        List<Product> products = productMap.get(Product.Category.valueOf(category));
        for (Product product : products) {
            if (product.getCode() == code) {
                products.remove(product);
                DatabaseHandler.saveProductData(productMap);
                return true;
            }
        }
        return false;
    }

    public boolean setProductQuantity(String category, int code, int quantity) throws IOException {
        if (quantity < 0 || quantity > 15) {
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
            if (product.getName().equals(newName)) {
                return false;
            }
        }
        for (Product product : products) {
            if (product.getCode() == code) {
                product.setName(newName);
                DatabaseHandler.saveProductData(productMap);
                return true;
            }
        }
        return false;
    }

    public boolean setProductCategory(String oldCategory, int code, String newCategory) throws IOException {
        List<Product> products = productMap.get(Product.Category.valueOf(oldCategory));
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

    public boolean setProductCode(String category, int oldCode, int newCode) throws IOException {
        for (List<Product> products : productMap.values()) {
            for (Product product : products) {
                if (product.getCode() == newCode) {
                    return false;
                }
            }
        }

        List<Product> products = productMap.get(Product.Category.valueOf(category));
        for (Product product : products) {
            if (product.getCode() == oldCode) {
                product.setCode(newCode);
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