# Student Management System 🏫  
## by Nazif Ishrak

<p>A <b>simple student management system</b> that can be used by schools to keep record of student data and make necessary changes to their database of students
I'm interested in making this application as I feel <i>I'll learn in depth about how such management systems work in real life</i>. Even though the application is very simple and has limited features, it is a stepping stone for creating more useful software.
</p>

## User Stories:
- _As a user I want to be able to **add** students to the management system_
- _As a user I want to be able to **view** my entries_
- _As a user I want to be able to **delete** my entries_
- _As a user I want to be able to **update a whole** entry_
- _As a user I want to be able to **update specific cells** from entries_
- _As a user, I want to be able to **save my management system state to file**_
- _As a user, I want to be able to be able to **load my management system data** from file_

## Phase 4: Task 2
When a user is added, an event with description student added to system is added to the EventLog
and when a student is removed an event with description student is removed from the system is added
to the EventLog. If no students are added or removed then the eventlog is empty.

For example:
If Student1 is added and then removed the output is as follows once the application closes:

- Student added to system
- Student removed from system
## Phase 4: Task 3
Some things that can be improved in my projects design:

- removing the MSLauncher class and transfer necessary methods to MSLauncherGUI since MSLauncher 
class is no longer relevant for the GUI phase, but it still contains some methods that are useful.
- additionButtonHandler and updateButtonHandler in MSlauncherGUI class have some similar behaviors 
in manipulating user inputs to add to the file system. Code duplication can be reduced by creating 
a single function that deals with this problem. 
- initComponents method is not performing only a single type of task. Certain parts of the method can be encapsulated under different helper functions to make the function more consistent with the purpose.





