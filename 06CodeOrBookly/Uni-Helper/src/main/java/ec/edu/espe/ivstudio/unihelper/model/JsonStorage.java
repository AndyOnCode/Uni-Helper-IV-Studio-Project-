package ec.edu.espe.ivstudio.unihelper.model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
/**
 *
 * @author Tomas Tapia, IV Studio, @ESPE
 */
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static <T> void saveListToFile(String filename, List<T> data) {
        try (FileWriter writer = new FileWriter(filename)) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Error saving data to " + filename + ": " + e.getMessage());
        }
    }

    public static <T> List<T> loadListFromFile(String filename, Class<T> clazz) {
        File file = new File(filename);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {
            Type type = TypeToken.getParameterized(List.class, clazz).getType();
            List<T> result = GSON.fromJson(reader, type);
            return result != null ? result : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error reading " + filename + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

