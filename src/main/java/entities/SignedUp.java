/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dennis
 */
@Entity
@Table(name = "SignedUps")
public class SignedUp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name = "grade")
    private String grade;
    
    @Column(name = "passedDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date passedDate;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "classId")
    private Classs clas;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    
    public SignedUp(){
        
    }

    public SignedUp(String grade) {
        this.grade = grade;
        passedDate = null;
        
    }
    
    
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getPassedDate() {
        return passedDate;
    }

    public void setPassedDate(Date passedDate) {
        this.passedDate = passedDate;
    }

    public Classs getClasss() {
        return clas;
    }

    public void setClas(Classs clas) {
        this.clas = clas;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SignedUp)) {
            return false;
        }
        SignedUp other = (SignedUp) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SignedUp[ id=" + id + " ]";
    }

}
