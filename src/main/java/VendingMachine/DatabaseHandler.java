package VendingMachine;

import VendingMachine.Data.Product;
import VendingMachine.Data.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.util.List;
import java.util.Map;

public class DatabaseHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final File userFile = new File("src/main/resources/user.json");
    private static final File cashFile = new File("src/main/resources/cash.json");
    private static final File productFile = new File("src/main/resources/product.json");

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

    public static void saveCashData(Map<Double, Integer> cashes) throws IOException {
        FileWriter fileWriter = new FileWriter(cashFile);
        JsonWriter jsonWriter = new JsonWriter(fileWriter);
        jsonWriter.setIndent(" ");
        gson.toJson(cashes, new TypeToken<Map<Double, Integer>>() {}.getType(), jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
    }

    public static Map<Double, Integer> loadCashData() throws IOException {
        InputStream input = new FileInputStream(cashFile);
        JsonReader reader = new JsonReader(new InputStreamReader(input));
        Map<Double, Integer> cashes = gson.fromJson(reader,
                new TypeToken<Map<Double, Integer>>() {}.getType());
        reader.close();
        return cashes;
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
}
