
package persistence;
//CITATION: MODEL FOR DATA PERSISTENCE WAS REFERRED FROM THE PHASE2 DEMO

import model.ManagementSystem;
import model.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads ManagementSystem Data from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ManagementSystem from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ManagementSystem read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseManagementSystem(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ManagementSystem from JSON object and returns it
    private ManagementSystem parseManagementSystem(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ManagementSystem ms = new ManagementSystem(name);
        addStudents(ms, jsonObject);
        return ms;
    }

    // MODIFIES: ms
    // EFFECTS: parses students from JSON object and adds them to ManagementSystem
    private void addStudents(ManagementSystem ms, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("students");
        for (Object json : jsonArray) {
            JSONObject nextStudent = (JSONObject) json;
            addStudent(ms, nextStudent);
        }
    }

    // MODIFIES: ms
    // EFFECTS: parses student from JSON object and adds it to ManagementSystem
    private void addStudent(ManagementSystem ms, JSONObject jsonObject) {
        String fname = jsonObject.getString("fname");
        String lname = jsonObject.getString("lname");
        String contact = jsonObject.getString("contact");
        String id = jsonObject.getString("id");
        String status = jsonObject.getString("status");
        Student student = new Student(fname, lname, contact, id, status);
        ms.insertStudent(student);
    }
}