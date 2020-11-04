package VendingMachine;

import java.io.IOException;

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

    public UserProcessor getUserProcessor(){
      return this.userProcessor;
    }

    public User getCurrentUser() {
      return this.userProcessor.getCurrentUser();
    }

}
