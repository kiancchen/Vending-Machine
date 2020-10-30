package VendingMachine;

public class MainProcessor {
    private UserProcessor userProcessor;

    public boolean addUser(String username, String password) {
        return this.userProcessor.addUser(username, password);
    }

    public boolean hasUser(String username) {
        return this.userProcessor.hasUser(username);
    }

    public boolean removeUser(int id) {
        return this.userProcessor.removeUser(id);
    }
}
