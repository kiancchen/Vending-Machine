package VendingMachine.Data;


import VendingMachine.DatabaseHandler;
import VendingMachine.Processor.CashProcessor;
import VendingMachine.Processor.ProductProcessor;
import VendingMachine.Processor.UserProcessor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transaction {
    private static List<Transaction> transactionList;
    private LocalDateTime date;
    private final Map<Integer, Integer> shoppingList;
    private Status status;
    private double totalPrice;
    private double paidAmount;
    private Map<Double, Integer> returnedChangeMap;
    private Payment payment;
    private String reason;
    private double change;
    private int userId;

    public static void load() throws IOException {
        transactionList = DatabaseHandler.loadTransactionData();
    }

    public Transaction(int userId) {
        shoppingList = new HashMap<>();
        status = Status.UNPAID;
        totalPrice = 0;
        this.userId = userId;
        this.date = LocalDateTime.now();
    }

    public boolean set(int id, int newQty) {
        Product product = ProductProcessor.getInstance().getProduct(id);
        int oldQty = 0;
        if (shoppingList.containsKey(id)) {
            oldQty = shoppingList.get(id);
        }

        if (newQty > product.getStock()) {
            return false;
        }

        shoppingList.put(id, newQty);
        if (newQty == 0) {
            shoppingList.remove(id);
        }
        this.totalPrice += product.getPrice() * (newQty - oldQty);
        return true;
    }

    public int pay(double amount, Payment payment) {
        if (amount < this.totalPrice) {
            // money not enough
            return 1;
        }
        this.change = amount - totalPrice;
        if (payment == Payment.CASH) {
            this.returnedChangeMap = CashProcessor.getInstance().getChange(change);
            if (returnedChangeMap == null) {
                // no available changes
                return 2;
            }
        }else{
            this.returnedChangeMap = new HashMap<>();
        }
        this.payment = payment;
        this.paidAmount = amount;
        this.status = Status.PAID;
        transactionList.add(this);
        this.date = LocalDateTime.now();
        this.shoppingList.forEach((id, soldNum) -> {
            Product product = ProductProcessor.getInstance().getProduct(id);
            product.sold(soldNum);
        });

        shoppingList.forEach((id, qty) -> {
            Product product = ProductProcessor.getInstance().getProduct(id);
            product.setStock(product.getStock() - qty);
        });
        return 0;
    }

    public double getChange() {
        return change;
    }

    public boolean hasProduct(int id) {
        return this.shoppingList.containsKey(id);
    }

    public boolean cancel(String reason) {
        status = Status.CANCELLED;
        this.reason = reason;
        transactionList.add(this);
        this.date = LocalDateTime.now();
        return true;
    }

    public User getPayee() {
        for (User user : UserProcessor.getInstance().getUsers()) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
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
        Map<Product, Integer> list = new HashMap<>();
        shoppingList.forEach((id, qty) -> {
            Product product = ProductProcessor.getInstance().getProduct(id);
            list.put(product, qty);
        });
        return list;
    }

    public static List<Transaction> getTransactionList() {
        return transactionList;
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