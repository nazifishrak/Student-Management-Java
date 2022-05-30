package model;


//Represents a Student having their name, contact, id, and tuition status

import org.json.JSONObject;

public class Student {

    private final String id;
    private String fname;
    private String lname;
    private String contact;
    private String status;

//    REQUIRES: fname, lname, contact, id have non-zero length
//     EFEFCTS: creates a student with their given information

    public Student(String fname, String lname, String contact, String id, String status) {
        this.fname = fname;
        this.lname = lname;
        this.contact = contact;
        this.id = id;
        this.status = status;

    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("fname", fname);
        json.put("lname", lname);
        json.put("contact", contact);
        json.put("id", id);
        json.put("status", status);
        return json;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
