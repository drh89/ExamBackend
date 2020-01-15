/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Classs;
import entities.SignedUp;
import entities.Teacher;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dennis
 */
public class ClassDTO {
    
    private int id;
    private String semester;
    private int maxNumberOfStudents;
    private CourseDTO course;
    
    private List<TeacherDTO> teachers = new ArrayList();
    private List<SignedUpDTO> signedUps = new ArrayList();
    
    public ClassDTO(){
        
    }
    
    public ClassDTO(Classs c){
        this.id = c.getId();
        this.semester = c.getSemester();
        this.maxNumberOfStudents = c.getMaxNumberOfStudents();
        this.course = new CourseDTO(c.getCourse());
        for (Teacher teacher : c.getTeachers()) {
            teachers.add(new TeacherDTO(teacher));
        }
        for(SignedUp sign : c.getSignedUps()){
            signedUps.add(new SignedUpDTO(sign));
        }
    }
    
}
