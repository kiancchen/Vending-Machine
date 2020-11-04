package VendingMachine;

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
}
