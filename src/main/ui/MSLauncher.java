package ui;

import model.ManagementSystem;
import model.Student;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// User Interface for the Management System

public class MSLauncher {

    private static final String JSON_LOCATION = "./data/managementData.json";
    private Scanner inp;
    private ManagementSystem ms;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    public MSLauncher() throws FileNotFoundException {

        welcome();
        launchMS();

    }

    /*
    MODIFIES: this, ms
    EFFECTS: launches the application by asking for user
     response and handling them
    */

    public void launchMS() throws FileNotFoundException {
        ms = new ManagementSystem("UBC CPSC 210");   //management system instance
        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);

        ArrayList<Student> students = ms.getLos();

        boolean state = true;

        while (state) {

            prompt();
            inp = new Scanner(System.in);
            String key = inp.next();

            if (key.equals("q")) {
                state = false;
            } else {
                inputResponseHandler(key);
            }


        }

    }

    //EFFECTS: Helper function to manage input response

    private void inputResponseHandler(String key) {

        if (key.equals("a")) {
            additionHandler(ms.getTotalStudents());
        } else if (key.equals("r")) {
            removalHandler();
        } else if (key.equals("u")) {
            updateResponseHandler();
        } else if (key.equals("s")) {
            saveManagementSystem();
        } else if (key.equals("l")) {
            loadManagementSystem();
        } else {
            System.out.printf("invalid input %s : Please type again\n", key);
        }
    }

    /*
    MODIFIES: ms
    EFFECTS: constructs student based on user response and adds to list of students
    */
    public void additionHandler(int index) {
        System.out.println("Type the First Name: ");
        String fn = inp.next();
        System.out.println("Type the Last Name: ");
        String ln = inp.next();
        System.out.println("Type the Contact Number: ");
        String c = inp.next();
        System.out.println("Type the ID: ");
        String id = inp.next();
        System.out.println("Type paid/due: ");
        String s = inp.next();

        Student tempStudent = new Student(fn, ln, c, id, s);

        if (index < 1) {
            index = 0;
        }

        ms.insertStudent(tempStudent, index);
        showTable();


    }

    /*
    MODIFIES: ms
    EFFECTS: removes student based on input
    */

    public void removalHandler() {

        System.out.println("Which index do you want to remove: ");
        String index = inp.next();
        ms.removeStudent(Integer.parseInt(index));
        showTable();

    }

    /*
    MODIFIES: ms
    EFFECTS: constructs student based on user response and updates list of students
    */
    public void updateAllHandler() {

        System.out.println("Which index do you want to update: ");
        String index = inp.next();
        ms.removeStudent(Integer.parseInt(index));
        additionHandler(Integer.parseInt(index));
        showTable();

    }

    /*
    REQUIRES: field input spelling is same as the column spelling
    MODIFIES: ms
    EFFECTS: Updates the list of student based on specific fields
    */

    public void updateHandler() {

        System.out.println("Which index do you want to update: ");
        String index = inp.next();
        System.out.println("Type the column name do you want to update: ");
        String field = inp.next();
        field = field.toLowerCase();
        fieldResponseHandler(field, Integer.parseInt(index));
        showTable();


    }

    /*
        MODIFIES: Student
        EFFECTS: Changes Student information based on input of user
    */
    public void fieldResponseHandler(String input, int index) {
        if (input.equals("fname")) {
            System.out.println("Enter the new value");
            String resp = inp.next();
            ms.getLos().get(index).setFname(resp);

        } else if (input.equals("lname")) {
            System.out.println("Enter the new value");
            String resp = inp.next();
            ms.getLos().get(index).setLname(resp);

        } else if (input.equals("contact")) {
            System.out.println("Enter the new value");
            String resp = inp.next();
            ms.getLos().get(index).setContact(resp);

        } else if (input.equals("id")) {
            System.out.println("Cannot change ID");


        } else if (input.equals("tuition")) {
            System.out.println("Enter the new value");
            String resp = inp.next();
            ms.getLos().get(index).setStatus(resp);

        } else {
            System.out.printf("invalid input %s : Please type again\n", input);
        }


    }


    //EFFECTS: asks users question about what to input to navigate the app

    public void prompt() {

        System.out.println("To add a student, type 'a' ");
        System.out.println("To remove students, type 'r' ");
        System.out.println("To update information of a student, type 'u' ");
        System.out.println("To save management system data, type 's' ");
        System.out.println("To load management system data from file, type 'l' ");
        System.out.println("To quit the program, type 'q' ");
        System.out.println("=============================================");
        System.out.print("Type here: ");
    }

    //EFFECTS: displays the list of students in a tabular format

    public void showTable() {
        ArrayList<Student> los = ms.getLos();
        int i = 0;
        System.out.println("sl\t\tFname\t\tLname\t\tContact\t\t\tID\t\tTuition");
        System.out.println("-------------------------------------------------------------");
        for (Student student : los) {

            System.out.printf("%d\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n\n", i,
                    student.getFname(),
                    student.getLname(),
                    student.getContact(),
                    student.getId(),
                    student.getStatus());
            i++;

        }

    }

    //EFFECTS: Handles input for updating student
    public void updateResponseHandler() {
        System.out.println("To update all the information of a student, type \"all\"");
        System.out.println("To update a specific information of a student, type 'spec' ");
        Scanner input = new Scanner(System.in);
        String resp = input.next();

        if (resp.equals("all")) {
            updateAllHandler();
        } else if (resp.equals("spec")) {
            updateHandler();
        } else {
            System.out.println("Invalid input");
        }


    }

    //EFFECTS: Displays welcome message

    public void welcome() {
        System.out.println("==============================================");
        System.out.println("***WELCOME TO UBC STUDENT MANAGEMENT SYSTEM***");
        System.out.println("==============================================");
    }


    // EFFECTS: saves the ManagementSystem to file
    private void saveManagementSystem() {
        try {
            jsonWriter.open();
            jsonWriter.write(ms);
            jsonWriter.close();
            System.out.println("Saved " + ms.getName() + " to " + JSON_LOCATION);
            showTable();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_LOCATION);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads ManagementSystem from file
    private void loadManagementSystem() {
        try {
            ms = jsonReader.read();
            System.out.println("Loaded " + ms.getName() + " from " + JSON_LOCATION);
            showTable();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_LOCATION);
        }
    }

}
