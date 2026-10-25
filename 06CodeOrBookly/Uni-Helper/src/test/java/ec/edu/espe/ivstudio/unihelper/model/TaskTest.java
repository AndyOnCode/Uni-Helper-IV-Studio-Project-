package ec.edu.espe.ivstudio.unihelper.model;

import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Andy Tiban, IV Studio, @ESPE
 */
public class TaskTest {
    
    private Subject subject;
    private Task task;

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }
    
    @BeforeEach
    public void setUp() {
        subject = new Subject("POO", "Programacion Orientada a Objetos", 4);
        task = new Task("TSK-01", "Taller 1", LocalDate.now().plusDays(5), 3, subject);
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

 
    @Test
    public void test1_InitialStatusIsPending() {
        assertEquals(TaskStatus.PENDING, task.getStatus());
    }

    @Test
    public void test2_GetTaskAttributes() {
        assertEquals("TSK-01", task.getId());
        assertEquals("Taller 1", task.getTitle());
    }

    @Test
    public void test3_UpdateStatusToCompleted() {
        task.setStatus(TaskStatus.COMPLETED);
        assertEquals(TaskStatus.COMPLETED, task.getStatus());
    }

    @Test
    public void test4_CalculatePriorityOverdue() {
        Task overdueTask = new Task("T-OLD", "Vieja", LocalDate.now().minusDays(2), 2, subject);
        assertEquals("OVERDUE (Atrasada)", overdueTask.calculatePriority());
    }

    @Test
    public void test5_CalculatePriorityUrgentToday() {
        Task urgentTask = new Task("T-TODAY", "Hoy", LocalDate.now(), 2, subject);
        assertEquals("URGENT (Urgente)", urgentTask.calculatePriority());
    }

    @Test
    public void test6_CalculatePriorityNormalReturnsLowOnly() {
        Task normalTask = new Task("T-NORM", "Normal", LocalDate.now().plusDays(8), 2, subject);
        assertEquals("LOW", normalTask.calculatePriority());
    }

    @Test
    public void test7_EstimatedHoursExpectedZero() {
        assertEquals(0, task.getEstimatedHours());
    }

    @Test
    public void test8_DueDateExpectedYesterday() {
        assertEquals(LocalDate.now().minusDays(1), task.getDueDate());
    }

    @Test
    public void test9_DefaultTaskStatusShouldBeInProgress() {
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    public void test10_TaskIdPrefixExpectedSYS() {
        assertTrue(task.getId().startsWith("SYS"));
    }

    
    @Test
    public void testGetBreakReminder1_ZeroHours() {
        System.out.println("getBreakReminder");
        Subject subject = new Subject("POO01", "POO", 4);
        Task instance = new Task("TSK-01", "Prueba", LocalDate.now().plusDays(1), 0, subject);
        String expResult = "Tarea corta: no necesitas descanso.";
        String result = instance.getBreakReminder();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBreakReminder2_PastDueDateSameResult() {
        System.out.println("getBreakReminder");
        Subject subject = new Subject("POO01", "POO", 4);
        Task instance = new Task("TSK-01", "Prueba", LocalDate.now().minusDays(5), 4, subject);
        String expResult = "Toma 2 descanso(s) de 10 min durante esta tarea.";
        String result = instance.getBreakReminder();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBreakReminder3_NegativeHours() {
        System.out.println("getBreakReminder");
        Subject subject = new Subject("POO01", "POO", 4);
        Task instance = new Task("TSK-01", "Prueba", LocalDate.now().plusDays(1), -4, subject);
        String expResult = "Tarea corta: no necesitas descanso.";
        String result = instance.getBreakReminder();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBreakReminder4_NotNull() {
        System.out.println("getBreakReminder");
        Subject subject = new Subject("POO01", "POO", 4);
        Task instance = new Task("TSK-01", "Prueba", LocalDate.now().plusDays(1), 4, subject);
        String result = instance.getBreakReminder();
        assertNotNull(result);
    }

    @Test
    public void testGetBreakReminder5_MaxFiveBreaks() {
        System.out.println("getBreakReminder");
        Subject subject = new Subject("POO01", "POO", 4);
        Task instance = new Task("TSK-01", "Prueba", LocalDate.now().plusDays(1), 20, subject);
        String expResult = "Toma 5 descanso(s) de 10 min durante esta tarea.";
        String result = instance.getBreakReminder();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBreakReminder6_CalledTwiceSameResult() {
        System.out.println("getBreakReminder");
        Subject subject = new Subject("POO01", "POO", 4);
        Task instance = new Task("TSK-01", "Prueba", LocalDate.now().plusDays(1), 5, subject);
        String firstCall = instance.getBreakReminder();
        String secondCall = instance.getBreakReminder();
        assertEquals(firstCall, secondCall);
    }

    @Test
    public void testGetBreakReminder7_TwoTasksIndependent() {
        System.out.println("getBreakReminder");
        Subject subject = new Subject("POO01", "POO", 4);
        Task shortTask = new Task("TSK-01", "Corta", LocalDate.now().plusDays(1), 1, subject);
        Task longTask = new Task("TSK-02", "Larga", LocalDate.now().plusDays(1), 8, subject);
        assertEquals("Tarea corta: no necesitas descanso.", shortTask.getBreakReminder());
        assertEquals("Toma 4 descanso(s) de 10 min durante esta tarea.", longTask.getBreakReminder());
    }

    @Test
    public void testGetBreakReminder8_CompletedTask() {
        System.out.println("getBreakReminder");
        Subject subject = new Subject("POO01", "POO", 4);
        Task instance = new Task("TSK-01", "Prueba", LocalDate.now().plusDays(1), 6, subject);
        instance.setStatus(TaskStatus.COMPLETED);
        String expResult = "Tarea completada: no necesitas descanso.";
        String result = instance.getBreakReminder();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBreakReminder9_SubjectDoesNotAffect() {
        System.out.println("getBreakReminder");
        Subject poo = new Subject("POO01", "POO", 4);
        Subject bd = new Subject("BD03", "Bases de Datos", 3);
        Task first = new Task("TSK-01", "Prueba", LocalDate.now().plusDays(1), 4, poo);
        Task second = new Task("TSK-02", "Prueba", LocalDate.now().plusDays(1), 4, bd);
        assertEquals(first.getBreakReminder(), second.getBreakReminder());
    }
    
    @Test
    public void testGetBreakReminder10_LongTaskIsNotShortMessage() {
        System.out.println("getBreakReminder");
        Subject subject = new Subject("POO01", "POO", 4);
        Task instance = new Task("TSK-01", "Prueba", LocalDate.now().plusDays(1), 8, subject);
        String result = instance.getBreakReminder();
        assertNotEquals("Tarea corta: no necesitas descanso.", result);
    }
    
}
