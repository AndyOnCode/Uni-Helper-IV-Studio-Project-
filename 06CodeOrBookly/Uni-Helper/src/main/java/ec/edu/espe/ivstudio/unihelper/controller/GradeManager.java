package ec.edu.espe.ivstudio.unihelper.controller;

import ec.edu.espe.ivstudio.unihelper.model.GradeItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo Simba, @ESPE
 */
public class GradeManager {
    private List<GradeItem> grades;

    public GradeManager() {
        this.grades = new ArrayList<>();
    }
    
    public void addGrade(GradeItem grade){
        grades.add(grade);
        System.out.println("Event added successfully");  
    }
    
    public List<GradeItem> getGrades(){
        return grades;
    }
    
    public double calculationWeightedAverage(){
        if(grades.isEmpty()){
            return 0;
        }
        double totalWeightedScore = 0;
        double totalWeight = 0;
        
        for(GradeItem g: grades){
            totalWeightedScore += g.getScore() * (g.getWeight()/100.0);
            totalWeight += (g.getWeight()/100.0);
        }
        if(totalWeight == 0){
            return 0;
        }
        return totalWeightedScore / totalWeight;
    }
 
}