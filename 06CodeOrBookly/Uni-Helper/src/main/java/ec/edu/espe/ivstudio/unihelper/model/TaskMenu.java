
package ec.edu.espe.ivstudio.unihelper.model;

import ec.edu.espe.ivstudio.unihelper.controller.TaskManager;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nohemi Tocagon, IV Studio, @ESPE
 */
public class TaskMenu {
    private final Scanner scanner;
    private final TaskManager manager;

    public TaskMenu(Scanner scanner, TaskManager manager) {
        this.scanner = scanner;
        this.manager = manager;
    }

    public void addTask() {
        System.out.println("\n--- ADD TASK ---");

        System.out.print("Subject ID (e.g. POO01): ");
        String subjectId = scanner.nextLine().trim().toUpperCase();
        if (subjectId.isEmpty()) {
            System.out.println("The ID cannot be empty.");
            return;
        }

        boolean exists = false;
        for (Subject subject : manager.getSubjects()) {
            if (subject.getId().equalsIgnoreCase(subjectId)) {
                exists = true;
            }
        }

        if (!exists) {
            System.out.print("Subject name: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("The name cannot be empty.");
                return;
            }
            
        int credits;
        try {
            System.out.print("Credits: ");
            credits = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Credits must be a number.");
            return;
        }
        if (credits < 1) {
            System.out.println("Credits must be greater than 0.");
            return;
        }
        
        manager.addSubject(subjectId, name, credits);
        System.out.println("Subject " + subjectId + " saved successfully.");
        }

        System.out.print("Task Title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("The title cannot be empty.");
            return;
        }

        LocalDate dueDate;
        try {
            System.out.print("Deadline date (YYYY-MM-DD): ");
            dueDate = LocalDate.parse(scanner.nextLine().trim());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date. Use YYYY-MM-DD format.");
            return;
        }

        int priority;
        try {
            System.out.print("Priority (1 = Urgent, 2 = Important, 3 = Less important): ");
            priority = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Priority must be a number.");
            return;
        }
        if (priority < 1 || priority > 3) {
            System.out.println("Priority must be between 1 and 3.");
            return;
        }

        int n = manager.getTasks().size() + 1;
        String taskId;
        boolean taken;
        do {
            taskId = String.format("TSK-%02d", n++);
            taken = false;
            for (Task t : manager.getTasks()) {
                if (t.getId().equalsIgnoreCase(taskId)) {
                    taken = true;
                }
            }
        } while (taken);

        manager.createTask(taskId, title, dueDate, priority, subjectId);
        System.out.println("Task " + taskId + " created for " + subjectId + ".");
    }

    public void filterBySubject() {
        System.out.println("\n--- TASK FILTER ---");

        if (manager.getSubjects().isEmpty()) {
            System.out.println("No subjects registered.");
            return;
        }

        System.out.println("Available subjects:");
        for (Subject s : manager.getSubjects()) {
            System.out.println("- " + s.getId() + " (" + s.getName() + ")");
        }

        String subjectId;
        do {
            System.out.print("Subject ID to filter (or 'exit'): ");
            subjectId = scanner.nextLine().trim();
            if (!subjectId.equalsIgnoreCase("exit")) {
                List<Task> filtered = manager.filterBySubject(subjectId);
                if (filtered.isEmpty()) {
                    System.out.println("No tasks for that subject.");
                } else {
                    for (Task t : filtered) {
                        System.out.println(t);
                    }
                }
            }
        } while (!subjectId.equalsIgnoreCase("exit"));
    }
        public void changeTaskStatus() {
        System.out.println("\n--- CHANGE TASK STATUS ---");

        if (manager.getTasks().isEmpty()) {
            System.out.println("No tasks registered.");
            return;
        }

        // Mostrar las tareas para elegir
        for (Task t : manager.getTasks()) {
            System.out.println(t);
        }

        System.out.print("Task ID (e.g. TSK-01): ");
        String taskId = scanner.nextLine().trim().toUpperCase();
        if (taskId.isEmpty()) {
            System.out.println("The ID cannot be empty.");
            return;
        }

        System.out.println("New status:");
        System.out.println("1. PENDING");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. DONE");
        System.out.print("Option: ");

        int option;
        try {
            option = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Option must be a number.");
            return;
        }

        TaskStatus newStatus;
        switch (option) {
            case 1:
                newStatus = TaskStatus.PENDING;
                break;
            case 2:
                newStatus = TaskStatus.IN_PROGRESS;
                break;
            case 3:
                newStatus = TaskStatus.COMPLETED;
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }

        manager.updateStatus(taskId, newStatus);
        System.out.println("Status updated.");
    }
}
