package ui;

import model.Event;
import model.EventLog;
import model.ManagementSystem;
import model.Student;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

//Graphical User Interface for Student Management System
public class MSLauncherGUI extends JFrame implements ActionListener {

    private static final String JSON_LOCATION = "./data/managementData.json";
    private final String[] column = {"First Name", "Last Name", "Contact", "ID", "Status"};
    private final String[] rowData = new String[5];
    private Font font1;
    private Font font2;
    private ImageIcon icon;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Container container;
    private JLabel fnL;
    private JLabel lnL;
    private JLabel contL;
    private JLabel statusL;
    private JLabel idL;
    private JTextField fnTf;
    private JTextField lnTf;
    private JTextField statusTf;
    private JTextField idTf;
    private JTextField contTf;
    private JButton addButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton updateButton;
    private JButton removeButton;
    private JButton clearButton;
    private JTable table;
    private JScrollPane sp;
    private DefaultTableModel model;
    private ManagementSystem ms = new ManagementSystem("UBC CPSC 210");

    //EFFECTS: launches the gui application and sets up font
    MSLauncherGUI() {


        font1 = new Font("Century Gothic", Font.BOLD, 17);
        font2 = new Font("Century Gothic", Font.BOLD, 22);
        initComponents();
    }

    //MODIFIES: this
    //EFFECTS: sets up all the components and functionalities of the management system
    public void initComponents() {

        icon = new ImageIcon("data/ubcicon.png");
        exitApp();
        this.setResizable(false);
        JLabel imgLabel = new JLabel(icon);
        imgLabel.setBounds(600, 80, 150, 220);
        setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("Student Management System");
        container = this.getContentPane();
        container.setLayout(null);
        container.add(imgLabel);
        container.setBackground(Color.decode("#27a9b5"));
        createTitleLabel("UBC STUDENT MANAGEMENT SYSTEM");
        createFnFields("First Name");
        createLnFields("Last Name");
        createContactFields("Contact");
        createIdFields("ID");
        createStatusFields("Status");
        addTable();
        createButtons();

    }

    //MODIFIES: this
    //EFFECTS: creates the title field
    public void createTitleLabel(String title) {

        Font font1 = new Font("Century Gothic", Font.BOLD, 17);
        fnL = new JLabel(title);
        fnL.setFont(font2);
        fnL.setBounds(110, 10, 400, 50);
        container.add(fnL);


    }


    //MODIFIES: this
    //EFFECTS: creates the first name fields
    public void createFnFields(String title) {


        fnL = new JLabel(title);
        fnL.setFont(font1);
        fnL.setBounds(10, 80, 140, 30);
        container.add(fnL);

        fnTf = new JTextField();
        fnTf.setBounds(110, 80, 200, 30);
        fnTf.setFont(font1);
        container.add(fnTf);


    }

    //MODIFIES: this
    //EFFECTS: creates the last name field
    public void createLnFields(String title) {
        lnL = new JLabel(title);
        lnL.setFont(font1);
        lnL.setBounds(10, 130, 150, 30);
        container.add(lnL);

        lnTf = new JTextField();
        lnTf.setBounds(110, 130, 200, 30);
        lnTf.setFont(font1);
        container.add(lnTf);


    }

    //MODIFIES: this
    //EFFECTS: creates the contact field
    public void createContactFields(String title) {
        contL = new JLabel(title);
        contL.setFont(font1);
        contL.setBounds(10, 180, 140, 30);
        container.add(contL);

        contTf = new JTextField();
        contTf.setBounds(110, 180, 200, 30);
        contTf.setFont(font1);
        container.add(contTf);


    }

    //MODIFIES: this
    //EFFECTS: creates the id field
    public void createIdFields(String title) {
        idL = new JLabel(title);
        idL.setFont(font1);
        idL.setBounds(10, 230, 200, 50);
        container.add(idL);

        idTf = new JTextField();
        idTf.setBounds(110, 230, 200, 30);
        idTf.setFont(font1);
        container.add(idTf);


    }

    //MODIFIES: this
    //EFFECTS: creates the status field
    public void createStatusFields(String title) {

        statusL = new JLabel(title);
        statusL.setFont(font1);
        statusL.setBounds(10, 280, 500, 50);
        container.add(statusL);
        statusTf = new JTextField();
        statusTf.setBounds(110, 280, 200, 30);
        statusTf.setFont(font1);
        container.add(statusTf);
    }

    //MODIFIES: this
    //EFFECTS: creates a table and adds it to the gui

    public void addTable() {
        table = new JTable();
        table.setFont(font1);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        table.setModel(model);
        table.setFont(font1);
        table.setSelectionBackground(Color.decode("#D9658E"));
        table.setBackground(Color.decode("#D3E5AD"));
        table.setRowHeight(30);
        sp = new JScrollPane(table);
        sp.setBounds(10, 360, 740, 265);
        container.add(sp);
        mouseHandler();


    }
    // MODIFIES: this
    //EFFECTS: gets value from the selected row and assigns to form field

    public void mouseHandler() {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int rowNum = table.getSelectedRow();
                String fname = model.getValueAt(rowNum, 0).toString();
                String lname = model.getValueAt(rowNum, 1).toString();
                String contact = model.getValueAt(rowNum, 2).toString();
                String id = model.getValueAt(rowNum, 3).toString();
                String status = model.getValueAt(rowNum, 4).toString();
                fnTf.setText(fname);
                lnTf.setText(lname);
                contTf.setText(contact);
                idTf.setText(id);
                statusTf.setText(status);

            }
        });
    }

    //MODIFIES: this
    //EFFECTS: creates add, remove, update, save and load buttons
    public void createButtons() {

        addButton = new JButton("Add");
        addButton.setBounds(380, 80, 200, 30);
        addButton.setFont(font1);
        container.add(addButton);


        removeButton = new JButton("Remove");
        removeButton.setBounds(380, 130, 200, 30);
        removeButton.setFont(font1);
        container.add(removeButton);


        updateButton = new JButton("Update");
        updateButton.setBounds(380, 180, 200, 30);
        updateButton.setFont(font1);
        container.add(updateButton);


        saveButton = new JButton("Save");
        saveButton.setBounds(380, 230, 200, 30);
        saveButton.setFont(font1);
        container.add(saveButton);

        loadButton = new JButton("Load");
        loadButton.setBounds(380, 280, 200, 30);
        loadButton.setFont(font1);
        container.add(loadButton);

        activateButtons();


    }

    //MODIFIES: this
    //EFFECTS: adds a listener to each button to handle the response; Creates clear button as well;
    public void activateButtons() {
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        updateButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);


        //Creating clear button
        clearButton = new JButton("Clear Form");
        clearButton.setBounds(580, 15, 130, 30);
        clearButton.setFont(font1);
        container.add(clearButton);
        clearButton.setBackground(Color.YELLOW);
        clearButton.setForeground(Color.BLACK);
        clearButton.addActionListener(this);


    }

    //MODIFIES: this
    //EFFECTS: loads data from JSON File to the table
    public void loadData() {
        loadManagementSystem();
        List<Student> students = ms.getLos();


        for (Student student : students) {
            rowData[0] = student.getFname();
            rowData[1] = student.getLname();
            rowData[2] = student.getContact();
            rowData[3] = student.getId();
            rowData[4] = student.getStatus();
            model.addRow(rowData);

        }
    }

    // EFFECTS: saves the ManagementSystem to file
    private void saveManagementSystem() {
        try {
            jsonWriter.open();
            jsonWriter.write(ms);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved Successfully");

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(null, "Can't Save File", "File error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads ManagementSystem from file
    private void loadManagementSystem() {
        try {
            ms = jsonReader.read();
//            System.out.println("Loaded " + ms.getName() + " from " + JSON_LOCATION);
        } catch (IOException e) {

            loadButton.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Can't Open File", "File error", JOptionPane.ERROR_MESSAGE);

        }
    }

    //MODIFIES: this
    //EFFECTS: handles button event
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = ms.getTotalStudents();

        if (e.getSource() == addButton) {
            addButtonHandler(index);

        } else if (e.getSource() == removeButton) {

            removeButtonHandler();
        } else if (e.getSource() == updateButton) {

            updateButtonHandler();
        } else if (e.getSource() == saveButton) {

            saveButtonHandler();
        } else if (e.getSource() == loadButton) {
            loadButtonHandler();

        } else if (e.getSource() == clearButton) {
            clearButtonHandler();
        }
    }

    //MODIFIES: this
    //EFFECTS: clears data from text field
    public void clearButtonHandler() {
        fnTf.setText("");
        lnTf.setText("");
        contTf.setText("");
        idTf.setText("");
        statusTf.setText("");

    }

    //MODIFIES: this
    //EFFECTS: loads data when loadbutton is pressed. Button can be pressed only once;
    private void loadButtonHandler() {
        jsonReader = new JsonReader(JSON_LOCATION);
        loadData();
        loadButton.setEnabled(false);
    }

    //MODIFIES: this
    //EFFECTS: saves data when save button is pressed.
    private void saveButtonHandler() {
        jsonWriter = new JsonWriter(JSON_LOCATION);
        saveManagementSystem();
    }

    //MODIFIES: this
    //EFFECTS: updates data when update button is pressed.
    private void updateButtonHandler() {


        String fname = fnTf.getText();
        String lname = lnTf.getText();
        String contact = contTf.getText();
        String id = idTf.getText();
        String status = statusTf.getText();

        int rowNum = table.getSelectedRow();

        model.setValueAt(fname, rowNum, 0);
        model.setValueAt(lname, rowNum, 1);
        model.setValueAt(contact, rowNum, 2);
        model.setValueAt(id, rowNum, 3);
        model.setValueAt(status, rowNum, 4);

        //Updating student in the ArrayList in ms
        Student tempStudent = new Student(fname, lname, contact, id, status);
        ms.removeStudent(rowNum);
        if (rowNum < 1) {
            rowNum = 0;
        }

        ms.insertStudent(tempStudent, rowNum);


    }

    //MODIFIES: this
    //EFFECTS: removes data when remove button  is pressed on selected row.
    private void removeButtonHandler() {

        int rowNum = table.getSelectedRow();
        if (rowNum >= 0) {
            model.removeRow(rowNum);
            ms.removeStudent(rowNum);
        } else {
            JOptionPane.showMessageDialog(null, "No row has been selected");
        }

    }

    //MODIFIES: this
    //EFFECTS: adds student when add button is pressed.
    public void addButtonHandler(int index) {

        rowData[0] = fnTf.getText();
        rowData[1] = lnTf.getText();
        rowData[2] = contTf.getText();
        rowData[3] = idTf.getText();
        rowData[4] = statusTf.getText();
        Student tempStudent = new Student(fnTf.getText(), lnTf.getText(), contTf.getText(), idTf.getText(),
                statusTf.getText());

        if (index < 1) {
            index = 0;
        }

        ms.insertStudent(tempStudent, index);
        model.addRow(rowData);

    }
//EFFECTS: prints event log when application exits

    private void exitApp() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {


                JFrame frame = (JFrame) e.getSource();

                int result = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to exit the application?",
                        "Exit Application", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    ms.logPrinter(EventLog.getInstance());
                    System.exit(0);

                }
            }

        });
    }
}
