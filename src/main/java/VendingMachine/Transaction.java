package VendingMachine;

import VendingMachine.Processor.PaymentProcessor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Transaction {
    private List<Product> products;
    private Date date;
    private boolean success;
    private double amount;
    private double receivedMoney;


    public Transaction() {
        products = new ArrayList<>();
        success = false;
        amount = 0;
    }

    public void add(Product product) {
        this.products.add(product);
        this.amount += product.getPrice();
    }

    public void remove(Product product) {
        this.products.remove(product);
        this.amount -= product.getPrice();
    }

    public void payCash(Map<Double, Integer> cashes){
        PaymentProcessor.payCash(cashes);
    }
}
