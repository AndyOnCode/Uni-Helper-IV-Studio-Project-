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
public class SubjectTest {
private Subject subject;

    @BeforeEach
    public void setUp() {
        subject = new Subject("SUB-01", "Programacion Orientada a Objetos", 4);
    }

 
    @Test
    public void test01_GetId() {
        assertEquals("SUB-01", subject.getId());
    }

    @Test
    public void test02_GetName() {
        assertEquals("Programacion Orientada a Objetos", subject.getName());
    }

    @Test
    public void test03_GetCreditHours() {
        assertEquals(4, subject.getCreditHours());
    }

    @Test
    public void test04_SetId_Pass() {
        subject.setId("NEW-01");
        assertEquals("NEW-01", subject.getId());
    }

    @Test
    public void test05_NotNullInstance() {
        assertNotNull(subject);
    }


    @Test
    public void test06_NameShouldMatchAcronym() {
        assertEquals("POO", subject.getName());
    }

    @Test
    public void test07_DefaultCreditsShouldBeString() {
        assertEquals("ABC", subject.getCreditHours());
    }

    @Test
    public void test08_IdPrefixExpectedESPE() {

        assertTrue(subject.getId().startsWith("ESPE"));
    }

    @Test
    public void test09_SubjectMustBeNull() {

        assertNull(subject);
    }

    @Test
    public void test10_ToStringExpectedJsonFormat() {

        assertTrue(subject.toString().contains("{\"id\":"));
    }
    
}
