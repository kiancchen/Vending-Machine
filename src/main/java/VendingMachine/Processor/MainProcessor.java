package VendingMachine.Processor;

import java.io.IOException;

public class MainProcessor {
    private static UserProcessor userProcessor;
    private static CashProcessor cashProcessor;
    private static ProductProcessor productProcessor;
    private static PaymentProcessor paymentProcessor;

    static {
        reload();
    }


    public static UserProcessor getUserProcessor() {
        return userProcessor;
    }

    public static CashProcessor getCashProcessor() {
        return cashProcessor;
    }

    public static ProductProcessor getProductProcessor() {
        return productProcessor;
    }

    public static PaymentProcessor getPaymentProcessor() {
        return paymentProcessor;
    }

    public static void reload() {
        try {
            userProcessor = new UserProcessor();
            cashProcessor = new CashProcessor();
            productProcessor = new ProductProcessor();
            paymentProcessor = new PaymentProcessor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
