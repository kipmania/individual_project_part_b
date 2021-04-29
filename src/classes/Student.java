/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author JOHN
 */
public class Student {

    private int student_id;
    private String firstName;
    private String lastName;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
   
    private String dateOfBirth;
    private LocalDate localDateOfBirth;
    private LocalDateTime dateTimeOfBirth;
    private double tuitionFees;
    private ArrayList<Assigment> assigment = new ArrayList<Assigment>();
    //private int year;private int month;private int day;

    public Student(int student_id, String firstName, String lastName, LocalDateTime dateTimeOfBirth, double tuitionFees) {
        this.student_id = student_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tuitionFees = tuitionFees;
        this.dateTimeOfBirth = dateTimeOfBirth;
    }

    public Student(String firstName, String lastName, String inputDate, double tuitionFees) throws DateTimeParseException{

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = inputDate;

        this.tuitionFees = tuitionFees;
  
            localDateOfBirth = LocalDate.parse(inputDate, format2);
  
        dateTimeOfBirth= localDateOfBirth.atTime(0, 0, 0);
          System.out.println("parsed "+ dateTimeOfBirth.toString());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.lastName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return true;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public DateTimeFormatter getFormat() {
        return format;
    }

    public void setFormat(DateTimeFormatter format) {
        this.format = format;
    }

    public LocalDateTime getDateTimeOfBirth() {
        return dateTimeOfBirth;
    }

    public void setDateTimeOfBirth(LocalDateTime dateTimeOfBirth) {
        this.dateTimeOfBirth = dateTimeOfBirth;
    }

    public void addAssigment(Assigment assigment) {
        this.assigment.add(assigment);
    }

    public ArrayList<Assigment> getAssigment() {
        return assigment;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String inputDate) throws DateTimeParseException {
        this.dateTimeOfBirth = dateTimeOfBirth;
    }

    public double getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(double tuitionFees) throws NumberFormatException {
        this.tuitionFees = tuitionFees;
    }

}
