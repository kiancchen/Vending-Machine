package VendingMachine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.util.List;

public class DatabaseHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String userDatabaseFilename = "src/main/resources/user.json";

    public static void saveUserData(List<User> users) throws IOException {
        File file = new File(userDatabaseFilename);
        FileWriter fileWriter = new FileWriter(file);
        JsonWriter jsonWriter = new JsonWriter(fileWriter);
        jsonWriter.setIndent(" ");
        gson.toJson(users, new TypeToken<List<User>>() {}.getType(), jsonWriter);
        jsonWriter.flush();
        jsonWriter.close();
    }

    public static List<User> loadUserData() throws IOException {
        File file = new File(userDatabaseFilename);
        InputStream input = new FileInputStream(file);
        JsonReader reader = new JsonReader(new InputStreamReader(input));
        List<User> users = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
        reader.close();
        return users;
    }
}
