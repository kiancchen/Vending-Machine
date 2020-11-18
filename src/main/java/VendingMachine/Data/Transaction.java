package VendingMachine.Data;


import VendingMachine.Processor.ProductProcessor;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transaction {
    private static List<Transaction> transactionList;
    private Map<Product, Integer> shoppingList;
    private LocalDate date;
    private Status status;
    private double totalPrice;
    private double paidAmount;
    private ProductProcessor productProcessor;
    private String reason;

    static {
        transactionList = new ArrayList<>();
    }

    public Transaction() {
        try {
            productProcessor = ProductProcessor.getInstance();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Can't get the product processor.");
            alert.show();
        }
        shoppingList = new HashMap<>();
        status = Status.UNPAID;
        totalPrice = 0;
    }

    public boolean add(int id, int quantity) {
        Product product = productProcessor.getProduct(id);
        if (quantity > product.getStock()) {
            return false;
        }
        if (shoppingList.containsKey(product)) {
            shoppingList.put(product, shoppingList.get(product) + quantity);
        } else {
            shoppingList.put(product, quantity);
        }
        product.setStock(product.getStock() - quantity);
        this.totalPrice += product.getPrice() * quantity;
        return true;
    }

    public boolean set(int id, int newQty) {
        Product product = productProcessor.getProduct(id);
        if (newQty > product.getStock() + shoppingList.get(product)) {
            return false;
        }
        int oldQty = shoppingList.get(product);
        shoppingList.put(product, newQty);
        if (newQty == 0) {
            shoppingList.remove(product);
        }
        product.setStock(product.getStock() + (oldQty - newQty));
        this.totalPrice += product.getPrice() * (newQty - oldQty);
        return true;
    }

    public boolean pay(double amount) {
        if (amount < this.totalPrice) {
            return false;
        }
        this.paidAmount = amount;
        status = Status.PAID;
        transactionList.add(this);
        shoppingList.forEach((product, soldNum) -> product.sold(soldNum));
        date = LocalDate.now();
        return true;
    }

    public boolean cancel(String reason) {
        status = Status.CANCELLED;
        this.reason = reason;
        return true;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public Map<Product, Integer> getShoppingList() {
        return shoppingList;
    }

    public static List<Transaction> getTransactionList() {
        return transactionList;
    }

    public LocalDate getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    enum Status {
        PAID,
        UNPAID,
        CANCELLED
    }
}
