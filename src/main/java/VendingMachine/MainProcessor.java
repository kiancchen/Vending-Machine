package VendingMachine;

import java.io.IOException;

public class MainProcessor {
    private UserProcessor userProcessor;

    public MainProcessor() throws IOException{
        userProcessor = new UserProcessor();
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
}
