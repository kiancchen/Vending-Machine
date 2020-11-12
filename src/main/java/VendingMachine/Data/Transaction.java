package VendingMachine.Data;

import VendingMachine.Processor.MainProcessor;
import VendingMachine.Processor.PaymentProcessor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Transaction {
    private Map<Product, Integer> shoppingList;
    private LocalDateTime date;
    private boolean success;
    private double amount;
    private double receivedMoney;

    public Transaction() {
        shoppingList = new HashMap<>();
        success = false;
        amount = 0;
    }

    public boolean add(String category, int code, int quantity) {
        Product product = MainProcessor.getProductProcessor().getProduct(category, code);
        if (quantity > product.getQuantity()) {
            return false;
        }
        if (shoppingList.containsKey(product)) {
            shoppingList.put(product, shoppingList.get(product) + quantity);
        } else {
            shoppingList.put(product, quantity);
        }
        product.setQuantity(product.getQuantity() - quantity);
        this.amount += product.getPrice() * quantity;
        return true;
    }

    public boolean set(String category, int code, int newQty) {
        Product product = MainProcessor.getProductProcessor().getProduct(category, code);
        if (newQty > product.getQuantity()) {
            return false;
        }
        int oldQty = shoppingList.get(product);
        shoppingList.put(product, newQty);
        if (newQty == 0) {
            shoppingList.remove(product);
        }
        product.setQuantity(product.getQuantity() + (oldQty - newQty));
        this.amount += product.getPrice() * (newQty - oldQty);
        return true;
    }

    public double getAmount() {
        return amount;
    }

    public Map<Product, Integer> getShoppingList() {
        return shoppingList;
    }
}
