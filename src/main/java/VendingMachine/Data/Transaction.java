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
    private LocalDateTime date;
    private Status status;
    private double totalPrice;
    private double paidAmount;
    private Map<Double, Integer> returnedChangeMap;
    private Payment payment;
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

    public static List<Transaction> getTransactionList() {
        return transactionList;
    }

    public boolean add(int id, int quantity) {
        Product product = productProcessor.getProduct(id);
        if (shoppingList.containsKey(product)) {
            if (shoppingList.get(product) + quantity > product.getStock()) {
                return false;
            }
            shoppingList.put(product, shoppingList.get(product) + quantity);
        } else {
            if (quantity > product.getStock()) {
                return false;
            }
            shoppingList.put(product, quantity);
        }
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

    public boolean pay(double amount, Payment payment) {
        if (amount < this.totalPrice) {
            return false;
        }
        this.payment = payment;
        this.paidAmount = amount;
        this.status = Status.PAID;
        transactionList.add(this);
        this.shoppingList.forEach((product, soldNum) -> product.sold(soldNum));
        this.date = LocalDateTime.now();

        shoppingList.forEach((product, qty) -> {
            product.setStock(product.getStock() - qty);
        });
        return true;
    }

    public boolean cancel(String reason) {
        status = Status.CANCELLED;
        this.reason = reason;
        return true;
    }

    public Map<Double, Integer> getReturnedChangeMap() {
        return returnedChangeMap;
    }

    public Payment getPayment() {
        return payment;
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

    public LocalDateTime getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public enum Status {
        PAID,
        UNPAID,
        CANCELLED
    }

    public enum Payment {
        CASH,
        CARD
    }
}
