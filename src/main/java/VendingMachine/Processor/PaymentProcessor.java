package VendingMachine.Processor;

import java.io.IOException;
import java.util.Map;

public class PaymentProcessor {
    private static CashProcessor cashProcessor;

    static {
        try {
            cashProcessor = new CashProcessor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean payCash(Map<Double, Integer> cashes){
        return false;
    }


}
