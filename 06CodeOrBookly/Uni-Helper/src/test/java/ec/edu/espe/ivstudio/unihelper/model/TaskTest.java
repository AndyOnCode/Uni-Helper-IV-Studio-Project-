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
    
    @BeforeEach
    public void setUp() {
        subject = new Subject("POO", "Programacion Orientada a Objetos", 4);
        task = new Task("TSK-01", "Taller 1", LocalDate.now().plusDays(5), 3, subject);
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
}
