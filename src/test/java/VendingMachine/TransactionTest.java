package VendingMachine;

import VendingMachine.Data.Transaction;
import VendingMachine.Processor.MainProcessor;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TransactionTest {
    private Transaction transaction;

    @Before
    public void init() {
        MainProcessor.reload();
        transaction = new Transaction();
    }

    @Test
    public void testAdd() {
        assertTrue(transaction.add(1, 1));
        assertEquals(1.0, transaction.getAmount(), 0);
        assertTrue(transaction.add(1, 2));
        assertEquals(3.0, transaction.getAmount(), 0);
        assertFalse(transaction.add(1,20));
    }

    @Test
    public void testSet() {
        assertTrue(transaction.add(1, 1));
        assertEquals(1.0, transaction.getAmount(), 0);
        assertTrue(transaction.set(1, 5));
        assertEquals(5.0, transaction.getAmount(), 0);
        assertTrue(transaction.set(1, 0));
        assertEquals(0, transaction.getAmount(), 0);
    }

    @Test
    public void testPay() {
        transaction.pay(10);
        assertEquals(10, transaction.getReceivedMoney(), 0);
    }

}
