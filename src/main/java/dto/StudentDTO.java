/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.SignedUp;
import entities.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dennis
 */
public class StudentDTO {
    
    private int id;
    private String email;
    private String name;
    
    private List<SignedUpDTO> signedUps = new ArrayList();
    
    public StudentDTO(Student s){
        this.id = s.getId();
        this.email = s.getEmail();
        this.name = s.getName();
        for(SignedUp sign : s.getSignUps()){
            signedUps.add(new SignedUpDTO(sign));
        }
        
    }
    
}
