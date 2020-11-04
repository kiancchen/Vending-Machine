package VendingMachine;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DatabaseHandlerTest {
    @Test
    public void testSaveAndLoadUserData() throws IOException {
        List<User> expected = new ArrayList<>();
        expected.add(new UserImpl("alan", "123", User.UserType.CUSTOMER));
        expected.add(new UserImpl("blan", "123", User.UserType.OWNER));
        DatabaseHandler.saveUserData(expected);

        List<User> actual = DatabaseHandler.loadUserData();
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getId(), actual.get(i).getId());
            assertEquals(expected.get(i).getPassword(), actual.get(i).getPassword());
            assertEquals(expected.get(i).getUsername(), actual.get(i).getUsername());
            assertEquals(expected.get(i).getType(), actual.get(i).getType());
            assertEquals(expected.get(i).getPermission(Permission.MANAGE_ITEM),
                    actual.get(i).getPermission(Permission.MANAGE_ITEM));
            assertEquals(expected.get(i).getPermission(Permission.MANAGE_CASH),
                    actual.get(i).getPermission(Permission.MANAGE_CASH));
            assertEquals(expected.get(i).getPermission(Permission.MANAGE_USER),
                    actual.get(i).getPermission(Permission.MANAGE_USER));

        }
    }
}
