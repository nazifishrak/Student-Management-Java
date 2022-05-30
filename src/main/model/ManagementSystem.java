package model;


//Represents a student management system

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManagementSystem {
    private final ArrayList<Student> los;
    private String name;


    // EFFECTS: creates a empty list of student
    public ManagementSystem(String name) {
        this.los = new ArrayList<>();
        this.name = name;


    }

    public ManagementSystem() {
        this.los = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: inserts the given student to the end of list of student but doesn't allow duplicates



    public void insertStudent(Student s) {

        if (!this.los.contains(s)) {

            this.los.add(s);
        }
        EventLog.getInstance().logEvent(new Event("Student added to system"));

    }

    // REQUIRES: 0= <i< length of List of Student
    // MODIFIES: this
    // EFFECTS: removes student from a specified index

    // MODIFIES: this
    // EFFECTS: inserts the given student to the specified index of list of student
    // and doesn't allow duplicates
    public void insertStudent(Student s, int index) {

        if (!this.los.contains(s)) {

            this.los.add(index, s);
        }
        EventLog.getInstance().logEvent(new Event("Student added to system"));

    }

    public void removeStudent(int i) {

        this.los.remove(i);
        EventLog.getInstance().logEvent(new Event("Student removed from system"));

    }

    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("students", studentsToJson());
        return json;
    }

    // EFFECTS: returns students in this management as a JSON array
    public JSONArray studentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Student s : los) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

    public ArrayList<Student> getLos() {
        return los;
    }


    public int getTotalStudents() {
        return los.size();
    }

    public String getName() {
        return this.name;
    }
//EFFECTS: prints the events from an event log

    public void logPrinter(EventLog el) {
        for (Event ev : el) {
            System.out.println(ev.getDescription());
        }
    }
}


