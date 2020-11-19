package VendingMachine;

import VendingMachine.Data.Transaction;
import VendingMachine.Processor.CashProcessor;
import VendingMachine.Processor.ProductProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static org.junit.Assert.*;

public class TransactionTest {
    private Transaction transaction;

    @Before
    @After
    public void restoreResources() {
        try {
            Files.copy(new File("src/main/resources/transactions_backup.json").toPath(),
                    new File("src/main/resources/transactions.json").toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void init() throws IOException {
        ProductProcessor.load();
        CashProcessor.load();
        Transaction.load();
        transaction = new Transaction(0);
    }

    @Test
    public void testSet() {
        assertTrue(transaction.set(1, 5));
        assertEquals(5.0, transaction.getTotalPrice(), 0);
        assertTrue(transaction.set(1, 0));
        assertEquals(0, transaction.getTotalPrice(), 0);
        assertFalse(transaction.set(1,10));
    }

    @Test
    public void testPay() {
        transaction.pay(10, Transaction.Payment.CASH);
        assertEquals(10, transaction.getPaidAmount(), 0);
        assertEquals(1, transaction.pay(-1, Transaction.Payment.CASH));
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

    @Test
    public void testGetPayment(){
        transaction.pay(10, Transaction.Payment.CASH);
        assertEquals(Transaction.Payment.CASH,transaction.getPayment());
    }

    @Test
    public void testGetReturnedChangeMap() {
        assertNull(transaction.getReturnedChangeMap());
    }

    @Test
    public void testGetChange() {
        Transaction transaction = new Transaction(1);
        transaction.set(1,1);
        transaction.hasProduct(1);
        transaction.pay(10, Transaction.Payment.CASH);
        assertEquals(9,(transaction.getChange()),0);
    }

}
