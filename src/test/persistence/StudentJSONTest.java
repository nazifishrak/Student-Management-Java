package persistence;

import model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentJSONTest {
protected void checkStudent(String fname, String lname, String contact, String id, String status, Student s) {
    assertEquals(fname, s.getFname());
    assertEquals(lname, s.getLname());
    assertEquals(contact, s.getContact());
    assertEquals(id, s.getId());
    assertEquals(status, s.getStatus());
}
}
