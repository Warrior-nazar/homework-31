package util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private FileUtil(){};
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
    private static final Path PATH = Paths.get("data/json/tasks.json");

    public static List<Task> loadTasks() {
        try {
            String json = Files.readString(PATH);
            JsonElement jsonElement = JsonParser.parseString(json);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray("tasks");
            return GSON.fromJson(jsonArray, new TypeToken<List<Task>>() {
            }.getType());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void saveTasks(List<Task> tasks) {
        try {
            JsonObject jsonObject = new JsonObject();
            JsonElement tasksJsonElement = GSON.toJsonTree(tasks, new TypeToken<List<Task>>(){}.getType());
            jsonObject.add("tasks", tasksJsonElement);

            String json = GSON.toJson(jsonObject);

            Files.writeString(PATH, json);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
