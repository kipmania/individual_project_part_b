/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author JOHN
 */
public class Trainer {
    private int trainer_id;
    private String firstName;
    private String lastName;
    private String subject;
   // private ArrayList<Student> student;

    
        public Trainer( String firstName, String lastName, String subject) {
 
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }
    
    public Trainer(int trainer_id, String firstName, String lastName, String subject) {
        this.trainer_id = trainer_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }
    
    
    
   
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
      public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
