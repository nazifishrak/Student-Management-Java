package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {
    private Student student;

    @BeforeEach
    public void runBefore() {
        student = new Student("Nazif", "Ishrak", "2369920300", "n01", "paid");
    }

    @Test
    public void testConstructor() {
        assertEquals("Nazif", student.getFname());
        assertEquals("Ishrak", student.getLname());
        assertEquals("2369920300", student.getContact());
        assertEquals("n01", student.getId());
        assertEquals("paid", student.getStatus());
        student.setFname("Nafis");
        student.setLname("Ishrak");
        student.setContact("565656565");
        student.setStatus("due");


        assertEquals("Nafis", student.getFname());
        assertEquals("Ishrak", student.getLname());
        assertEquals("565656565", student.getContact());
        assertEquals("due", student.getStatus());
    }


}