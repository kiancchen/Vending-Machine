package VendingMachine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
}
