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

    public static void saveCashData(Map<Double, Integer> cashMap) {
        cashMap.forEach((value, number) -> {
            try {
                PreparedStatement stmt = connection.prepareStatement(
                        "UPDATE Cash " +
                                "Set number=? " +
                                "WHERE value=?");
                stmt.setInt(1, number);
                stmt.setDouble(2, value);
                stmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public static void saveProductData(Map<Integer, Product> productMap) {
        productMap.forEach((key, value) -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE Product " +
                                "SET code=?, category=?, name=?, price=?, stock=?, sold=? " +
                                "where id=?");
                preparedStatement.setString(1, value.getCode());
                preparedStatement.setString(2, value.getCategory().toString());
                preparedStatement.setString(3, value.getName());
                preparedStatement.setDouble(4, value.getPrice());
                preparedStatement.setInt(5, value.getStock());
                preparedStatement.setInt(6, value.getSold());
                preparedStatement.setInt(7, value.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }

    public static Map<Integer, Product> loadProductData() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Product");
            Map<Integer, Product> productMap = new HashMap<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String code = resultSet.getString("code");
                Product.Category category = Product.Category.valueOf(resultSet.getString("category"));
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");
                int sold = resultSet.getInt("sold");

                productMap.put(id, new Product(id, code, category, name, price, stock, sold));
            }
            return productMap;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
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
