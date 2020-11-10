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

    public void add(String category, int code, int quantity) {
        Product product = MainProcessor.getProductProcessor().getProduct(category, code);
        if (shoppingList.containsKey(product)) {
            shoppingList.put(product, shoppingList.get(product) + quantity);
        } else {
            shoppingList.put(product, quantity);
        }
        this.amount += product.getPrice() * quantity;
    }

    public void set(String category, int code, int newQty) {
        Product product = MainProcessor.getProductProcessor().getProduct(category, code);
        int oldQty = shoppingList.get(product);
        shoppingList.put(product, newQty);
        if (newQty == 0) {
            shoppingList.remove(product);
        }
        this.amount += product.getPrice() * (newQty - oldQty);
    }

    public void payCash(Map<Double, Integer> cashes) {
        PaymentProcessor.payCash(cashes);
    }
}
