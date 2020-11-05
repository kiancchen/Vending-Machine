package VendingMachine;

import VendingMachine.Processor.MainProcessor;
import VendingMachine.Processor.UserProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.Assert.*;

public class MainProcessorTest {

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
        assertTrue(mainProcessor.removeUser(2));
        assertFalse(mainProcessor.removeUser(10000));

    }

    @Test
    public void testVerifyUser() throws IOException{
        MainProcessor mainProcessor = new MainProcessor();
        assertTrue(mainProcessor.verifyUser("alan","123"));
        assertFalse(mainProcessor.verifyUser("blan","1234"));

    }

    @Test
    public void testChangeUsername() throws IOException{
        MainProcessor mainProcessor = new MainProcessor();
        assertTrue(mainProcessor.changeUsername(2,"test1"));
        assertTrue(mainProcessor.verifyUser("test1","123"));
        assertFalse(mainProcessor.changeUsername(10000,""));
    }

    @Test
    public void testGetUsers() throws IOException{
        MainProcessor mainProcessor = new MainProcessor();
        List<User> userList = DatabaseHandler.loadUserData();
        for (int i = 0; i < userList.size(); i ++) {
            assertEquals(userList.get(i).getId(),mainProcessor.getUsers().get(i).getId());
            assertEquals(userList.get(i).getPassword(),mainProcessor.getUsers().get(i).getPassword());
            assertEquals(userList.get(i).getType(),mainProcessor.getUsers().get(i).getType());
            assertEquals(userList.get(i).getUsername(),mainProcessor.getUsers().get(i).getUsername());
        }
    }

    @Test
    public void testGetCurrentUser() throws IOException{
        MainProcessor mainProcessor = new MainProcessor();
        mainProcessor.verifyUser("alan","123");
        assertEquals("alan",mainProcessor.getCurrentUser().getUsername());
        assertEquals("123",mainProcessor.getCurrentUser().getPassword());
    }

    @Test
    public void testLogoutUser() throws IOException {
        MainProcessor mainProcessor = new MainProcessor();
        mainProcessor.verifyUser("alan","123");
        mainProcessor.logoutUser();
        assertEquals(User.UserType.ANONYMOUS, mainProcessor.getCurrentUser().getType());
    }

    @Test
    public void testAddUserWithType() throws IOException {
        MainProcessor mainProcessor = new MainProcessor();
        mainProcessor.addUser("test","test", "CASHIER");
        assertTrue(mainProcessor.verifyUser("test","test"));
        assertFalse(mainProcessor.addUser("test","test"));
        assertEquals(User.UserType.CASHIER, mainProcessor.getCurrentUser().getType());
    }

    @Test
    public void testChangePassword() throws IOException{
        MainProcessor mainProcessor = new MainProcessor();
        assertTrue(mainProcessor.changePassword(2,"test1"));
        assertTrue(mainProcessor.verifyUser("blan","test1"));
        assertFalse(mainProcessor.changePassword(10000,"123"));
    }

    @Test
    public void testChangeType() throws IOException{
        MainProcessor mainProcessor = new MainProcessor();
        assertTrue(mainProcessor.changeType(2, "CASHIER"));
        List<User> userList = DatabaseHandler.loadUserData();
        for (int i = 0; i < userList.size(); i ++) {
            assertEquals(userList.get(i).getType(),mainProcessor.getUsers().get(i).getType());
        }
        assertFalse(mainProcessor.changeType(10000,"CASHIER"));
    }
}
