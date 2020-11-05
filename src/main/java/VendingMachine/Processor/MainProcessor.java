package VendingMachine.Processor;

import VendingMachine.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainProcessor {
    private UserProcessor userProcessor;
    private CashProcessor cashProcessor;


    public MainProcessor() throws IOException {
        userProcessor = new UserProcessor();
        cashProcessor = new CashProcessor();
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

    public boolean removeUser(int id) throws IOException {
        return this.userProcessor.removeUser(id);
    }

    public boolean changeUsername(int id, String newUsername) throws IOException {
        return this.userProcessor.changeUsername(id, newUsername);
    }

    public boolean changePassword(int id, String newPassword) throws IOException {
        return this.userProcessor.changePassword(id, newPassword);
    }

    public boolean changeType(int id, String newType) throws IOException {
        return this.userProcessor.changeType(id, newType);
    }

    public boolean addUser(String username, String password, String type) throws IOException {
        return this.userProcessor.addUser(username, password, type);
    }

    public List<User> getUsers() {
        return this.userProcessor.getUsers();
    }

    public Map<Double, Integer> getCashMap() {
        return this.cashProcessor.getCashMap();
    }
    public User getCurrentUser() {
        return this.userProcessor.getCurrentUser();
    }
}
