package VendingMachine.Processor;

import java.io.IOException;

public class MainProcessor {
    private static UserProcessor userProcessor;
    private static CashProcessor cashProcessor;
    private static ProductProcessor productProcessor;

    static {
        try {
            userProcessor = new UserProcessor();
            cashProcessor = new CashProcessor();
            productProcessor = new ProductProcessor();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
