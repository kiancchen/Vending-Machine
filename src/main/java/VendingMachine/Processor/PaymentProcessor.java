package VendingMachine.Processor;

import java.io.IOException;

public class PaymentProcessor {
    private CashProcessor cashProcessor;

    public PaymentProcessor() throws IOException {
        cashProcessor = new CashProcessor();
    }


}
