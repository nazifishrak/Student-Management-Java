package persistence;

import model.ManagementSystem;
import model.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends StudentJSONTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ManagementSystem ms = new ManagementSystem("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyManagementSystem() {
        try {
            ManagementSystem ms = new ManagementSystem("UBC CPSC 210");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyManagementSystem.json");
            writer.open();
            writer.write(ms);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyManagementSystem.json");
            ms = reader.read();
            assertEquals("UBC CPSC 210", ms.getName());
            assertEquals(0, ms.getTotalStudents());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralManagementSystem() {
        try {
            ManagementSystem ms = new ManagementSystem("UBC CPSC 210");
            ms.insertStudent(new Student("Nazif", "Ishrak", "01727498603", "123", "paid"));
            ms.insertStudent(new Student("Nafis", "Ishrak", "01727498603", "124", "paid"));
            ms.insertStudent(new Student("Alex", "Zhang", "01725564503", "125", "paid"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralManagementSystem.json");
            writer.open();
            writer.write(ms);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralManagementSystem.json");
            ms = reader.read();
            assertEquals("UBC CPSC 210", ms.getName());
            ArrayList<Student> los = ms.getLos();
            assertEquals(3, los.size());
            checkStudent("Nazif", "Ishrak", "01727498603", "123", "paid", los.get(0));
            checkStudent("Nafis", "Ishrak", "01727498603", "124", "paid", los.get(1));
            checkStudent("Alex", "Zhang", "01725564503", "125", "paid", los.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
