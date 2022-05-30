package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagementSystemTest {

    private ManagementSystem ms;
    private Student student;
    private Student student1;
    private Student student2;

    @BeforeEach
    public void runBefore() {
        ms = new ManagementSystem();
        student = new Student("Nazif", "Ishrak", "2369920300", "n01", "paid");
        student1 = new Student("Nafis", "Ishrak", "4569920300", "n02", "due");
        student2 = new Student("Nafis", "Ashraf", "4569920300", "n03", "due");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, ms.getLos().size());
        assertEquals(0, ms.getTotalStudents());
    }

    @Test

    public void testInsertStudent() {
        ms.insertStudent(student);
        assertEquals(1, ms.getLos().size());

        ms.insertStudent(student);
        assertEquals(1, ms.getLos().size());

        ms.insertStudent(student, 0);
        assertEquals(student, ms.getLos().get(0));

    }


    @Test

    public void testRemoveStudent() {
        ms.insertStudent(student);
        ms.insertStudent(student);  //not added
        ms.insertStudent(student1);
        ms.insertStudent(student); //not added
        ms.removeStudent(1); //student1 removed
            ms.insertStudent(student1); //added
        ms.insertStudent(student2, 2);

        assertEquals(3, ms.getTotalStudents());
        assertEquals(student, ms.getLos().get(0));
        assertEquals(student2, ms.getLos().get(2));

    }




}
