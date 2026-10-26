package ec.edu.espe.ivstudio.unihelper.controller;

import ec.edu.espe.ivstudio.unihelper.model.StudySession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo Simba, @ESPE
 */
public class StudySessionManager {
    private List<StudySession> completedSessions;
    
    public StudySessionManager(){
        this(new ArrayList<>());
    }

    public StudySessionManager(List<StudySession> completedSessions){
        this.completedSessions = completedSessions;
    }
    
    public void startSession(StudySession session){
        System.out.println("\n--- STARTING STUDY SESSION ---");
        System.out.println("Topic: " + session.getTopic());
        System.out.println("Duration: " + session.getDurationMinutes() + "minutes");   
        System.out.println("Focus! Timer running...");
        
        try{
            for (int i = session.getDurationMinutes(); i > 0; i--){
                System.out.println("Time remaining: " + i + "minute(s)..");
                Thread.sleep(1000);
            }
            completedSessions.add(session);
            System.out.println("Great job! Study session completed.\n");
        }catch (InterruptedException e){
            System.out.println("Session interrupted.");
            Thread.currentThread().interrupt();
            
        }  
    }
    public List<StudySession> getCompletedSessions(){
        return completedSessions;
    }
    public int getTotalStudyTimeMinutes(){
        int total = 0;
        for(StudySession session : completedSessions){
            total += session.getDurationMinutes();
        }
        return total;
    }    
}
