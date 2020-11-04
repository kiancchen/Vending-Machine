package VendingMachine;

import VendingMachine.Processor.UserProcessor;
import org.junit.*;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

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
        userProcessor.removeUser(3);
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
        assertTrue(userProcessor.removeUser(2));
        assertFalse(userProcessor.removeUser(10000));
        assertFalse(userProcessor.verifyUser("blan","123"));
    }

    @Test
    public void testChangeUsername() throws IOException{
        UserProcessor userProcessor = new UserProcessor();
        assertTrue(userProcessor.changeUsername(2,"test1"));
        assertTrue(userProcessor.verifyUser("test1","123"));
        assertFalse(userProcessor.changeUsername(10000,""));
    }

    @Test
    public void testGetUsers() throws IOException{
        UserProcessor userProcessor = new UserProcessor();
        List<User> userList = DatabaseHandler.loadUserData();
        for (int i = 0; i < userList.size(); i ++) {
            assertEquals(userList.get(i).getId(),userProcessor.getUsers().get(i).getId());
            assertEquals(userList.get(i).getPassword(),userProcessor.getUsers().get(i).getPassword());
            assertEquals(userList.get(i).getType(),userProcessor.getUsers().get(i).getType());
            assertEquals(userList.get(i).getUsername(),userProcessor.getUsers().get(i).getUsername());
        }
    }

    @Test
    public void testGetCurrentUser() throws IOException{
        UserProcessor userProcessor = new UserProcessor();
        userProcessor.verifyUser("alan","123");
        assertEquals("alan",userProcessor.getCurrentUser().getUsername());
        assertEquals("123",userProcessor.getCurrentUser().getPassword());
    }
}
