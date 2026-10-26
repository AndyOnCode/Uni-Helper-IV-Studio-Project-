package ec.edu.espe.ivstudio.unihelper.model;
/**
 *
 * @author Tomas Tapia, IV Studio, @ESPE
 */
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduleService {
    public record AcademicBlock(
            DayOfWeek dayOfWeek,
            RoomIdentifier room,
            String subjectName,
            List<String> keywords
    ) {}

    private final List<AcademicBlock> currentWeekBlocks;

    public ScheduleService() {
        this(new ArrayList<>());
    }

    public ScheduleService(List<AcademicBlock> currentWeekBlocks) {
        this.currentWeekBlocks = currentWeekBlocks;
    }

    public void addOrUpdateBlock(AcademicBlock block, LocalDate currentDate) {
        DayOfWeek today = currentDate.getDayOfWeek();

        // Business rule: Can only edit today or future days in the Monday-Sunday week
        if (block.dayOfWeek().getValue() < today.getValue()) {
            throw new IllegalStateException("Restricted: Cannot edit or add schedules for past days (" + block.dayOfWeek() + ").");
        }

        currentWeekBlocks.removeIf(b -> b.dayOfWeek() == block.dayOfWeek() && b.room().equals(block.room()));
        currentWeekBlocks.add(block);
    }

    public List<AcademicBlock> getBlocksForDay(DayOfWeek day) {
        return currentWeekBlocks.stream()
                .filter(b -> b.dayOfWeek() == day)
                .toList();
    }
}
