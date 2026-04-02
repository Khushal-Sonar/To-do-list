Gemini said
Here is a clean, console-based To-Do List application in Java. It uses an object-oriented approach by creating a Task class and manages the list using an ArrayList.

☕ The Java Code
Save this code in a file named TodoListApp.java.

Java
import java.util.ArrayList;
import java.util.Scanner;

// Class to represent a single Task
class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        String status = isCompleted ? "[✓]" : "[ ]";
        return status + " " + description;
    }
}

// Main application class
public class TodoListApp {
    private static ArrayList<Task> todoList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=== Welcome to Your Java To-Do List ===");

        while (running) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    viewTasks();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    completeTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    System.out.println("\nGoodbye! Have a productive day.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please pick between 1 and 5.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n----------------------------");
        System.out.println("1. View Tasks");
        System.out.println("2. Add a Task");
        System.out.println("3. Mark Task as Completed");
        System.out.println("4. Delete a Task");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a valid number. Try again.");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character left behind
        return choice;
    }

    private static void viewTasks() {
        System.out.println("\n--- Your Tasks ---");
        if (todoList.isEmpty()) {
            System.out.println("Your list is empty. Add some tasks!");
            return;
        }
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println((i + 1) + ". " + todoList.get(i));
        }
    }

    private static void addTask() {
        System.out.print("\nEnter task description: ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            System.out.println("Task cannot be empty!");
            return;
        }
        todoList.add(new Task(description));
        System.out.println("Task added successfully!");
    }

    private static void completeTask() {
        if (todoList.isEmpty()) {
            System.out.println("\nNo tasks to mark as completed.");
            return;
        }
        viewTasks();
        System.out.print("\nEnter the number of the task to complete: ");
        int taskNumber = getUserChoice();

        if (taskNumber > 0 && taskNumber <= todoList.size()) {
            todoList.get(taskNumber - 1).markAsCompleted();
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void deleteTask() {
        if (todoList.isEmpty()) {
            System.out.println("\nNo tasks to delete.");
            return;
        }
        viewTasks();
        System.out.print("\nEnter the number of the task to delete: ");
        int taskNumber = getUserChoice();

        if (taskNumber > 0 && taskNumber <= todoList.size()) {
            todoList.remove(taskNumber - 1);
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Invalid task number.");
        }
    }
}
