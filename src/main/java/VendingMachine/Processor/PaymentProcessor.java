package VendingMachine.Processor;

import java.io.IOException;
import java.util.Map;

public class PaymentProcessor {
    private CashProcessor cashProcessor;

    public PaymentProcessor() throws IOException {
        cashProcessor = new CashProcessor();
    }


}
