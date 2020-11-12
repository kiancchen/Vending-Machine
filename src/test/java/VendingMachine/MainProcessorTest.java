package VendingMachine;

import VendingMachine.Processor.MainProcessor;
import org.junit.Test;


import static org.junit.Assert.*;

public class MainProcessorTest {

    @Test
    public void testMainProcessorConstructor() {
        MainProcessor mainProcessor = new MainProcessor();
        assertNotNull(mainProcessor);
        assertNotNull(MainProcessor.getUserProcessor());
        assertNotNull(MainProcessor.getCashProcessor());
        assertNotNull(MainProcessor.getPaymentProcessor());
        assertNotNull(MainProcessor.getProductProcessor());

    }

}
