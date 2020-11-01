package VendingMachine;

import java.io.IOException;
import java.util.List;

public class UserProcessor {
    private List<User> users;
    private User currentUser;

    public UserProcessor() throws IOException{
        users = DatabaseHandler.loadUserData();
    }

    public boolean verifyUser(String username, String password){
        // verify the username and password from the database
        for(User user: users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean addUser(String username, String password) {
        // 检查用户是否已存在
        return true;
    }

    public boolean hasUser(String username) {
        return true;
    }

    public boolean removeUser(int id) {
        return true;
    }

    public boolean changeUsername(int id, String newUsername) {
        return true;
    }
}
