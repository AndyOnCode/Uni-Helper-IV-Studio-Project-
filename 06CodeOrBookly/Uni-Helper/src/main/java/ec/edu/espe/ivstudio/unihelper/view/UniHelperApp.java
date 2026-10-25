package ec.edu.espe.ivstudio.unihelper.view;

import ec.edu.espe.ivstudio.unihelper.controller.CalendarManager;
import ec.edu.espe.ivstudio.unihelper.controller.GradeManager;
import ec.edu.espe.ivstudio.unihelper.controller.StudySessionManager;
import ec.edu.espe.ivstudio.unihelper.controller.TaskManager;
import ec.edu.espe.ivstudio.unihelper.model.CalendarEvent;
import ec.edu.espe.ivstudio.unihelper.model.GradeItem;
import ec.edu.espe.ivstudio.unihelper.model.StudySession;
import ec.edu.espe.ivstudio.unihelper.model.Task;
import ec.edu.espe.ivstudio.unihelper.model.TaskStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Andy Tiban, IV Studio, @ESPE
 */
public class UniHelperApp {
    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("     UNI-HELPER SYSTEM V0.5.0        ");
        System.out.println("=====================================");
        
        TaskManager manager= new TaskManager();

        
manager.addSubject("POO01", "Programacion Orientada a Objetos", 4);
        manager.addSubject("ED02", "Estructuras de Datos", 4);

       
        manager.createTask("TSK-01", "Taller de Polimorfismo", LocalDate.now().plusDays(1), 3, "POO01");
        manager.createTask("TSK-02", "Proyecto Final Fase 1", LocalDate.now().plusDays(3), 8, "POO01");
        manager.createTask("TSK-03", "Lectura de Listas Enlazadas", LocalDate.now().plusDays(7), 2, "ED02");
        
        manager.addSubject("BD03", "Bases de Datos", 3);

        manager.createTask("TSK-04", "Informe", LocalDate.now().minusDays(2), 2, "ED02");
        manager.createTask("TSK-05", "Diagrama ER", LocalDate.now().minusDays(5), 4, "BD03");
        manager.createTask("TSK-06", "Taller", LocalDate.now().minusDays(1), 3, "POO01");
        manager.updateStatus("TSK-06", TaskStatus.COMPLETED);

    
        System.out.println("\n--- LISTA DE TAREAS REGISTRADAS ---");
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
            
        System.out.println("\n--- FILTRO DE TAREAS---");
        for (Task t : manager.filterBySubject("ED02")) {
            System.out.println(t);
        }

        System.out.println("==================================================");   
        
        System.out.println("\n--- TAREAS VENCIDAS ---");
        List<Task> overdue = manager.getOverdueTasks();
        if (overdue.isEmpty()) {
            System.out.println("No tienes tareas vencidas.");
        } else {
            for (Task t : overdue) {
                System.out.println("ALERTA: " + t.getTitle());
            }
        }

        System.out.println("==================================================");   
        
        System.out.println("\n--- DESCANSOS ---");
        for (Task t : manager.getTasks()) {
            System.out.println(t.getTitle() + ": " + t.getBreakReminder());
        }
        
        Scanner scanner = new Scanner (System.in);
           CalendarManager calendarManager = new CalendarManager();
           GradeManager gradeManager = new GradeManager();
           StudySessionManager  studySessionManager = new StudySessionManager();
           int option = 0;
           
           do{
               System.out.println("\n==== Uni-HELPER: CALENDAR =====");
               System.out.println("1. Add Events");
               System.out.println("2. View Events");
               System.out.println("3 Add Grade");
               System.out.println("4. View Grades & Average");
               System.out.println("5. Start tudy Session");
               System.out.println("6. View Study essions");
               System.out.println("7. Exit");
               System.out.println("Slect an option");
               
               option = scanner.nextInt();
               scanner.nextLine();
               
               switch (option){
                   case 1:
                       System.out.println("Enter title: ");
                       String title = scanner.nextLine();
                       
                       System.out.println("Enter date: ");
                       String date = scanner.nextLine();
                       
                       System.out.println("Enter time: ");
                       String time = scanner.nextLine();
                       
                       System.out.println("Enter description: ");
                       String description = scanner.nextLine();
                       
                       CalendarEvent newEvent = new CalendarEvent(title, date, time, description);
                       calendarManager.addEvent(newEvent);
                       break;
                   case 2:
                       System.out.println("\n--- SCEDULED EVENTS ---");
                       for(CalendarEvent e: calendarManager.getEvents()){
                           System.out.println(e);
                       }
                       break;
                       
                   case 3:
                       System.out.println("Enter grade name: ");
                       String gradeName = scanner.nextLine();
                       
                       System.out.println("Enter score: ");
                       double score = scanner.nextDouble();
                       
                       System.out.println("Enter weight (%)");
                       double weight = scanner.nextDouble();
                       scanner.nextLine();
                       
                       GradeItem newGrade = new GradeItem(gradeName, score, weight);
                       gradeManager.addGrade(newGrade);
                       break;
                   case 4:
                       System.out.println("\n--- GRADED ITEMS ---");
                       for(GradeItem g : gradeManager.getGrades()){
                           System.out.println(g);
                       }
                       System.out.println("Weighted Average: " + gradeManager.calculationWeightedAverage());
                       break;
                   case 5:
                       System.out.println("Enter study topic: ");
                       String topic = scanner.nextLine();
                       
                       System.out.println("Enter duration in minutes: ");
                       int duration = scanner.nextInt();
                       scanner.nextLine();
                       
                       StudySession newSession = new StudySession(topic, duration);
                       studySessionManager.startSession(newSession);
                       break;
                       
                   case 6:
                       System.out.println("\n--- COMPLETED STUDY SESSIONS ---");
                       if(studySessionManager.getCompletedSessions().isEmpty()){
                           System.out.println("No study sessions completed yet.");
                       }else{
                           for(StudySession s : studySessionManager.getCompletedSessions()){
                           System.out.println(s);   
                           }
                           System.out.println("Total Study Time: " + studySessionManager.getTotalStudyTimeMinutes() + "minutes.");
                       }
                       break;
                       
                   case 7:
                       System.out.println("Exiting program...");
                       break;
                   default:
                       System.out.println("Invalid option. Try again.");
               }
           }while (option !=7);
 
    }
    
 
}
