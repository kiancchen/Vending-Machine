package VendingMachine.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private static int totalId = 0;
    private int id;
    private String username;
    private String password;
    private Map<Permission, Boolean> permissions;
    private UserType type;
    private List<Transaction> transactions;
    private Transaction shoppingCart;

    public User() {
        this.username = "";
        this.password = "";
        this.setType(UserType.ANONYMOUS);
        this.id = totalId++;
        this.transactions = new ArrayList<>();
        this.shoppingCart = new Transaction();
    }

    public User(String username, String password, UserType type) {
        this.username = username;
        this.password = password;
        this.setType(type);
        this.id = totalId++;
    }

    public boolean addToCart(int id, int quantity) {
        return this.shoppingCart.add(id, quantity);
    }

    public boolean setItemInCart(int id, int newQty) {
        return this.shoppingCart.set(id, newQty);
    }

    public void initPermissions() {
        this.permissions = new HashMap<>();
        permissions.put(Permission.MANAGE_ITEM, false);
        permissions.put(Permission.MANAGE_CASH, false);
        permissions.put(Permission.MANAGE_USER, false);
    }

    public void setPermission(Permission permission, boolean accessibility) {
        this.permissions.put(permission, accessibility);
    }

    public boolean getPermission(Permission permission) {
        return this.permissions.get(permission);
    }

    public Transaction getShoppingCart() {
        return shoppingCart;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
        this.initPermissions();
        if (type == UserType.SELLER) {
            this.setPermission(Permission.MANAGE_ITEM, true);
        } else if (type == UserType.CASHIER) {
            this.setPermission(Permission.MANAGE_CASH, true);
        } else if (type == UserType.OWNER) {
            this.setPermission(Permission.MANAGE_CASH, true);
            this.setPermission(Permission.MANAGE_ITEM, true);
            this.setPermission(Permission.MANAGE_USER, true);
        }
    }

    public enum UserType {
        CUSTOMER,
        SELLER,
        CASHIER,
        OWNER,
        ANONYMOUS
    }

    public enum Permission {
        MANAGE_ITEM,
        MANAGE_CASH,
        MANAGE_USER
    }
}