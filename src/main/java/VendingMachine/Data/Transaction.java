package VendingMachine.Data;

import VendingMachine.Processor.MainProcessor;

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

    public boolean add(int id, int quantity) {
        Product product = MainProcessor.getProductProcessor().getProduct(id);
        if (quantity > product.getStock()) {
            return false;
        }
        if (shoppingList.containsKey(product)) {
            shoppingList.put(product, shoppingList.get(product) + quantity);
        } else {
            shoppingList.put(product, quantity);
        }
        product.setStock(product.getStock() - quantity);
        this.amount += product.getPrice() * quantity;
        System.out.println(this.amount);
        return true;
    }

    public boolean set(int id, int newQty) {
        Product product = MainProcessor.getProductProcessor().getProduct(id);
        if (newQty > product.getStock() + shoppingList.get(product)) {
            System.out.println(false);
            return false;
        }
        int oldQty = shoppingList.get(product);
        shoppingList.put(product, newQty);
        if (newQty == 0) {
            shoppingList.remove(product);
        }
        product.setStock(product.getStock() + (oldQty - newQty));
        this.amount += product.getPrice() * (newQty - oldQty);
        return true;
    }

    public boolean pay(double amount) {
        this.receivedMoney = amount;
        return true;
    }

    public double getAmount() {
        return amount;
    }

    public double getReceivedMoney() {
        return receivedMoney;
    }

    public Map<Product, Integer> getShoppingList() {
        return shoppingList;
    }
}
