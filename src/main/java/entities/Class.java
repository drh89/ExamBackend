/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Dennis
 */
@Entity
@Table(name = "Class")
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "semester")
    private String semester;
    
    @Column(name = "maxNumberOfStudents")
    private int maxNumberOfStudents;

    @ManyToOne
    private Course course;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "ClassHasTeacher", joinColumns = @JoinColumn(name = "classId"),
            inverseJoinColumns = @JoinColumn(name = "teacherId"))
    private List<Teacher> teachers = new ArrayList();

    @OneToMany(mappedBy = "clas")
    private List<SignedUp> signedUps = new ArrayList();

    public Class() {
    }

    public Class(String semester, int maxNumberOfStudents) {
        this.semester = semester;
        this.maxNumberOfStudents = maxNumberOfStudents;
        course = new Course();
    }
    
    
    public void addTeacher(Teacher t){
        teachers.add(t);
    }
    public void addSignedUps(SignedUp sU){
        signedUps.add(sU);
    }
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public void setMaxNumberOfStudents(int maxNumberOfStudents) {
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<SignedUp> getSignedUps() {
        return signedUps;
    }

    public void setSignedUps(List<SignedUp> signedUps) {
        this.signedUps = signedUps;
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
        if (!(object instanceof Class)) {
            return false;
        }
        Class other = (Class) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Class[ id=" + id + " ]";
    }

}
