package utils;


import entities.Role;
import entities.Student;
import entities.Teacher;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User student1 = new User("Student1", "1234");
    User teacher1 = new User("Teacher1", "admin");
    User both = new User("user_admin", "both1");
    
    Student student = new Student("test@test.dk", "Jens");
    Teacher teacher = new Teacher("Lone Hejre");

    if(teacher1.getUserPass().equals("test")||student1.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    student1.addRole(userRole);
    teacher1.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    student.setUser(student1);
    teacher.setUser(teacher1);
    em.persist(student);
    em.persist(teacher);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(student1);
    em.persist(teacher1);
    em.persist(both);
    em.getTransaction().commit();
    System.out.println("PW: " + student1.getUserPass());
    System.out.println("Testing user with OK password: " + student1.verifyPassword("user1"));
    System.out.println("Testing user with wrong password: " + student1.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }

}
