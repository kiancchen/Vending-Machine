package VendingMachine;

import java.io.IOException;
import java.util.List;

public class MainProcessor {
    private UserProcessor userProcessor;

    public MainProcessor() throws IOException{
        userProcessor = new UserProcessor();
    }

    public boolean verifyUser(String username, String password) {
      return this.userProcessor.verifyUser(username, password);
    }

    public void logoutUser() {
      this.userProcessor.logoutUser();
    }

    public boolean addUser(String username, String password) throws IOException {
        return this.userProcessor.addUser(username, password);
    }

    public boolean hasUser(String username) {
        return this.userProcessor.hasUser(username);
    }

    public boolean removeUser(int id) throws IOException{
        return this.userProcessor.removeUser(id);
    }

    public boolean changeUsername(int id, String newUsername) throws IOException {
        return this.userProcessor.changeUsername(id,newUsername);
    }

    public List<User> getUsers() {
        return this.userProcessor.getUsers();
    }

    public User getCurrentUser() {
        return this.userProcessor.getCurrentUser();
    }

    public boolean addUserWithType(String username, String password, String type) throws IOException  {
        return this.userProcessor.addUserWithType(username, password, type);
    }
}
