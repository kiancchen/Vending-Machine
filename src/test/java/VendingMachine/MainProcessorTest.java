package VendingMachine;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;
public class MainProcessorTest {

    @Test
    public void testMainProcessorConstructor() {
        try{
            MainProcessor mainProcessor = new MainProcessor();
            assertNotNull(mainProcessor);
        }catch (IOException e) {
            System.out.println("Error");
        }
    }

    @Test
    public void testAddUser() throws IOException{
        MainProcessor mainProcessor = new MainProcessor();
        assertTrue(mainProcessor.addUser("test1","test1"));
        assertFalse(mainProcessor.addUser("test1","test1"));
    }

    @Test
    public void testHasUser() throws IOException{
        MainProcessor mainProcessor = new MainProcessor();
        assertTrue(mainProcessor.hasUser("alan"));
        assertFalse(mainProcessor.hasUser("anyone"));
    }

    @Test
    public void testRemoveUser() throws IOException{
        MainProcessor mainProcessor = new MainProcessor();
        mainProcessor.addUser("test1","test1");
        assertTrue(mainProcessor.removeUser(3));
        assertFalse(mainProcessor.removeUser(10000));

    }
}
