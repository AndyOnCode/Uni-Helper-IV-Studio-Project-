package ec.edu.espe.ivstudio.unihelper.view;

import ec.edu.espe.ivstudio.unihelper.controller.TaskManager;
import ec.edu.espe.ivstudio.unihelper.model.Task;
import ec.edu.espe.ivstudio.unihelper.model.TaskStatus;
import java.time.LocalDate;
import java.util.List;

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
 
    }
    
 
}
