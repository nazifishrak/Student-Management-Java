package persistence;

import model.ManagementSystem;
import model.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends StudentJSONTest {


    @Test
    public void testNoFileFound() {
        JsonReader reader = new JsonReader("./data/xyz.json");
        try {
            reader.read();
            fail("IOException expected");

        } catch (IOException e) {
            //
        }
    }

    @Test
    void testReaderEmptyManagementSystem() {
        JsonReader reader = new JsonReader("./data/emptyManagementData.json");
        try {
            ManagementSystem ms = reader.read();
            assertEquals("UBC CPSC 210", ms.getName());
            assertEquals(0, ms.getTotalStudents());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralManagementSystem() {
        JsonReader reader = new JsonReader("./data/generalManagementData.json");
        try {
            ManagementSystem ms = reader.read();
            assertEquals("UBC CPSC 210", ms.getName());
            ArrayList<Student> los = ms.getLos();
            assertEquals(2, los.size());
            checkStudent("Nazif", "Ishrak", "2369920300", "9jvk", "paid", los.get(0));
            checkStudent("Nafis", "Ishrak", "2369950300", "9gvk", "paid", los.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
