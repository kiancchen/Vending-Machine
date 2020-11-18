package VendingMachine;

import VendingMachine.Data.Transaction;
import VendingMachine.Processor.ProductProcessor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class TransactionTest {
    private Transaction transaction;

    @Before
    public void init() throws IOException {
        transaction = new Transaction();
        ProductProcessor.reload();
    }

    @Test
    public void testAdd() {
        assertTrue(transaction.add(1, 1));
        assertEquals(1.0, transaction.getTotalPrice(), 0);
        assertTrue(transaction.add(1, 2));
        assertEquals(3.0, transaction.getTotalPrice(), 0);
        assertFalse(transaction.add(1,20));
    }

    @Test
    public void testSet() {
        assertTrue(transaction.add(1, 1));
        assertEquals(1.0, transaction.getTotalPrice(), 0);
        assertTrue(transaction.set(1, 5));
        assertEquals(5.0, transaction.getTotalPrice(), 0);
        assertTrue(transaction.set(1, 0));
        assertEquals(0, transaction.getTotalPrice(), 0);
        transaction.add(2,1);
        assertFalse(transaction.set(2,10));
    }

    @Test
    public void testPay() {
        transaction.pay(10, Transaction.Payment.CASH);
        assertEquals(10, transaction.getPaidAmount(), 0);
        assertFalse(transaction.pay(-1, Transaction.Payment.CASH));
        assertNotNull(transaction.getDate());
    }

    @Test
    public void testCancel() {
        assertTrue(transaction.cancel("test"));
        assertEquals(Transaction.Status.CANCELLED,transaction.getStatus());
        assertEquals("test",transaction.getReason());
    }

    @Test
    public void testGetTransactionList() {
        assertNotNull(Transaction.getTransactionList());
    }

    @Test
    public void testGetPayment() {
        transaction.pay(10, Transaction.Payment.CASH);
        assertEquals(Transaction.Payment.CASH,transaction.getPayment());
    }

    @Test
    public void testGetReturnedChangeMap() {
        assertNull(transaction.getReturnedChangeMap());
    }

}
