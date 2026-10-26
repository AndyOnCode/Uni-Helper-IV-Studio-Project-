package ec.edu.espe.ivstudio.unihelper.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomas Tapia, IV Studio, @ESPE
 */
public class UserAccount {
    private String username;
    private String passwordHash;

    private List<Task> tasks = new ArrayList<>();
    private List<CalendarEvent> calendarEvents = new ArrayList<>();
    private List<GradeItem> grades = new ArrayList<>();
    private List<StudySession> studySessions = new ArrayList<>();
    private List<ScheduleService.AcademicBlock> scheduleBlocks = new ArrayList<>();
    private List<BudgetCalculation> budgetCalculations = new ArrayList<>();

    public UserAccount() {}

    public UserAccount(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }
    public List<Task> getTasks() { return tasks; }
    public List<CalendarEvent> getCalendarEvents() { return calendarEvents; }
    public List<GradeItem> getGrades() { return grades; }
    public List<StudySession> getStudySessions() { return studySessions; }
    public List<ScheduleService.AcademicBlock> getScheduleBlocks() { return scheduleBlocks; }
    public List<BudgetCalculation> getBudgetCalculations() { return budgetCalculations; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
}
