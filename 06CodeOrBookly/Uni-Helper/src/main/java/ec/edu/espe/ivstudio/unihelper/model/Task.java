package ec.edu.espe.ivstudio.unihelper.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Andy Tiban, IV Studio, @ESPE
 */
public class Task {
    private String id;
    private String title;
    private LocalDate dueDate;
    private int estimatedHours;
    private TaskStatus status;
    private Subject subject;

    public Task(String id, String title, LocalDate dueDate, int estimatedHours, Subject subject) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.estimatedHours = estimatedHours;
        this.status = TaskStatus.PENDING;
        this.subject = subject;
    }
    
    public String calculatePriority(){
        long daysUntilDue=ChronoUnit.DAYS.between(LocalDate.now(),dueDate);
        if(daysUntilDue<0){
            return "OVERDUE";
        }else if(daysUntilDue<=2){
            return "URGENT";
        }else if(daysUntilDue<=4||estimatedHours>=6){
            return "HIGH";
        }else{
        return "LOW";
        }
 

    }
    
    public boolean isOverdue() {
    return dueDate.isBefore(LocalDate.now()) && status != TaskStatus.COMPLETED;
    }
    
    public String getBreakReminder() {
        int breaks = estimatedHours / 2;
        if (breaks == 0) {
            return "Short task: no break needed.";
        }
        return "Take " + breaks + " 10-minute break(s) during this task.";
    }
    

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Subject getSubject() {
        return subject;
    }
    
    public void setStatus(TaskStatus status){
        this.status=status;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Subject: %s | Due: %s | Status: %s | Priority: %s",
            id, title, subject.getName(), dueDate, status, calculatePriority());
        }
    
    
   
}
