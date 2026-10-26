package ec.edu.espe.ivstudio.unihelper.view;

import ec.edu.espe.ivstudio.unihelper.controller.*;
import ec.edu.espe.ivstudio.unihelper.model.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class for UniHelper.
 * @author Andy Tiban, IV Studio, @ESPE
 */
public class UniHelperApp {

    private static final String USERS_FILE = "users.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=====================================");
        System.out.println("       UNI-HELPER SYSTEM V0.5.0      ");
        System.out.println("=====================================");

        // --- AUTHENTICATION PHASE ---
        List<UserAccount> existingUsers = JsonStorage.loadListFromFile(USERS_FILE, UserAccount.class);
        AuthService authService = new AuthService(existingUsers);
        UserAccount currentUser = null;

        while (currentUser == null) {
            System.out.println("\n--- ACCESS CONTROL ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int authChoice = scanner.nextInt();
            scanner.nextLine();

            switch (authChoice) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    String user = scanner.nextLine().trim();
                    System.out.print("Enter password: ");
                    String pass = scanner.nextLine().trim();

                    if (authService.authenticate(user, pass)) {
                        for (UserAccount account : existingUsers) {
                            if (account.getUsername().equalsIgnoreCase(user)) {
                                currentUser = account;
                                break;
                            }
                        }
                        System.out.println("Authentication successful! Welcome, " + user + ".");
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter new username: ");
                    String newUser = scanner.nextLine().trim();
                    System.out.print("Enter new password: ");
                    String newPass = scanner.nextLine().trim();

                    if (authService.register(newUser, newPass)) {
                        if (saveUsers(authService.getUsers())) {
                            System.out.println("Account registered successfully! Please log in.");
                        } else {
                            System.out.println("Account registered, but could not save users.json.");
                        }
                    } else {
                        System.out.println("Username already exists.");
                    }
                }
                case 3 -> {
                    System.out.println("Exiting Uni-Helper...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        // --- MANAGERS INITIALIZATION BOUND TO CURRENT USER ---
// --- MANAGERS INITIALIZATION ---
        TaskManager manager = new TaskManager();
        CalendarManager calendarManager = new CalendarManager(currentUser.getCalendarEvents());
        GradeManager gradeManager = new GradeManager(currentUser.getGrades());
        StudySessionManager studySessionManager = new StudySessionManager(currentUser.getStudySessions());
        BudgetService budgetService = new BudgetService();
<<<<<<< HEAD
        ScheduleService scheduleService = new ScheduleService();

        // Sample initial academic subjects and tasks
        manager.addSubject("POO01", "Object Programming Oriented", 4);
        manager.addSubject("ED02", "Data Structures", 4);
        manager.createTask("TSK-01", "Polymorphism Workshop", LocalDate.now().plusDays(1), 3, "POO01");
        manager.createTask("TSK-02", "Final Project Phase 1", LocalDate.now().plusDays(3), 8, "POO01");
        manager.createTask("TSK-03", "Linked Lists Reading", LocalDate.now().plusDays(7), 2, "ED02");

        manager.addSubject("BD03", "Databases", 3);
        manager.createTask("TSK-04", "Report", LocalDate.now().minusDays(2), 2, "ED02");
        manager.createTask("TSK-05", "ER Diagram", LocalDate.now().minusDays(5), 4, "BD03");
        manager.createTask("TSK-06", "Workshop", LocalDate.now().minusDays(1), 3, "POO01");
        manager.updateStatus("TSK-06", TaskStatus.COMPLETED);

        // Display sample system checks
        System.out.println("\n--- REGISTERED TASK LIST ---");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }

        System.out.println("\n--- ACTUALIZANDO ESTADO DE TAREA TSK-01 ---");
        manager.updateStatus("TSK-01", TaskStatus.IN_PROGRESS);
        for (Task task : manager.getTasks()) {
            if (task.getId().equals("TSK-01")) {
                System.out.println(task);
            }
        }
        System.out.println("==================================================");

        System.out.println("\n--- TASK FILTER  ---");
        System.out.println("Available materials:");
        for (Subject subject : manager.getSubjects()) {
            System.out.println("- " + subject.getId() + " (" + subject.getName() + ")");
        }

        String subjectId;
        do {
            System.out.print("ID of matter to filter (or 'Exit'): ");
            subjectId = scanner.nextLine().trim();

            if (!subjectId.equalsIgnoreCase("salir")) {
                List<Task> filtered = manager.filterBySubject(subjectId);
                if (filtered.isEmpty()) {
                    System.out.println("There are no assignments for that subject.");
                } else {
                    for (Task task : filtered) {
                        System.out.println(task);
                    }
                }
            }
        } while (!subjectId.equalsIgnoreCase("Exit"));
        System.out.println("==================================================");

        System.out.println("\n--- TASKS DUE ---");
        List<Task> overdue = manager.getOverdueTasks();
        if (overdue.isEmpty()) {
            System.out.println("You have no overdue tasks.");
        } else {
            for (Task task : overdue) {
                System.out.println("ALERT: " + task.getTitle());
            }
        }
        System.out.println("==================================================");

        System.out.println("\n--- BREAKS ---");
        for (Task task : manager.getTasks()) {
            System.out.println(task.getTitle() + ": " + task.getBreakReminder());
        }
=======
        ScheduleService scheduleService = new ScheduleService(currentUser.getScheduleBlocks());
>>>>>>> e02a7ee (JSON report)

        // --- MAIN APPLICATION MENU ---
        int option = 0;
        do {
            System.out.println("\n==== UNI-HELPER: MAIN CONTROL (" + currentUser.getUsername() + ") =====");
            System.out.println("1. Add Events (Calendar)");
            System.out.println("2. View Events (Calendar)");
            System.out.println("3. Add Grade");
            System.out.println("4. View Grades & Average");
            System.out.println("5. Start Study Session");
            System.out.println("6. View Study Sessions");
            System.out.println("7. Calculate Smart Daily Budget");
            System.out.println("8. Add Academic Schedule Block");
            System.out.println("9. View Academic Schedule");
            System.out.println("10. Save and Exit");
            System.out.print("Select an option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter time (HH:MM): ");
                    String time = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    CalendarEvent newEvent = new CalendarEvent(title, date, time, description);
                    calendarManager.addEvent(newEvent);
                    currentUser.getCalendarEvents().add(newEvent);

                    JsonStorage.saveListToFile(USERS_FILE, existingUsers);
                    System.out.println("Event added and saved successfully!");
                    if (saveUsers(existingUsers)) {
                        System.out.println("Event added and saved successfully!");
                    } else {
                        System.out.println("Event added, but could not be saved.");
                    }
                }
                case 2 -> {
                    System.out.println("\n--- SCHEDULED EVENTS ---");
                    if (calendarManager.getEvents().isEmpty()) {
                        System.out.println("No events scheduled yet.");
                    } else {
                        for (CalendarEvent e : calendarManager.getEvents()) {
                            System.out.println(e);
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Enter grade name: ");
                    String gradeName = scanner.nextLine();
                    System.out.print("Enter score: ");
                    double score = scanner.nextDouble();
                    System.out.print("Enter weight (%): ");
                    double weight = scanner.nextDouble();
                    scanner.nextLine();

                    GradeItem newGrade = new GradeItem(gradeName, score, weight);
                    gradeManager.addGrade(newGrade);
                    currentUser.getGrades().add(newGrade);

                    JsonStorage.saveListToFile(USERS_FILE, existingUsers);
                    System.out.println("Grade registered and saved successfully!");
                    if (saveUsers(existingUsers)) {
                        System.out.println("Grade registered and saved successfully!");
                    } else {
                        System.out.println("Grade registered, but could not be saved.");
                    }
                }
                case 4 -> {
                    System.out.println("\n--- GRADED ITEMS ---");
                    if (gradeManager.getGrades().isEmpty()) {
                        System.out.println("No grades registered yet.");
                    } else {
                        for (GradeItem g : gradeManager.getGrades()) {
                            System.out.println(g);
                        }
                        System.out.println("Weighted Average: " + gradeManager.calculationWeightedAverage());
                    }
                }
                case 5 -> {
                    System.out.print("Enter study topic: ");
                    String topic = scanner.nextLine();
                    System.out.print("Enter duration in minutes: ");
                    int duration = scanner.nextInt();
                    scanner.nextLine();


                    StudySession newSession = new StudySession(topic, duration);
                    studySessionManager.startSession(newSession);

                    currentUser.getStudySessions().add(newSession);

                    JsonStorage.saveListToFile(USERS_FILE, existingUsers);
                    System.out.println("Study session recorded and saved!");
                    if (saveUsers(existingUsers)) {
                        System.out.println("Study session recorded and saved!");
                    } else {
                        System.out.println("Study session completed, but could not be saved.");
                    }
                }
                case 6 -> {
                    System.out.println("\n--- COMPLETED STUDY SESSIONS ---");
                    if (studySessionManager.getCompletedSessions().isEmpty()) {
                        System.out.println("No study sessions completed yet.");
                    } else {
                        for (StudySession s : studySessionManager.getCompletedSessions()) {
                            System.out.println(s);
                        }
                        System.out.println("Total Study Time: " + studySessionManager.getTotalStudyTimeMinutes() + " minutes.");
                    }
                }
                case 7 -> {
                    System.out.println("\n--- SMART BUDGET CALCULATOR ---");
                    System.out.println("Select Profile: 1. SAVE | 2. RECOMMENDED | 3. SOCIAL");
                    System.out.print("Choice: ");
                    String tierChoice = scanner.nextLine().trim();

                    SpendingTier tier = switch (tierChoice) {
                        case "1" -> SpendingTier.SAVE;
                        case "3" -> SpendingTier.SOCIAL;
                        default -> SpendingTier.RECOMMENDED;
                    };

                    System.out.print("Will you eat lunch/dinner on campus? (y/n): ");
                    boolean eatCampus = scanner.nextLine().trim().equalsIgnoreCase("y");

                    System.out.print("Commuting by personal car? (y = car / n = bus): ");
                    boolean car = scanner.nextLine().trim().equalsIgnoreCase("y");

                    System.out.print("Enter academic keywords (comma-separated, e.g., exam, laboratory): ");
                    String kw = scanner.nextLine().trim();
                    List<String> keywords = Arrays.asList(kw.split("\\s*,\\s*"));

                    long budgetCents = budgetService.calculateDailyBudget(tier, eatCampus, car, keywords);
                    System.out.println("Calculated Daily Budget: " + BudgetService.formatCentsToCurrency(budgetCents));
                    currentUser.getBudgetCalculations().add(
                            new BudgetCalculation(tier, eatCampus, car, keywords, budgetCents));
                    if (!saveUsers(existingUsers)) {
                        System.out.println("Budget calculation could not be saved.");
                    }
                }
                case 8 -> {
                    System.out.println("\n--- ADD ACADEMIC SCHEDULE BLOCK ---");
                    System.out.print("Enter Day of Week (e.g., MONDAY, TUESDAY, FRIDAY): ");
                    String dayStr = scanner.nextLine().trim().toUpperCase();

                    try {
                        DayOfWeek day = DayOfWeek.valueOf(dayStr);
                        System.out.print("Enter Block letter (A-H): ");
                        String blockCode = scanner.nextLine().trim();
                        System.out.print("Enter Room number (e.g., 202, 101): ");
                        String roomNum = scanner.nextLine().trim();

                        RoomIdentifier room = new RoomIdentifier(blockCode, roomNum);

                        System.out.print("Enter Subject Name: ");
                        String subject = scanner.nextLine().trim();

                        System.out.print("Enter Special Keywords (e.g., exam, laboratory): ");
                        String kw = scanner.nextLine().trim();
                        List<String> keywords = Arrays.asList(kw.split("\\s*,\\s*"));

                        ScheduleService.AcademicBlock block = new ScheduleService.AcademicBlock(day, room, subject, keywords);
                        scheduleService.addOrUpdateBlock(block, LocalDate.now());
                        if (saveUsers(existingUsers)) {
                            System.out.println("Schedule block saved: " + subject + " in " + room.formatted() + " for " + day);
                        } else {
                            System.out.println("Schedule block added, but could not be saved.");
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("Validation Error: " + e.getMessage());
                    } catch (IllegalStateException e) {
                        System.out.println("Schedule Restriction: " + e.getMessage());
                    }
                }
                case 9 -> {
                    System.out.print("Enter Day of Week to inspect (e.g., MONDAY, THURSDAY): ");
                    String dayStr = scanner.nextLine().trim().toUpperCase();
                    try {
                        DayOfWeek day = DayOfWeek.valueOf(dayStr);
                        List<ScheduleService.AcademicBlock> blocks = scheduleService.getBlocksForDay(day);
                        System.out.println("\n--- SCHEDULE FOR " + day + " ---");
                        if (blocks.isEmpty()) {
                            System.out.println("No classes scheduled for this day.");
                        } else {
                            for (ScheduleService.AcademicBlock b : blocks) {
                                System.out.println("- " + b.subjectName() + " | Classroom: " + b.room().formatted() + " | Keywords: " + b.keywords());
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid day entered.");
                    }
                }
                case 10 -> {
                    if (saveUsers(existingUsers)) {
                        System.out.println("All session data saved. Exiting Uni-Helper...");
                    } else {
                        System.out.println("Some session data could not be saved. Exiting Uni-Helper...");
                    }
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        } while (option != 10);
    }

    private static boolean saveUsers(List<UserAccount> users) {
        return JsonStorage.saveListToFile(USERS_FILE, users);
    }
}