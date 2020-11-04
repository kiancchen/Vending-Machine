package VendingMachine;


public interface User {
    boolean getPermission(Permission permission);

    void setPermission(Permission permission, boolean accessibility);

    void initPermissions();

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    UserType getType();

    void setType(UserType type);

    int getId();

    String getString();

    String getTypeString();

    enum UserType {
        CUSTOMER,
        SELLER,
        CASHIER,
        OWNER,
        ANONYMOUS
    }

}