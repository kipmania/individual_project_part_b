/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package individual_project_part_b;

/**
 *
 * @author kopan
 */
public class Individual_project_part_B {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Methods.getConnection();
         SelectMethods.selectStudents();
        SelectMethods.selectTrainers();
        SelectMethods.selectAssigments();
        SelectMethods.selectCourses();

        SelectMethods.selectStudentsPerCourse();
        SelectMethods.selectTrainersPerCourse();
        SelectMethods.selectAssigmentsPerCourse();
        SelectMethods.selectAssigmentsPerCoursePerStudent();
        SelectMethods.selectMultipleStudentsPerCourse();
        
        Course course1 = new Course("CB16  ", "Java", "Part Time", "2019-01-01 00:00:00", "2019-03-03 00:00:00");

        Student student2 = new Student("stelios", "tralala", "1989-07-02 00:00:00", 1000.00);
        Trainer trainer1 = new Trainer("Michalis", "Ignatiou", "Algorithms");
        Assigment individualProject1 = new Assigment("First Individual Assigment-Private school", "2020-02-12 00:00:00",5);
        InsertMethods.insert(course1);
        InsertMethods.insert(student2);
        InsertMethods.insert(trainer1);
        InsertMethods.insert(individualProject1);

        InsertMethods.insert(student2, course1);
        InsertMethods.insert(trainer1, course1);
        InsertMethods.insert(individualProject1,student2, course1);
        
 
    }

}
