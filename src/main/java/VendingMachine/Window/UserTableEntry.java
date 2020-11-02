package VendingMachine.Window;

public class UserTableEntry {
    private String id;
    private String username;
    private String password;
    private String type;

    public UserTableEntry(String id, String username, String password, String type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }
}
