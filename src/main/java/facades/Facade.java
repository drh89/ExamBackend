/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.SchoolClassDTO;
import dto.CourseDTO;
import dto.SignedUpDTO;
import dto.StudentDTO;
import dto.TeacherDTO;
import entities.SchoolClass;
import entities.Course;
import entities.SignedUp;
import entities.Student;
import entities.Teacher;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author Dennis
 */
public class Facade {

    private static Facade instance;
    private static EntityManagerFactory emf;

    private Facade() {

    }

    public Facade(EntityManagerFactory _emf) {
        this.emf = _emf;
    }

    public static Facade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Facade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<SchoolClassDTO> getAllClasses() {
        EntityManager em = getEntityManager();
        List<SchoolClassDTO> result = new ArrayList();
        TypedQuery q = (TypedQuery) em.createQuery("SELECT c FROM SchoolClass c", SchoolClass.class);
        List<SchoolClass> classes = q.getResultList();

        for (SchoolClass c : classes) {
            result.add(new SchoolClassDTO(c));

        }

        return result;
    }

    public TeacherDTO getTeacherByUsername(String username) {
        EntityManager em = getEntityManager();

        TypedQuery q = (TypedQuery) em.createQuery("SELECT t FROM Teacher t WHERE t.user.userName = :name", Teacher.class);
        q.setParameter("name", username);
        TeacherDTO dto = new TeacherDTO((Teacher) q.getSingleResult());
        return dto;
    }

    public StudentDTO getStudentByUsername(String username) {
        EntityManager em = getEntityManager();

        TypedQuery q = (TypedQuery) em.createQuery("SELECT s FROM Student s WHERE s.user.userName = :name", Student.class);
        q.setParameter("name", username);
        StudentDTO dto = new StudentDTO((Student) q.getSingleResult());
        return dto;
    }

    public List<SignedUpDTO> getAllSignedUpByStudentId(int studentId) {
        EntityManager em = getEntityManager();
        List<SignedUpDTO> result = new ArrayList();
        TypedQuery q = (TypedQuery) em.createQuery("SELECT sign FROM SignedUp sign WHERE sign.student.id = :id", SignedUp.class);
        q.setParameter("id", studentId);
        List<SignedUp> signs = q.getResultList();
        for (SignedUp sign : signs) {
            result.add(new SignedUpDTO(sign));
        }

        return result;
    }
    
    public CourseDTO newCourse(String courseName, String description, String semester, int maxStudents){
        EntityManager em = getEntityManager();
        Course c = new Course(courseName, description);
        em.getTransaction().begin();
        em.persist(c);
//        SchoolClass sc = new SchoolClass(semester, maxStudents);
//        c.addClass(sc);
//        sc.setCourse(c);
        CourseDTO dto = new CourseDTO(c);
        
        em.close();
        return dto;
    }

    private Course courseCheck(CourseDTO course) {
        EntityManager em = getEntityManager();

        TypedQuery q = (TypedQuery) em.createQuery("SELECT c FROM Course c WHERE c.courseName = :name", Course.class);
        q.setParameter("name", course.getCourseName());

        try {
            Course c = (Course) q.getSingleResult();
            for(SchoolClassDTO dto : course.getClasses()){
                c.getClasses().add(new SchoolClass(dto.getSemester(),dto.getMaxNumberOfStudents()));
            }
            return c;

        } catch (NoResultException ex) {
            Course newC = new Course(course.getCourseName(), course.getDescription());
            for(SchoolClassDTO sc : course.getClasses()){
                newC.addClass(new SchoolClass(sc.getSemester(),sc.getMaxNumberOfStudents()));
            }
            return newC;
        }

    }

}
