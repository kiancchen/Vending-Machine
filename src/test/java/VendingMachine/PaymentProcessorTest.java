package VendingMachine;

import VendingMachine.Processor.PaymentProcessor;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class PaymentProcessorTest {

    @Test
    public void testPaymentProcessorConstructor() throws IOException{
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        assertNotNull(paymentProcessor);
    }
}
