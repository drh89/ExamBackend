/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Classs;
import entities.Teacher;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dennis
 */
public class TeacherDTO {
    
    private int id;
    private String name;
    
    private List<ClassDTO> classes = new ArrayList();
    
    public TeacherDTO(){
        
    }
    public TeacherDTO(Teacher t){
        this.id = t.getId();
        this.name = t.getName();
        for(Classs c : t.getClasses()){
            classes.add(new ClassDTO(c));
        }
        
    }
    
}
