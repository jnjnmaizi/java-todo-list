# Java To-Do List

A Java application for organizing personal, important, and work tasks. It combines a Swing desktop interface with a console menu and stores tasks in a custom singly linked list.

The project uses inheritance to give each task type its own details, while sharing the same operations for adding, finding, completing, and removing tasks.

## Program Output

See [OUTPUT.md](OUTPUT.md) for a sample run showing task details, subtasks, completion, and error messages. You can reproduce it with [ExampleRun.java](ExampleRun.java).

## Features

- Create personal tasks with a title and note.
- Add important tasks with a deadline and optional urgency level.
- Break work tasks into subtasks.
- Display tasks in the order they were added.
- Find, complete, and remove tasks by title.
- Reject duplicate titles without distinguishing uppercase and lowercase letters.
- Limit the number of tasks in a list.
- Save and read a list through Java object serialization. The application startup paths have limitations described below.

## Tools Used

| Tool or concept | Purpose |
| --- | --- |
| Java | Application logic and task models |
| Swing | Main window, task forms, and dialogs |
| Abstract classes and inheritance | Shared task fields with different task types |
| Polymorphism | Each task provides its own `display()` output |
| Linked list | Stores the main list through `Node` objects |
| Arrays | Stores each work task's subtasks |
| Custom exceptions | Reports duplicate titles and missing tasks |
| Serialization | Writes and reads the list in `tasks.dat` |

The project needs only a JDK. No external libraries are required.

## Project Structure

```text
java-todo-list/
├── Tasks.java                    # Abstract task class
├── PersonalTask.java             # Personal task
├── ImportantTask.java            # Task with a deadline
├── urgencyLevel.java             # Important task with urgency
├── WorkTask.java                 # Task with an array of subtasks
├── SubTask.java                  # Subtask details
├── Node.java                     # Linked-list node
├── toDoList.java                 # List operations and serialization
├── DuplicateTaskException.java
├── TaskNotFoundException.java
├── firstFrame.java               # Main Swing window and GUI entry point
├── AddPersonalTaskFrame.java
├── AddImportantTaskFrame.java
├── AddWorkTaskFrame.java
├── testToDo.java                  # Console setup, GUI, and console menu
├── ExampleRun.java               # Reproducible console example
├── OUTPUT.md
├── README.md
└── .gitignore
```

The source keeps its original package, `com.gamar.project_netbeans`. The archive contains Java source files rather than a complete NetBeans project configuration.

## How It Works

### Task Types

`Tasks` is an abstract class holding a title, note, and completion flag. `PersonalTask`, `ImportantTask`, and `WorkTask` extend it and provide their own display methods. `urgencyLevel` extends `ImportantTask` with an additional urgency string.

`isDone()` marks a task as complete and prints a message. Calling it again reports that the task is already done.

### The Linked List

Each `Node` holds a `Tasks` object and a reference to the next node. `toDoList` tracks the head, current number of tasks, and maximum capacity.

```text
head -> [PersonalTask] -> [ImportantTask] -> [WorkTask] -> null
```

Adding a task checks capacity and searches for an existing title before appending a node. Searching follows the links and compares titles with `equalsIgnoreCase()`. Removing a task updates the head or the previous node's link.

### Work Tasks and Subtasks

A `WorkTask` creates a subtask array with a capacity chosen when the task is created. `addSubTask()` copies each `SubTask` into the array while space remains. Subtasks have names; completion is tracked for the parent task rather than for individual subtasks.

### The Desktop Interface

`firstFrame` provides buttons for adding each task type, displaying tasks, removing tasks, marking tasks as done, and exiting. Separate forms collect each task's details.

**Display All Tasks writes to the terminal or IDE output window.** The current Swing interface does not include a task table or text area for these results.

### Saving Tasks

`toDoList.saveToFile()` serializes the list into `tasks.dat` in the working directory. `readFromFile()` reconstructs it. The task classes, nodes, and subtasks implement `Serializable`, allowing the complete list to be stored.

These methods work when called directly, but the current launchers start a fresh list: `firstFrame.main()` creates a new list, and `testToDo.main()` replaces the list it has just loaded. See the notes below before relying on data being restored at startup.

## Running the Project

The source and console example were compiled and checked with OpenJDK 25.

```bash
git clone https://github.com/jnjnmaizi/java-todo-list.git
cd java-todo-list
javac -encoding UTF-8 -d out *.java
```

### Open the Desktop App

```bash
java -cp out com.gamar.project_netbeans.firstFrame
```

This opens the Swing window with a new list named `My List` and a capacity of 10,000 tasks. A graphical desktop is required. Keep the terminal open to see task output.

### Use the Console Menu

```bash
java -cp out com.gamar.project_netbeans.testToDo
```

Enter a list name and maximum number of tasks. This launcher opens the Swing window as well as the console menu, so it also requires a graphical desktop. A missing `tasks.dat` file is reported during the initial load attempt.

### Run the Output Example

```bash
java -cp out com.gamar.project_netbeans.ExampleRun
```

The example creates a few tasks, displays them, marks a task as done, and demonstrates duplicate-title and missing-task messages. It works without a graphical desktop and does not read or write `tasks.dat`.

### NetBeans

Create a Java application project, add the original source files under the package `com.gamar.project_netbeans`, and select `firstFrame` or `testToDo` as the main class. The terminal commands above work directly with the flat source layout in this repository.

## Time Complexity

Let `n` be the number of tasks and `s` the number of subtasks in a work task. These estimates exclude string lengths and file I/O.

| Operation | Worst-case time |
| --- | --- |
| Add a task | `O(n)` for duplicate checking and appending |
| Find a task | `O(n)` |
| Remove a task by title | `O(n)` |
| Mark a task complete by title | `O(n)` for lookup, then `O(1)` |
| Add a subtask to an available array slot | `O(1)` |
| Display one work task | `O(s)` |
| Display the entire list | `O(n + total subtasks)` |

List nodes use `O(n)` space. Each work task also allocates an array sized to its selected subtask capacity.

## Testing and Known Issues

Compilation and checks of the task logic passed for capacity limits, case-insensitive search, completion, duplicate detection, removal, missing-task exceptions, and saving/loading through direct serialization calls. The console output example also ran successfully. The Swing interaction flows have not been tested end to end.

- **Startup loading:** Both launch paths replace saved state with a new list instead of continuing with the loaded one.
- **Saving from the GUI:** The Exit button saves, but closing the main window with its window-close control does not explicitly save. Marking a task done in the GUI also does not immediately save, unlike the console action.
- **Input and errors:** Some duplicate-title and missing-task exceptions are not caught by the launchers. Invalid numeric input and negative subtask counts need handling. Deadlines and urgency values are stored as strings without format validation.
- **Success messages:** When a list is full, `addTask()` prints a message and returns, but the GUI forms can still display an addition-success dialog.
- **GUI and console together:** `testToDo` gives both interfaces access to the same list without synchronization. Exiting its console menu returns from `main`, but an open Swing window can keep the process running.

The application logic and original author comments are retained. One Arabic inline comment was translated to English, and `ExampleRun.java` was added to demonstrate the output.
