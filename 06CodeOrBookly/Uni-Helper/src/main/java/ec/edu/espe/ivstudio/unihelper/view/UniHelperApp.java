package ec.edu.espe.ivstudio.unihelper.view;

import ec.edu.espe.ivstudio.unihelper.controller.TaskManager;
import ec.edu.espe.ivstudio.unihelper.model.Task;
import ec.edu.espe.ivstudio.unihelper.model.TaskStatus;
import java.time.LocalDate;

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
            
        

        
        
        
        
        
    }
    
 
}
