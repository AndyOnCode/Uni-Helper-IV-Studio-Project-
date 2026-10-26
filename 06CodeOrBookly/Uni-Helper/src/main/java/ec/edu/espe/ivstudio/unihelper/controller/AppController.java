package ec.edu.espe.ivstudio.unihelper.controller;
import ec.edu.espe.ivstudio.unihelper.model.*;
/**
 *
 * @author Tomas Tapia, IV Studio, @ESPE
 */
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class AppController {
    private final AuthService authService;
    private final BudgetService budgetService;
    private final ScheduleService scheduleService;
    private final String USERS_FILE = "users.json";

    public AppController() {
        List<UserAccount> users = JsonStorage.loadListFromFile(USERS_FILE, UserAccount.class);
        this.authService = new AuthService(users);
        this.budgetService = new BudgetService();
        this.scheduleService = new ScheduleService();
    }

    public boolean handleRegister(String username, String rawPassword) {
        boolean success = authService.register(username, rawPassword);
        if (success) {
            JsonStorage.saveListToFile(USERS_FILE, authService.getUsers());
        }
        return success;
    }

    public boolean handleLogin(String username, String rawPassword) {
        return authService.authenticate(username, rawPassword);
    }

    public long handleBudgetCalculation(
            SpendingTier tier,
            boolean eatAtCampus,
            boolean usesPersonalCar,
            List<String> keywords
    ) {
        return budgetService.calculateDailyBudget(tier, eatAtCampus, usesPersonalCar, keywords);
    }

    public void handleAddScheduleBlock(
            DayOfWeek day,
            String blockCode,
            String roomNum,
            String subject,
            List<String> keywords
    ) {
        RoomIdentifier room = new RoomIdentifier(blockCode, roomNum);
        ScheduleService.AcademicBlock block = new ScheduleService.AcademicBlock(day, room, subject, keywords);
        scheduleService.addOrUpdateBlock(block, LocalDate.now());
    }


    public List<ScheduleService.AcademicBlock> handleGetSchedule(DayOfWeek day) {
        return scheduleService.getBlocksForDay(day);
    }
}
