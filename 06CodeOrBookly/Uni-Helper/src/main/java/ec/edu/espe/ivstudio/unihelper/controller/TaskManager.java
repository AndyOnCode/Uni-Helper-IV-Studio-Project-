package ec.edu.espe.ivstudio.unihelper.controller;

import ec.edu.espe.ivstudio.unihelper.model.Subject;
import ec.edu.espe.ivstudio.unihelper.model.Task;
import ec.edu.espe.ivstudio.unihelper.model.TaskStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andy Tiban, IV Studio, @ESPE
 */
public class TaskManager {
    private List<Subject> subjects;
    private List<Task> tasks;

    public TaskManager() {
        this.subjects = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }
    
    public void addSubject(String id,String name, int credits){
        subjects.add(new Subject(id,name,credits));
        
    }
    
    public Subject findSubjectById(String id){
        for(Subject s: subjects){
            if(s.getId().equalsIgnoreCase(id))
            return s;
        }
        return null;
    }
    
    
    public void createTask(String id, String title,LocalDate dueDate,int hours,String subjectId){
    Subject subject=findSubjectById(subjectId);
        if(subject!=null){
            tasks.add(new Task(id,title,dueDate,hours,subject)); 
        }
        else{
            System.out.println("Error: Subject with ID: "+ subjectId +" doesn't exist.");
        }
    }
    
    public void updateStatus(String taskId,TaskStatus newStatus){
        for(Task t:tasks){
            if(t.getId().equalsIgnoreCase(taskId)){
                t.setStatus(newStatus);
                return;
            }
        }
        System.out.println("Error, task not found.");
    
    }
    
    public List<Task> filterBySubject(String subjectId) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getSubject().getId().equalsIgnoreCase(subjectId)) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Task> getOverdueTasks() {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isOverdue()) {
                result.add(t);
            }
        }
        return result;
    }
    
    public List<Task> getTasks(){
        return tasks;
    }
    
    public List<Subject> getSubjects(){
        return subjects;
    }

    
}
