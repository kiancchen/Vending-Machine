package R18B.Group5.VendingMachine;

public interface User {
    String getUsername();

    String getPassword();

    void setUsername(String username);

    void setPassword(String password);

    boolean getPermission(Permission permission);

    UserType getType();

    void setPermission(Permission permission, boolean accessibility);

    void initPermissions();

    void setType(UserType type);

    int getId();

}

enum UserType{
    CUSTOMER,
    SELLER,
    CASHIER,
    OWNER,
    ANONYMOUS
}