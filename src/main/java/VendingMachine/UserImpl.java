package VendingMachine;

import java.util.HashMap;
import java.util.Map;

public class UserImpl implements User {
    private static int totalId = 1;
    private int id;
    private String username;
    private String password;
    private Map<Permission, Boolean> permissions;
    private UserType type;

    public UserImpl(){
        this.username = "";
        this.password = "";
        this.setType(UserType.ANONYMOUS);
        this.id = totalId++;
    }

    public UserImpl(String username, String password, UserType type) {
        this.username = username;
        this.password = password;
        this.setType(type);
        this.id = totalId++;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean getPermission(Permission permission) {
        return this.permissions.get(permission);
    }

    @Override
    public void initPermissions(){
        this.permissions = new HashMap<Permission, Boolean>();
        permissions.put(Permission.MANAGE_ITEM, false);
        permissions.put(Permission.MANAGE_CASH, false);
        permissions.put(Permission.MANAGE_USER, false);
    }

    @Override
    public void setPermission(Permission permission, boolean accessibility){
        this.permissions.put(permission, accessibility);
    }

    @Override
    public UserType getType() {
        return type;
    }

    @Override
    public void setType(UserType type) {
        this.type = type;
        this.initPermissions();
        if (type == UserType.SELLER){
            this.setPermission(Permission.MANAGE_ITEM, true);
        }
        // TODO
    }

    @Override
    public int getId() {
        return this.id;
    }
}

enum Permission{
    MANAGE_ITEM,
    MANAGE_CASH,
    MANAGE_USER
}