package VendingMachine;

import VendingMachine.Data.CreditCard;
import VendingMachine.Data.Product;
import VendingMachine.Data.Transaction;
import VendingMachine.Data.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final File userFile = new File("src/main/resources/user.json");
    private static final File cashFile = new File("src/main/resources/cash.json");
    private static final File productFile = new File("src/main/resources/product.json");
    private static final File cardFile = new File("src/main/resources/credit_cards.json");
    private static final File tranFile = new File("src/main/resources/transactions.json");
    private static Statement statement;
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost" +
                    "/Vending_Machine?useSSL=false", "root", "12352000");
            statement = connection.createStatement();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void saveUserData(List<User> users) throws IOException {
        FileWriter fileWriter = new FileWriter(userFile);
        JsonWriter jsonWriter = new JsonWriter(fileWriter);
        jsonWriter.setIndent(" ");
        gson.toJson(users, new TypeToken<List<User>>() {}.getType(), jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
    }

    public static List<User> loadUserData() throws IOException {
        InputStream input = new FileInputStream(userFile);
        JsonReader reader = new JsonReader(new InputStreamReader(input));
        List<User> users = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
        reader.close();
        return users;
    }

    public static Map<Double, Integer> loadCashData() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Cash");
            Map<Double, Integer> cashes = new HashMap<>();
            while (resultSet.next()) {
                cashes.put(resultSet.getDouble("value"), resultSet.getInt("number"));
            }
            return cashes;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void executeUpdate(String sql){
        try {
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static PreparedStatement getPreparedStatement(String sql){
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }



    public static void saveProductData(Map<Integer, Product> productMap) throws IOException {
        FileWriter fileWriter = new FileWriter(productFile);
        JsonWriter jsonWriter = new JsonWriter(fileWriter);
        jsonWriter.setIndent(" ");
        gson.toJson(productMap, new TypeToken<Map<Integer, Product>>() {}.getType(), jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
    }

    public static Map<Integer, Product> loadProductData() throws IOException {
        InputStream input = new FileInputStream(productFile);
        JsonReader reader = new JsonReader(new InputStreamReader(input));
        Map<Integer, Product> products = gson.fromJson(reader,
                new TypeToken<Map<Integer, Product>>() {}.getType());
        reader.close();
        return products;
    }

    public static List<CreditCard> loadCreditCards() throws IOException {
        InputStream input = new FileInputStream(cardFile);
        JsonReader reader = new JsonReader(new InputStreamReader(input));
        List<CreditCard> products = gson.fromJson(reader,
                new TypeToken<List<CreditCard>>() {}.getType());
        reader.close();
        return products;
    }

    public static void saveTransactionData(List<Transaction> transactions) throws IOException {
        FileWriter fileWriter = new FileWriter(tranFile);
        JsonWriter jsonWriter = new JsonWriter(fileWriter);
        jsonWriter.setIndent(" ");
        gson.toJson(transactions, new TypeToken<List<Transaction>>() {}.getType(), jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
    }

    public static List<Transaction> loadTransactionData() throws IOException {
        InputStream input = new FileInputStream(tranFile);
        JsonReader reader = new JsonReader(new InputStreamReader(input));
        List<Transaction> products = gson.fromJson(reader,
                new TypeToken<List<Transaction>>() {}.getType());
        reader.close();
        return products;
    }
}
