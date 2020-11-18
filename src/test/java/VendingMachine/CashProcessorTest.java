package VendingMachine;

import VendingMachine.Processor.CashProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class CashProcessorTest {
    CashProcessor cashProcessor;

    @Before
    @After
    public void restoreResources() {
        try {
            Files.copy(new File("src/main/resources/cash_backup.json").toPath(),
                    new File("src/main/resources/cash.json").toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void init() throws IOException {
        cashProcessor = CashProcessor.load();
    }

    @Test
    public void testConstructor() {
        Map<Double, Integer> cashes = cashProcessor.getCashMap();
        double[] values = {100.0, 50.0, 20.0, 10.0, 5.0, 2.0, 1.0, 0.5, 0.2, 0.1, 0.05};
        for (double value : values) {
            assertEquals(5, (int) cashes.get(value));
        }
    }

    @Test
    public void testSetCashNumber() throws IOException {
        Map<Double, Integer> cashes = cashProcessor.getCashMap();
        cashProcessor.setCashNumber(100.0, 10);
        assertEquals(10, (int) cashes.get(100.0));
        cashProcessor.setCashNumber(0.05, 6);
        assertEquals(6, (int) cashes.get(0.05));
        cashProcessor.setCashNumber(1.0, 3);
        assertEquals(3, (int) cashes.get(1.0));
        cashProcessor.setCashNumber(0.5, 0);
        assertEquals(0, (int) cashes.get(0.5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeCashNumber() throws IOException {
        Map<Double, Integer> cashes = cashProcessor.getCashMap();
        cashProcessor.setCashNumber(100.0, -1);
    }

    @Test
    public void testGetChange1() throws IOException {
        Map<Double, Integer> changes = cashProcessor.getChange(45);
        assertEquals(2, (int) changes.get(20.0));
        assertEquals(1, (int) changes.get(5.0));
        assertEquals(3, (int) cashProcessor.getCashMap().get(20.0));
        assertEquals(4, (int) cashProcessor.getCashMap().get(5.0));
    }

    @Test
    public void testGetChange2() throws IOException {
        Map<Double, Integer> changes = cashProcessor.getChange(10000.0);
        assertNull(changes);
    }

    @Test
    public void testGetChange3() throws IOException {
        Map<Double, Integer> changes = cashProcessor.getChange(0.01);
        assertNull(changes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNegativeChange1() throws IOException {
        Map<Double, Integer> changes = cashProcessor.getChange(-10);
    }

    @Test
    public void getInstance () throws IOException{
        CashProcessor.getInstance();
    }
}
