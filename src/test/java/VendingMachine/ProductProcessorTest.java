package VendingMachine;

import VendingMachine.Data.Product;
import VendingMachine.Processor.ProductProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.print.attribute.standard.PrinterMessageFromOperator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static org.junit.Assert.*;

public class ProductProcessorTest {

    @Before
    @After
    public void restoreResources() {
        try {
            Files.copy(new File("src/main/resources/user_backup.json").toPath(),
                    new File("src/main/resources/user.json").toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProductProcessorConstructor() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertNotNull(productProcessor);
    }

    @Test
    public void testAddProduct() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertFalse(productProcessor.addProduct("CHOCOLATE","M\u0026M",2.5,7));
        assertTrue(productProcessor.addProduct("DRINK","test",3,1));

    }

    @Test
    public void testSetProductQuantity() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertFalse(productProcessor.setProductQuantity("DRINK",1,16));
        assertTrue(productProcessor.setProductQuantity("DRINK",1,10));
        assertFalse(productProcessor.setProductQuantity("DRINK",10,10));
    }

    @Test
    public void testSetProductName() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertFalse(productProcessor.setProductName("DRINK",10,"test"));
        assertTrue(productProcessor.setProductName("DRINK",2,"newName"));
    }

    @Test
    public void testSetProductCategory() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertFalse(productProcessor.setProductCategory("DRINK", 10, "test"));
        assertTrue(productProcessor.setProductCategory("DRINK",1,"CHIP"));
    }

    @Test
    public void testSetProductPrice() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertFalse(productProcessor.setProductPrice("DRINK",10,10));
        assertTrue(productProcessor.setProductPrice("DRINK",2,10));
    }



}















