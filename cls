[33mcommit b0736bde0d562bb75528e400bb31aebf2df8c274[m[33m ([m[1;36mHEAD -> [m[1;32mmaster[m[33m, [m[1;31morigin/master[m[33m, [m[1;31morigin/HEAD[m[33m)[m
Author: Nazif <nzfishrak60@gmail.com>
Date:   Wed Mar 30 22:24:49 2022 -0700

    Finished phase 4

[1mdiff --git a/README.md b/README.md[m
[1mindex ca68dc7..74e5037 100644[m
[1m--- a/README.md[m
[1m+++ b/README.md[m
[36m@@ -14,6 +14,27 @@[m [mI'm interested in making this application as I feel <i>I'll learn in depth about[m
 - _As a user, I want to be able to **save my management system state to file**_[m
 - _As a user, I want to be able to be able to **load my management system data** from file_[m
 [m
[32m+[m[32m## Phase 4: Task 2[m
[32m+[m[32mWhen a user is added, an event with description student added to system is added to the EventLog[m
[32m+[m[32mand when a student is removed an event with description student is removed from the system is added[m
[32m+[m[32mto the EventLog. If no students are added or removed then the eventlog is empty.[m
[32m+[m
[32m+[m[32mFor example:[m
[32m+[m[32mIf Student1 is added and then removed the output is as follows once the application closes:[m
[32m+[m
[32m+[m[32m- Student added to system[m
[32m+[m[32m- Student removed from system[m
[32m+[m[32m## Phase 4: Task 3[m
[32m+[m[32mSome things that can be improved in my projects design:[m
[32m+[m
[32m+[m[32m- removing the MSLauncher class and transfer necessary methods to MSLauncherGUI since MSLauncher[m[41m [m
[32m+[m[32mclass is no longer relevant for the GUI phase, but it still contains some methods that are useful.[m
[32m+[m[32m- additionButtonHandler and updateButtonHandler in MSlauncherGUI class have some similar behaviors[m[41m [m
[32m+[m[32min manipulating user inputs to add to the file system. Code duplication can be reduced by creating[m[41m [m
[32m+[m[32ma single function that deals with this problem.[m[41m [m
[32m+[m[32m- initComponents method is not performing only a single type of task. Certain parts of the method can be encapsulated under different helper functions to make the function more consistent with the purpose.[m
[41m+[m
[41m+[m
 [m
 [m
 [m
[1mdiff --git a/UML_Design_Diagram.jpg b/UML_Design_Diagram.jpg[m
[1mnew file mode 100644[m
[1mindex 0000000..8e58f8b[m
Binary files /dev/null and b/UML_Design_Diagram.jpg differ
[1mdiff --git a/data/managementData.json b/data/managementData.json[m
[1mindex dd5011b..c8969e9 100644[m
[1m--- a/data/managementData.json[m
[1m+++ b/data/managementData.json[m
[36m@@ -4,30 +4,37 @@[m
         {[m
             "fname": "Nazif",[m
             "lname": "Ishrak",[m
[31m-            "contact": "2369920300",[m
[31m-            "id": "abc",[m
[32m+[m[32m            "contact": "323233232",[m
[32m+[m[32m            "id": "sdd",[m
             "status": "paid"[m
         },[m
         {[m
[31m-            "fname": "Mr",[m
[31m-            "lname": "Robot",[m
[31m-            "contact": "123456",[m
[31m-            "id": "djd",[m
[31m-            "status": "paid"[m
[32m+[m[32m            "fname": "Nafis",[m
[32m+[m[32m            "lname": "Ishrak",[m
[32m+[m[32m            "contact": "3235553232",[m
[32m+[m[32m            "id": "gaf",[m
[32m+[m[32m            "status": "unpaid"[m
         },[m
         {[m
[31m-            "fname": "Samuel ",[m
[31m-            "lname": "Jackson",[m
[31m-            "contact": "654321",[m
[31m-            "id": "jvks",[m
[32m+[m[32m            "fname": "Jeremy",[m
[32m+[m[32m            "lname": "Luigi",[m
[32m+[m[32m            "contact": "323233232",[m
[32m+[m[32m            "id": "sda",[m
             "status": "paid"[m
         },[m
         {[m
[31m-            "fname": "Hugh",[m
[31m-            "lname": "Stew",[m
[31m-            "contact": "47896100",[m
[31m-            "id": "jdj",[m
[32m+[m[32m            "fname": "Ayush ",[m
[32m+[m[32m            "lname": "Joshi",[m
[32m+[m[32m            "contact": "15145456",[m
[32m+[m[32m            "id": "shj",[m
             "status": "paid"[m
[32m+[m[32m        },[m
[32m+[m[32m        {[m
[32m+[m[32m            "fname": "vsdv",[m
[32m+[m[32m            "lname": "sdvfsdv",[m
[32m+[m[32m            "contact": "eafewf",[m
[32m+[m[32m            "id": "sdv",[m
[32m+[m[32m            "status": "esf"[m
         }[m
     ][m
 }[m
\ No newline at end of file[m
[1mdiff --git a/src/main/model/Event.java b/src/main/model/Event.java[m
[1mnew file mode 100644[m
[1mindex 0000000..924e57d[m
[1m--- /dev/null[m
[1m+++ b/src/main/model/Event.java[m
[36m@@ -0,0 +1,67 @@[m
[32m+[m[32mpackage model;[m
[32m+[m
[32m+[m[32mimport java.util.Calendar;[m
[32m+[m[32mimport java.util.Date;[m
[32m+[m
[32m+[m
[32m+[m[32m//Represents a management system system event.[m
[32m+[m[32mpublic class Event {[m
[32m+[m
[32m+[m[32m    private static final int HASH_CONSTANT = 13;[m
[32m+[m[32m    private final Date dateLogged;[m
[32m+[m[32m    private final String description;[m
[32m+[m
[32m+[m[32m    /**[m
[32m+[m[32m     * Creates an event with the given description[m
[32m+[m[32m     * and the current date/time stamp.[m
[32m+[m[32m     *[m
[32m+[m[32m     * @param description a description of the event[m
[32m+[m[32m     */[m
[32m+[m[32m    public Event(String description) {[m
[32m+[m[32m        dateLogged = Calendar.getInstance().getTime();[m
[32m+[m[32m        this.description = description;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    /**[m
[32m+[m[32m     * Gets the date of this event (includes time).[m
[32m+[m[32m     *[m
[32m+[m[32m     * @return the date of the event[m
[32m+[m[32m     */[m
[32m+[m[32m    public Date getDate() {[m
[32m+[m[32m        return dateLogged;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    /**[m
[32m+[m[32m     * Gets the description of this event.[m
[32m+[m[32m     *[m
[32m+[m[32m     * @return the description of the event[m
[32m+[m[32m     */[m
[32m+[m[32m    public String getDescription() {[m
[32m+[m[32m        return description;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    public boolean equals(Object other) {[m
[32m+[m[32m        if (other == null) {[m
[32m+[m[32m            return false;[m
[32m+[m[32m        }[m
[32m+[m
[32m+[m[32m        if (other.getClass() != this.getClass()) {[m
[32m+[m[32m            return false;[m
[32m+[m[32m        }[m
[32m+[m
[32m+[m[32m        Event otherEvent = (Event) other;[m
[32m+[m
[32m+[m[32m        return (this.dateLogged.equals(otherEvent.dateLogged) && this.description.equals(otherEvent.description));[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    public int hashCode() {[m
[32m+[m[32m        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    public String toString() {[m
[32m+[m[32m        return dateLogged.toString() + "\n" + description;[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[1mdiff --git a/src/main/model/EventLog.java b/src/main/model/EventLog.java[m
[1mnew file mode 100644[m
[1mindex 0000000..84cfd4c[m
[1m--- /dev/null[m
[1m+++ b/src/main/model/EventLog.java[m
[36m@@ -0,0 +1,64 @@[m
[32m+[m[32mpackage model;[m
[32m+[m
[32m+[m[32mimport java.util.ArrayList;[m
[32m+[m[32mimport java.util.Collection;[m
[32m+[m[32mimport java.util.Iterator;[m
[32m+[m
[32m+[m[32m/**[m
[32m+[m[32m * Represents a log of Management system events.[m
[32m+[m[32m * We use the Singleton Design Pattern to ensure that there is only[m
[32m+[m[32m * one EventLog in the system and that the system has global access[m
[32m+[m[32m * to the single instance of the EventLog.[m
[32m+[m[32m */[m
[32m+[m[32mpublic class EventLog implements Iterable<Event> {[m
[32m+[m[32m    /**