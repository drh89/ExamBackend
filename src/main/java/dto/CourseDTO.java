/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Classs;
import entities.Course;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dennis
 */
public class CourseDTO {
    
    private int id;
    private String courseName;
    private String description;
    private List<ClassDTO> classes = new ArrayList();
    
    public CourseDTO(){
        
    }
    public CourseDTO(Course c){
        this.id = c.getId();
        this.courseName = c.getCourseName();
        this.description = c.getDescription();
        for(Classs clas : c.getClasses()){
            classes.add(new ClassDTO(clas));
        }
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ClassDTO> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassDTO> classes) {
        this.classes = classes;
    }
    
    
}
