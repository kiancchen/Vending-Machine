package VendingMachine;

import VendingMachine.Data.Product;
import VendingMachine.Processor.ProductProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ProductProcessorTest {

    @Before
    @After
    public void restoreResources() {
        try {
            Files.copy(new File("src/main/resources/product_backup.json").toPath(),
                    new File("src/main/resources/product.json").toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCategory() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertEquals(Product.Category.DRINK,productProcessor.getProduct(1).getCategory());
    }

    @Test
    public void testGetPrice() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertEquals(1.0,productProcessor.getProduct(1).getPrice(),0);
    }

    @Test
    public void testGetQuantity() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertEquals(7,productProcessor.getProduct(1).getStock(),0);
    }

    @Test
    public void testProductProcessorConstructor() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertNotNull(productProcessor);
    }

    @Test
    public void testAddProduct() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertFalse(productProcessor.addProduct("1","DRINK","Mineral Water",1,7));
        assertFalse(productProcessor.addProduct("20","DRINK","Test",1,20));
        assertTrue(productProcessor.addProduct("20","DRINK","Test",1,7));
    }

    @Test
    public void testSetProductQuantity() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertFalse(productProcessor.setProductStock(1,16));
        assertTrue(productProcessor.setProductStock(1,10));
    }

    @Test
    public void testSetProductName() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertFalse(productProcessor.setProductName(1,"Sprite"));
        assertTrue(productProcessor.setProductName(1, "test"));
    }

    @Test
    public void testSetProductCategory() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertTrue(productProcessor.setProductCategory(1, "CHIP"));
    }

    @Test
    public void testSetProductPrice() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertTrue(productProcessor.setProductPrice(1, 10));
    }

    @Test
    public void testGetProductMap() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        Map<Integer, Product> test = DatabaseHandler.loadProductData();
        assertEquals(test.size(),productProcessor.getProductMap().size());
    }

    @Test
    public void testRemoveProduct() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertFalse(productProcessor.removeProduct(20));
        assertTrue(productProcessor.removeProduct(1));
    }

    @Test
    public void testGetProduct() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertNull(productProcessor.getProduct(20));
        assertNotNull(productProcessor.getProduct(1));
    }

    @Test
    public void testSetProductCode() throws IOException{
        ProductProcessor productProcessor = new ProductProcessor();
        assertFalse(productProcessor.setProductCode(1, "5"));
        assertTrue(productProcessor.setProductCode(1,"20"));
    }

}















