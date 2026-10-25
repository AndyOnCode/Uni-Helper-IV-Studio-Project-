package ec.edu.espe.ivstudio.unihelper.controller;

import ec.edu.espe.ivstudio.unihelper.model.CalendarEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo Simba, @ESPE
 */
public class CalendarManager {
    private List<CalendarEvent> events;
    
    public CalendarManager() {
        this.events = new ArrayList<>();
    }
    
    public void addEvent(CalendarEvent event){
        events.add(event);
        System.out.println("Event added successfully");
    }
    
    public List<CalendarEvent> getEvents(){
        return events;
    }  
}

