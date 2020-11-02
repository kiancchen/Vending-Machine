package VendingMachine;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class UserProcessorTest {

    /**
     * Test UserProcessor Constructor
     */
    @Test
    public void UserProcessorConstructor() {
        try{
            UserProcessor userProcessor = new UserProcessor();
            assertNotNull(userProcessor);
        }catch (IOException e) {
            System.out.println("File not found");
        }
    }

    /**
     *
     * @throws IOException
     * Test the verifyUser method in two situations true/false
     */
    @Test
    public void testVerifyUser() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        assertTrue(userProcessor.verifyUser("alan","123"));
        assertFalse(userProcessor.verifyUser("blan","1234"));

    }

    @Test
    public void testAddUser() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        userProcessor.addUser("test","test");
        assertTrue(userProcessor.verifyUser("test","test"));
    }

    @Test
    public void testHasUser() throws IOException{
        UserProcessor userProcessor = new UserProcessor();
        assertTrue(userProcessor.hasUser("alan"));
        assertFalse(userProcessor.hasUser("anyone"));
    }
}
