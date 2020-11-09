package VendingMachine.Processor;

import java.io.IOException;
import java.util.Map;

public class PaymentProcessor {
    private CashProcessor cashProcessor;

    public PaymentProcessor() throws IOException {
        cashProcessor = new CashProcessor();
    }

    public static boolean payCash(Map<Double, Integer> cashes){
        return false;
    }


}
