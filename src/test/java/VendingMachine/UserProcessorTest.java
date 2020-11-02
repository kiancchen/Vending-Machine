package VendingMachine;

import org.junit.*;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import static org.junit.Assert.*;

public class UserProcessorTest {

    @Before
    @After
    public void restoreResources(){
        try {
            Files.copy(new File("src/main/resources/user_backup.json").toPath(),
                    new File("src/main/resources/user.json").toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test UserProcessor Constructor
     */
    @Test
    public void UserProcessorConstructor() {
        try{
            UserProcessor userProcessor = new UserProcessor();
            assertNotNull(userProcessor);
        }catch (IOException e) {
            System.out.println("Error");
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
        assertFalse(userProcessor.addUser("test","test"));
    }

    @Test
    public void testHasUser() throws IOException{
        UserProcessor userProcessor = new UserProcessor();
        assertTrue(userProcessor.hasUser("alan"));
        assertFalse(userProcessor.hasUser("anyone"));
    }

    @Test
    public void testRemoveUser() throws IOException{
        UserProcessor userProcessor = new UserProcessor();
        userProcessor.addUser("test","test");
        assertTrue(userProcessor.removeUser(3));
        assertFalse(userProcessor.removeUser(10000));
        assertFalse(userProcessor.verifyUser("test","test"));
    }

    @Test
    public void testChangeUsername() throws IOException{
        UserProcessor userProcessor = new UserProcessor();
        userProcessor.addUser("test","test");
        assertTrue(userProcessor.changeUsername(3,"test1"));
        assertTrue(userProcessor.verifyUser("test1","test"));
        assertFalse(userProcessor.changeUsername(10000,""));
    }
}
