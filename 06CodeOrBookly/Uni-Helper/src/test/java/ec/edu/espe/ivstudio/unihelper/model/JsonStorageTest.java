package ec.edu.espe.ivstudio.unihelper.model;

import java.nio.file.Path;
import java.time.DayOfWeek;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

class JsonStorageTest {

    @TempDir
    Path tempDir;

    @Test
    void savesAndLoadsAllUserData() {
        UserAccount user = new UserAccount("student", "hash");
        user.getCalendarEvents().add(new CalendarEvent("Exam", "2026-11-01", "09:00", "Final"));
        user.getGrades().add(new GradeItem("Math", 95, 20));
        user.getStudySessions().add(new StudySession("Review", 45));
        user.getScheduleBlocks().add(new ScheduleService.AcademicBlock(
                DayOfWeek.MONDAY,
                new RoomIdentifier("A", "101"),
                "Math",
                List.of("exam")));
        user.getBudgetCalculations().add(new BudgetCalculation(
                SpendingTier.SAVE, true, false, List.of("exam"), 1850));

        String filename = tempDir.resolve("users.json").toString();
        assertTrue(JsonStorage.saveListToFile(filename, List.of(user)));

        List<UserAccount> loaded = JsonStorage.loadListFromFile(filename, UserAccount.class);

        assertEquals(1, loaded.size());
        UserAccount loadedUser = loaded.getFirst();
        assertEquals("student", loadedUser.getUsername());
        assertEquals("Exam", loadedUser.getCalendarEvents().getFirst().getTitle());
        assertEquals(95, loadedUser.getGrades().getFirst().getScore());
        assertEquals("Review", loadedUser.getStudySessions().getFirst().getTopic());
        assertEquals("Math", loadedUser.getScheduleBlocks().getFirst().subjectName());
        assertEquals(SpendingTier.SAVE, loadedUser.getBudgetCalculations().getFirst().getTier());
        assertEquals(1850, loadedUser.getBudgetCalculations().getFirst().getCalculatedBudgetCents());
    }
}
