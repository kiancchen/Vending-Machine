package VendingMachine;

import java.io.IOException;
import java.util.List;

public class UserProcessor {
    private List<User> users;
    private User currentUser;

    public UserProcessor() throws IOException {
        users = DatabaseHandler.loadUserData();
        this.currentUser = this.users.get(0);
    }

    public boolean verifyUser(String username, String password) {
        // verify the username and password from the database
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                this.currentUser = user;
                return true;
            }
        }
        return false;
    }

    public boolean addUser(String username, String password) throws IOException {
        // check the username is used or not
        return this.addUser(username, password, "CUSTOMER");
    }

    public boolean addUser(String username, String password, String type) throws IOException {
        // check the username is used or not
        if (!hasUser(username)) {
            User newUser = new UserImpl(username, password, User.UserType.valueOf(type));
            users.add(newUser);
            DatabaseHandler.saveUserData(users);
            return true;
        } else {
            return false;
        }
    }

    public boolean hasUser(String username) {
        // loop users to check the username is exists or not
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeUser(int id) throws IOException {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                DatabaseHandler.saveUserData(users);
                return true;
            }
        }
        return false;
    }

    public boolean changeUsername(int id, String newUsername) throws IOException {
        for (User user : users) {
            if (user.getId() == id) {
                user.setUsername(newUsername);
                DatabaseHandler.saveUserData(users);
                return true;
            }
        }
        return false;
    }

    public void logoutUser() {
        this.currentUser = this.users.get(0);
    }

    public List<User> getUsers() {
        return this.users;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }
}
