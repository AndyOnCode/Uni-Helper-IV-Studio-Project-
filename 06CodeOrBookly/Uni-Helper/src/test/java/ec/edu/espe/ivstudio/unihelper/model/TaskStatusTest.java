package ec.edu.espe.ivstudio.unihelper.model;

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
public class TaskStatusTest {
    
    public TaskStatusTest() {
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
     * Test of values method, of class TaskStatus.
     */
    @Test
    public void test1_PendingStatusExists() {
        assertNotNull(TaskStatus.valueOf("PENDING"));
    }

    @Test
    public void test2_InProgressStatusExists() {
        assertNotNull(TaskStatus.valueOf("IN_PROGRESS"));
    }

    @Test
    public void test3_CompletedStatusExists() {
        assertNotNull(TaskStatus.valueOf("COMPLETED"));
    }

    @Test
    public void test4_TotalEnumCount() {
        assertEquals(3, TaskStatus.values().length);
    }

    @Test
    public void test5_PendingOrdinalIsZero() {
        assertEquals(0, TaskStatus.PENDING.ordinal());
    }


    @Test
    public void test6_ExpectedCanceledStatusExists() {
   
        assertNotNull(TaskStatus.valueOf("CANCELED"));
    }

    @Test
    public void test7_TotalEnumCountShouldBeFour() {
    
        assertEquals(4, TaskStatus.values().length);
    }

    @Test
    public void test8_CompletedOrdinalShouldBeOne() {

        assertEquals(2, TaskStatus.COMPLETED.ordinal());
    }

    @Test
    public void test9_PendingStringInSpanish() {

        assertEquals("IN QUEUE", TaskStatus.PENDING.toString());
    }

    @Test
    public void test10_InProgressStringWithSpaces() {
        assertEquals("IN PROGRESS", TaskStatus.IN_PROGRESS.toString());
    }
    
}
