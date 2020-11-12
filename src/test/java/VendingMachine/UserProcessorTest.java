package VendingMachine;

import VendingMachine.Data.User;
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

public class UserProcessorTest {

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
        MainProcessor.reload();
    }

    /**
     * Test UserProcessor Constructor
     */
    @Test
    public void UserProcessorConstructor() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        assertNotNull(userProcessor);
        assertEquals(User.UserType.ANONYMOUS, userProcessor.getCurrentUser().getType());
    }

    /**
     * @throws IOException Test the verifyUser method in two situations true/false
     */
    @Test
    public void testVerifyUser() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        assertTrue(userProcessor.verifyUser("alan", "123"));
        assertFalse(userProcessor.verifyUser("blan", "1234"));

    }

    @Test
    public void testAddToCart() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        assertFalse(userProcessor.getCurrentUser().addToCart(1, 10));
        assertTrue(userProcessor.getCurrentUser().addToCart(1, 1));
    }

    @Test
    public void testSetItemInCart() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        assertTrue(userProcessor.getCurrentUser().addToCart(1, 5));
        assertFalse(userProcessor.getCurrentUser().setItemInCart(1, 10));
        assertTrue(userProcessor.getCurrentUser().setItemInCart(1, 3));
    }

    @Test
    public void testGetAmount() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        assertEquals(0, userProcessor.getCurrentUser().getShoppingCart().getAmount(), 0);
    }

    @Test
    public void testGetShoppingList() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        assertNotNull(userProcessor.getCurrentUser().getShoppingCart().getShoppingList());
    }

    @Test
    public void testAddUser() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        userProcessor.addUser("test", "test");
        assertTrue(userProcessor.verifyUser("test", "test"));
        assertFalse(userProcessor.addUser("test", "test"));
        userProcessor.removeUser(3);
    }

    @Test
    public void testAddUserWithType() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        userProcessor.addUser("test", "test", "CASHIER");
        assertTrue(userProcessor.verifyUser("test", "test"));
        assertFalse(userProcessor.addUser("test", "test"));
        assertEquals(User.UserType.CASHIER, userProcessor.getCurrentUser().getType());
    }

    @Test
    public void testHasUser() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        assertTrue(userProcessor.hasUser("alan"));
        assertFalse(userProcessor.hasUser("anyone"));
    }

    @Test
    public void testRemoveUser() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        assertTrue(userProcessor.removeUser(2));
        assertFalse(userProcessor.removeUser(10000));
        assertFalse(userProcessor.verifyUser("blan", "123"));
    }

    @Test
    public void testChangeUsername() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        assertTrue(userProcessor.setUsername(2, "test1"));
        assertTrue(userProcessor.verifyUser("test1", "123"));
        assertFalse(userProcessor.setUsername(10000, ""));
    }

    @Test
    public void testGetUsers() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        List<User> userList = DatabaseHandler.loadUserData();
        for (int i = 0; i < userList.size(); i++) {
            assertEquals(userList.get(i).getId(), userProcessor.getUsers().get(i).getId());
            assertEquals(userList.get(i).getPassword(), userProcessor.getUsers().get(i).getPassword());
            assertEquals(userList.get(i).getType(), userProcessor.getUsers().get(i).getType());
            assertEquals(userList.get(i).getUsername(), userProcessor.getUsers().get(i).getUsername());
        }
    }

    @Test
    public void testGetCurrentUser() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        userProcessor.verifyUser("alan", "123");
        assertEquals("alan", userProcessor.getCurrentUser().getUsername());
        assertEquals("123", userProcessor.getCurrentUser().getPassword());
        assertEquals(User.UserType.CUSTOMER, userProcessor.getCurrentUser().getType());
    }

    @Test
    public void testLogoutUser() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        userProcessor.verifyUser("alan", "123");
        userProcessor.logoutUser();
        assertEquals(User.UserType.ANONYMOUS, userProcessor.getCurrentUser().getType());
    }

    @Test
    public void testChangePassword() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        assertTrue(userProcessor.setPassword(2, "test1"));
        assertTrue(userProcessor.verifyUser("blan", "test1"));
        assertFalse(userProcessor.setPassword(10000, "123"));
    }

    @Test
    public void testChangeType() throws IOException {
        UserProcessor userProcessor = new UserProcessor();
        assertTrue(userProcessor.setUserType(2, "SELLER"));
        List<User> userList = DatabaseHandler.loadUserData();
        for (int i = 0; i < userList.size(); i++) {
            assertEquals(userList.get(i).getType(), userProcessor.getUsers().get(i).getType());
        }
        assertFalse(userProcessor.setUserType(10000, "CASHIER"));
    }
}
