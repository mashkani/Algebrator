package persistence;

import model.CongruenceClass;
import model.FiniteGroup;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads FiniteGroup from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads FiniteGroup from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FiniteGroup read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFiniteGroup(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses finiteGroup from JSON object and returns it
    private FiniteGroup parseFiniteGroup(JSONObject jsonObject) {
        int operation = jsonObject.getInt("operation");
        FiniteGroup fg = new FiniteGroup(new HashSet<>(),operation);
        addCongruenceClasses(fg, jsonObject);
        return fg;
    }

    // MODIFIES: fg
    // EFFECTS: parses set from JSON object and adds them to finiteGroup
    private void addCongruenceClasses(FiniteGroup fg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("set");
        for (Object json : jsonArray) {
            JSONObject nextCongruenceClass = (JSONObject) json;
            addCongruenceClass(fg, nextCongruenceClass);
        }
    }

    // MODIFIES: fg
    // EFFECTS: parses congruenceClass from JSON object and adds it to finiteGroup
    private void addCongruenceClass(FiniteGroup fg, JSONObject jsonObject) {
        CongruenceClass c = new CongruenceClass(jsonObject.getInt("congruenceClass"));
        fg.addElement(c);
    }
}