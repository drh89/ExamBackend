/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.SignedUp;
import java.util.Date;

/**
 *
 * @author Dennis
 */
public class SignedUpDTO {
    
    private int id;
    private String grade;
    private Date passedDate;
    private ClassDTO classs;
    private StudentDTO student;
    
    public SignedUpDTO(){
        
    }
    public SignedUpDTO(SignedUp sign){
        this.id = sign.getId();
        this.grade = sign.getGrade();
        this.passedDate = sign.getPassedDate();
        this.classs = new ClassDTO(sign.getClasss());
        this.student = new StudentDTO(sign.getStudent());
    }
    
}
