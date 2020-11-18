package VendingMachine;

import VendingMachine.Data.Transaction;
import VendingMachine.Processor.CashProcessor;
import VendingMachine.Processor.ProductProcessor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TransactionTest {
    private Transaction transaction;

    @Before
    public void init() throws IOException {
        ProductProcessor.load();
        CashProcessor.load();
        transaction = new Transaction();
    }

    @Test
    public void testSet() {
        assertTrue(transaction.set(1, 5));
        assertEquals(5.0, transaction.getTotalPrice(), 0);
        assertTrue(transaction.set(1, 0));
        assertEquals(0, transaction.getTotalPrice(), 0);
    }

    @Test
    public void testPay() {
        transaction.pay(10, Transaction.Payment.CASH, null);
        assertEquals(10, transaction.getPaidAmount(), 0);
        assertFalse(transaction.pay(-1, Transaction.Payment.CASH, null));
        assertNotNull(transaction.getDate());
    }

    @Test
    public void testCancel() {
        assertTrue(transaction.cancel("test"));
        assertEquals(Transaction.Status.CANCELLED, transaction.getStatus());
        assertEquals("test", transaction.getReason());
    }

    @Test
    public void testGetTransactionList() {
        assertNotNull(Transaction.getTransactionList());
    }
}
