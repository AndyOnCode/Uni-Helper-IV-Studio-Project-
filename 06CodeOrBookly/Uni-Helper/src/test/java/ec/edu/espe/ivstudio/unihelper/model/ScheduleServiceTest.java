package ec.edu.espe.ivstudio.unihelper.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class ScheduleServiceTest {

    private ScheduleService service;

    @BeforeEach
    void setUp() {
        service = new ScheduleService();
    }

    @Test
    @DisplayName("[Low] Adds a block for today")
    void addsBlockForToday() {
        LocalDate today = LocalDate.of(200, 10, 25);
        ScheduleService.AcademicBlock block = block(today.getDayOfWeek(), "A", "101", "Programming");

        service.addOrUpdateBlock(block, today);

        assertEquals(List.of(block), service.getBlocksForDay(today.getDayOfWeek()));
    }

    @Test
    @DisplayName("[Medium] Rejects a block for a past day")
    void rejectsBlockForPastDay() {
        LocalDate wednesday = LocalDate.of(2026, 10, 28);
        ScheduleService.AcademicBlock mondayBlock = block(DayOfWeek.MONDAY, "A", "101", "Programming");

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.addOrUpdateBlock(mondayBlock, wednesday)
        );

        assertTrue(exception.getMessage().contains("Cannot edit or add schedules for past days"));
        assertTrue(service.getBlocksForDay(DayOfWeek.MONDAY).isEmpty());
    }

    @Test
    @DisplayName("[Medium] Replaces an existing block for the same day and room")
    void replacesExistingBlockForSameDayAndRoom() {
        LocalDate monday = LocalDate.of(2026, 10, 26);
        ScheduleService.AcademicBlock original = block(DayOfWeek.MONDAY, "A", "101", "Programming");
        ScheduleService.AcademicBlock replacement = block(DayOfWeek.MONDAY, "A", "101", "Databases");

        service.addOrUpdateBlock(original, monday);
        service.addOrUpdateBlock(replacement, monday);

        assertEquals(List.of(replacement), service.getBlocksForDay(DayOfWeek.MONDAY));
    }

    @Test
    @DisplayName("[Low] Keeps blocks in different rooms on the same day")
    void keepsBlocksInDifferentRoomsOnSameDay() {
        LocalDate monday = LocalDate.of(2026, 10, 26);
        ScheduleService.AcademicBlock first = block(DayOfWeek.MONDAY, "A", "101", "Programming");
        ScheduleService.AcademicBlock second = block(DayOfWeek.MONDAY, "B", "202", "Databases");

        service.addOrUpdateBlock(first, monday);
        service.addOrUpdateBlock(second, monday);

        assertEquals(List.of(first, second), service.getBlocksForDay(DayOfWeek.MONDAY));
    }

    @Test
    @DisplayName("[Low] Returns only blocks for the requested day")
    void returnsOnlyBlocksForRequestedDay() {
        LocalDate monday = LocalDate.of(2026, 10, 26);
        ScheduleService.AcademicBlock mondayBlock = block(DayOfWeek.MONDAY, "A", "101", "Programming");
        ScheduleService.AcademicBlock tuesdayBlock = block(DayOfWeek.TUESDAY, "A", "101", "Databases");

        service.addOrUpdateBlock(mondayBlock, monday);
        service.addOrUpdateBlock(tuesdayBlock, monday);

        assertEquals(List.of(tuesdayBlock), service.getBlocksForDay(DayOfWeek.TUESDAY));
    }

    @Test
    @DisplayName("[Low] Returns an empty list when no blocks are scheduled")
    void returnsEmptyListWhenNoBlocksExist() {
        assertTrue(service.getBlocksForDay(DayOfWeek.MONDAY).isEmpty());
    }

    @Test
    @DisplayName("[Low] Allows adding a block for a future day")
    void allowsBlockForFutureDay() {
        LocalDate monday = LocalDate.of(2026, 10, 26);
        ScheduleService.AcademicBlock fridayBlock = block(DayOfWeek.FRIDAY, "D", "405", "Physics");

        service.addOrUpdateBlock(fridayBlock, monday);

        assertEquals(List.of(fridayBlock), service.getBlocksForDay(DayOfWeek.FRIDAY));
    }

    @Test
    @DisplayName("[Medium] Replacing a room block does not remove a different room")
    void replacingBlockKeepsOtherRoomOnSameDay() {
        LocalDate monday = LocalDate.of(2026, 10, 26);
        ScheduleService.AcademicBlock original = block(DayOfWeek.MONDAY, "A", "101", "Programming");
        ScheduleService.AcademicBlock otherRoom = block(DayOfWeek.MONDAY, "B", "202", "Databases");
        ScheduleService.AcademicBlock replacement = block(DayOfWeek.MONDAY, "A", "101", "Algorithms");

        service.addOrUpdateBlock(original, monday);
        service.addOrUpdateBlock(otherRoom, monday);
        service.addOrUpdateBlock(replacement, monday);

        assertEquals(List.of(otherRoom, replacement), service.getBlocksForDay(DayOfWeek.MONDAY));
    }

    @Test
    @DisplayName("[Medium] Updating a block does not replace the same room on another day")
    void updatingBlockDoesNotReplaceRoomOnAnotherDay() {
        LocalDate monday = LocalDate.of(2026, 10, 26);
        ScheduleService.AcademicBlock mondayBlock = block(DayOfWeek.MONDAY, "A", "101", "Programming");
        ScheduleService.AcademicBlock tuesdayBlock = block(DayOfWeek.TUESDAY, "A", "101", "Databases");

        service.addOrUpdateBlock(mondayBlock, monday);
        service.addOrUpdateBlock(tuesdayBlock, monday);

        assertEquals(List.of(mondayBlock), service.getBlocksForDay(DayOfWeek.MONDAY));
        assertEquals(List.of(tuesdayBlock), service.getBlocksForDay(DayOfWeek.TUESDAY));
    }

    @Test
    @DisplayName("[Hard] Exposes crash when the schedule block is null")
    void throwsWhenBlockIsNull() {
        assertThrows(
                NullPointerException.class,
                () -> service.addOrUpdateBlock(null, LocalDate.of(2026, 10, 26))
        );
    }

    @Test
    @DisplayName("[Hard] Exposes crash when the current date is null")
    void throwsWhenCurrentDateIsNull() {
        ScheduleService.AcademicBlock block = block(DayOfWeek.MONDAY, "A", "101", "Programming");

        assertThrows(NullPointerException.class, () -> service.addOrUpdateBlock(block, null));
    }

    @Test
    @DisplayName("[Hard] Exposes crash when the block day is null")
    void throwsWhenBlockDayIsNull() {
        ScheduleService.AcademicBlock block = block(null, "A", "101", "Programming");

        assertThrows(
                NullPointerException.class,
                () -> service.addOrUpdateBlock(block, LocalDate.of(2026, 10, 26))
        );
    }

    private ScheduleService.AcademicBlock block(DayOfWeek day, String roomBlock, String roomNumber, String subject) {
        return new ScheduleService.AcademicBlock(
                day,
                new RoomIdentifier(roomBlock, roomNumber),
                subject,
                List.of()
        );
    }
}
