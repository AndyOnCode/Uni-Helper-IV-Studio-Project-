/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espe.ivstudio.unihelper.controller;

import ec.edu.espe.ivstudio.unihelper.model.Subject;
import ec.edu.espe.ivstudio.unihelper.model.Task;
import ec.edu.espe.ivstudio.unihelper.model.TaskStatus;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Nohemi Tocagon, IV Studio, @ESPE
 */
public class TaskManagerTest {
    
    public TaskManagerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    
    /**
     * Test of findSubjectById method, of class TaskManager.
     */
    @Test
    public void testFindSubjectById() {
        System.out.println("findSubjectById");
        String id = "";
        TaskManager instance = new TaskManager();
        Subject expResult = null;
        Subject result = instance.findSubjectById(id);
        assertEquals(expResult, result);
    }
    
    @Test
    public void test1FilterBySubject_EmptyManager() {
        System.out.println("filterBySubject");
        TaskManager instance = new TaskManager();
        int expResult = 0;
        int result = instance.filterBySubject("POO01").size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void test2FilterBySubject_SubjectWithoutTasks() {
        System.out.println("filterBySubject");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        int expResult = 0;
        int result = instance.filterBySubject("POO01").size();
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void test3FilterBySubject() {
        System.out.println("filterBySubject");
        String subjectId = "";
        TaskManager instance = new TaskManager();
        List<Task> expResult = null;
        List<Task> result = instance.filterBySubject(subjectId);
        assertEquals(expResult, result);
    }

    @Test
    public void test4FilterBySubject_OneTask() {
        System.out.println("filterBySubject");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().plusDays(1), 2, "POO01");
        int expResult = 1;
        int result = instance.filterBySubject("POO01").size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void test5FilterBySubject_TwoTasksSameSubject() {
        System.out.println("filterBySubject");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().plusDays(1), 2, "POO01");
        instance.createTask("TSK-02", "B", LocalDate.now().plusDays(2), 3, "POO01");
        int expResult = 2;
        int result = instance.filterBySubject("POO01").size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void test6FilterBySubject_MixedSubjectsFirst() {
        System.out.println("filterBySubject");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.addSubject("ED02", "Estructuras", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().plusDays(1), 2, "POO01");
        instance.createTask("TSK-02", "B", LocalDate.now().plusDays(2), 3, "POO01");
        instance.createTask("TSK-03", "C", LocalDate.now().plusDays(7), 2, "ED02");
        int expResult = 2;
        int result = instance.filterBySubject("POO01").size();
        assertEquals(expResult, result);
    }

    @Test
    public void test7FilterBySubject_MixedSubjectsSecond() {
        System.out.println("filterBySubject");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.addSubject("ED02", "Estructuras", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().plusDays(1), 2, "POO01");
        instance.createTask("TSK-02", "B", LocalDate.now().plusDays(2), 3, "POO01");
        instance.createTask("TSK-03", "C", LocalDate.now().plusDays(7), 2, "ED02");
        int expResult = 1;
        int result = instance.filterBySubject("ED02").size();
        assertEquals(expResult, result);
    }

    @Test
    public void test8FilterBySubject_IgnoreCase() {
        System.out.println("filterBySubject");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().plusDays(1), 2, "POO01");
        int expResult = 1;
        int result = instance.filterBySubject("poo01").size();
        assertEquals(expResult, result);
    }

    @Test
    public void test9FilterBySubject_UnknownSubject() {
        System.out.println("filterBySubject");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().plusDays(1), 2, "POO01");
        int expResult = 0;
        int result = instance.filterBySubject("XYZ99").size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void test10FilterBySubject_IdWithSpaces() {
        System.out.println("filterBySubject");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().plusDays(1), 2, "POO01");
        int expResult = 1;
        int result = instance.filterBySubject(" POO01 ").size();
        assertEquals(expResult, result);
    }
    /**
     * Test of getOverdueTasks method, of class TaskManager.
     */
    @Test
    public void testGetOverdueTasks() {
        System.out.println("getOverdueTasks");
        TaskManager instance = new TaskManager();
        List<Task> expResult = null;
        List<Task> result = instance.getOverdueTasks();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetOverdueTasks1_EmptyManager() {
        System.out.println("getOverdueTasks");
        TaskManager instance = new TaskManager();
        int expResult = 0;
        int result = instance.getOverdueTasks().size();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetOverdueTasks2_OnePastPending() {
        System.out.println("getOverdueTasks");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().minusDays(2), 2, "POO01");
        int expResult = 1;
        int result = instance.getOverdueTasks().size();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetOverdueTasks3_FutureTask() {
        System.out.println("getOverdueTasks");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().plusDays(3), 2, "POO01");
        int expResult = 0;
        int result = instance.getOverdueTasks().size();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetOverdueTasks4_DueToday() {
        System.out.println("getOverdueTasks");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.createTask("TSK-01", "A", LocalDate.now(), 2, "POO01");
        int expResult = 0;
        int result = instance.getOverdueTasks().size();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetOverdueTasks5_PastCompleted() {
        System.out.println("getOverdueTasks");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().minusDays(2), 2, "POO01");
        instance.updateStatus("TSK-01", TaskStatus.COMPLETED);
        int expResult = 0;
        int result = instance.getOverdueTasks().size();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetOverdueTasks6_PastInProgress() {
        System.out.println("getOverdueTasks");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().minusDays(2), 2, "POO01");
        instance.updateStatus("TSK-01", TaskStatus.IN_PROGRESS);
        int expResult = 1;
        int result = instance.getOverdueTasks().size();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetOverdueTasks7_ThreePastPending() {
        System.out.println("getOverdueTasks");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().minusDays(1), 2, "POO01");
        instance.createTask("TSK-02", "B", LocalDate.now().minusDays(5), 2, "POO01");
        instance.createTask("TSK-03", "C", LocalDate.now().minusDays(30), 2, "POO01");
        int expResult = 3;
        int result = instance.getOverdueTasks().size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetOverdueTasks8_CompletedPriorityNotOverdue() {
        System.out.println("getOverdueTasks");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().minusDays(2), 2, "POO01");
        instance.updateStatus("TSK-01", TaskStatus.COMPLETED);
        String result = instance.getTasks().get(0).calculatePriority();
        assertNotEquals("OVERDUE", result);
    }

    @Test
    public void testGetOverdueTasks9_ReopenedTask() {
        System.out.println("getOverdueTasks");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().minusDays(2), 2, "POO01");
        instance.updateStatus("TSK-01", TaskStatus.COMPLETED);
        instance.updateStatus("TSK-01", TaskStatus.PENDING);
        int expResult = 1;
        int result = instance.getOverdueTasks().size();
        assertEquals(expResult, result);
    }
    
     @Test
    public void testGetOverdueTasks10_DuplicateIdsOnlyFirstUpdated() {
        System.out.println("getOverdueTasks");
        TaskManager instance = new TaskManager();
        instance.addSubject("POO01", "POO", 4);
        instance.createTask("TSK-01", "A", LocalDate.now().minusDays(2), 2, "POO01");
        instance.createTask("TSK-01", "B", LocalDate.now().minusDays(3), 2, "POO01");
        instance.updateStatus("TSK-01", TaskStatus.COMPLETED);
        int expResult = 0;
        int result = instance.getOverdueTasks().size();
        assertEquals(expResult, result);
    }
    
}
