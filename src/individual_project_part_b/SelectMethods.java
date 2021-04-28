/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package individual_project_part_b;

import classes.Assigment;
import classes.Course;
import classes.Student;
import classes.Trainer;
import static individual_project_part_b.Methods.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kopan
 */
public class SelectMethods {

    public static String select_students = "SELECT * FROM STUDENTS;";
    public static String select_trainers = "SELECT distinct(trainers.idtrainer), trainers.first_name, trainers.last_name, subjects.subject_name FROM TRAINERS, subjects\n"
            + "where trainers.idtrainer = subjects.Trainer_id;";
    public static String select_assigments = "SELECT * FROM ASSIGMENTS;";
    public static String select_courses = "SELECT * FROM COURSES;";
    public static String select_students_per_course = "SELECT  COURSES_ID,STUDENTS_ID FROM STUDIES ORDER BY COURSEs_ID ASC;";
    public static String select_trainers_per_course = "SELECT  DISTINCT(COURSE_ID),  TRAINER_ID FROM SUBJECTS ORDER BY COURSE_ID ASC;";
    public static String select_assigments_per_course = "SELECT COURSE_IDCOURSE,IDASSIGMENT  FROM ASSIGMENTS ORDER BY COURSE_IDCOURSE;";
    public static String select_assigments_per_course_per_student = "SELECT STUDIES.students_id,STUDIES.courses_id, ASSIGMENTS.IDASSIGMENT FROM \n"
            + "STUDIES, assigments WHERE STUDIES.COURSES_ID = assigments.course_idcourse ORDER BY STUDIES.STUDENTS_ID;";
    public static String select_students_with_multiple_courses = "SELECT students_id, count(*) AS 'Number of Courses'FROM STUDIES\n"
            + "GROUP BY 1 HAVING COUNT(*)>1;";

    
   
    protected static void selectStudents() {
        ArrayList<Student> list = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(select_students);
            while (rs.next()) {
                int student_id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                double tuitionFees = rs.getDouble(5);

                LocalDateTime date_of_birth = rs.getTimestamp("date_of_birth").toLocalDateTime();

                Student student = new Student(student_id, fname, lname, date_of_birth, tuitionFees);
                list.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(rs, stmt, conn);
        }
        System.out.println("STUDENTS:");
        for (int i = 0; i < list.size(); i++) {        
        System.out.println((i+1) +" "+ list.get(i).getFirstName() +" "+ list.get(i).getLastName() +" "+ list.get(i).getDateTimeOfBirth().toString()
                +"  "+ list.get(i).getTuitionFees());
        }
//return list;
    }

    protected static void selectTrainers() {
        ArrayList<Trainer> list = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(select_trainers);
            while (rs.next()) {
                int trainer_id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String subject = rs.getString(4);
                Trainer trainer = new Trainer(trainer_id, fname, lname, subject);
                list.add(trainer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(rs, stmt, conn);
        }
        System.out.println("TRAINERS: ");
        for (int i = 0; i < list.size(); i++) {        
        System.out.println((i+1) +" "+ list.get(i).getFirstName() +" "+ list.get(i).getLastName() +" "+ list.get(i).getSubject());
        }
        
        //return list;
    }

    protected static void selectAssigments() {
        ArrayList<Assigment> list = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(select_assigments);
            while (rs.next()) {
                int assigment_id = rs.getInt(1);
                String description = rs.getString(2);
                LocalDateTime submission_date = rs.getTimestamp("submission_date").toLocalDateTime();
                int course_id = rs.getInt(4);
                Assigment assigment = new Assigment(assigment_id, description, submission_date, course_id);
                list.add(assigment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(rs, stmt, conn);
        }
        System.out.println("ASSIGMENTS:");
        for (int i = 0; i < list.size(); i++) {        
        System.out.println((i+1) + " "+ list.get(i).getDescription() +" "+ list.get(i).getSubDateTime().toString() +" "+ list.get(i).getCourse_id());
        }
        
        //return list;
    }

    protected static void selectCourses() {
        ArrayList<Course> list = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(select_courses);
            while (rs.next()) {
                int course_id = rs.getInt(1);
                String title = rs.getString(2);
                String stream = rs.getString(3);
                String type = rs.getString(4);
                LocalDateTime start_date = rs.getTimestamp("start_date").toLocalDateTime();
                LocalDateTime end_date = rs.getTimestamp("end_date").toLocalDateTime();
                Course course = new Course(course_id, title, stream, type, start_date, end_date);
                list.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(rs, stmt, conn);
        }
        System.out.println("Courses");
        for (int i = 0; i < list.size(); i++) {        
        System.out.println((i+1)+" "+ list.get(i).getTitle() +" "+ list.get(i).getStream() +" "+ list.get(i).getType() +" "+ list.get(i).getStart_date_time().toString()
                +" "+ list.get(i).getEnd_date_time().toString());
        }
        
        //return list;
    }

    protected static void selectStudentsPerCourse() {
        ArrayList<ArrayList<Integer>> list = new ArrayList();
        ArrayList<Integer> listOfCourses = new ArrayList();
        ArrayList<Integer> listOfStudents = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(select_students_per_course);
            while (rs.next()) {
                int course_id = rs.getInt(1);
                int students_id = rs.getInt(2);
                listOfCourses.add(course_id);

                listOfStudents.add(students_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(rs, stmt, conn);
        }
        for (int i = 0; i < listOfCourses.size(); i++) {
            System.out.println("COURSE: " + listOfCourses.get(i) + "   -   STUDENT: " + listOfStudents.get(i));

        }
//        for (int i = 0; i < listOfCourses.size(); i++) {
//            
//            list.add(listOfCourses.get(i), listOfStudents);
//            
//        }
//        return list;
    }

    protected static void selectTrainersPerCourse() {
        ArrayList<ArrayList<Integer>> list = new ArrayList();
        ArrayList<Integer> listOfCourses = new ArrayList();
        ArrayList<Integer> listOfTrainers = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(select_trainers_per_course);
            while (rs.next()) {
                int course_id = rs.getInt(1);
                int trainer_id = rs.getInt(2);
                listOfCourses.add(course_id);

                listOfTrainers.add(trainer_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(rs, stmt, conn);
        }
        for (int i = 0; i < listOfCourses.size(); i++) {
            System.out.println("COURSE: " + listOfCourses.get(i) + "   -   TRAINER: " + listOfTrainers.get(i));

        }
    }

    protected static void selectAssigmentsPerCourse() {
        ArrayList<ArrayList<Integer>> list = new ArrayList();
        ArrayList<Integer> listOfCourses = new ArrayList();
        ArrayList<Integer> listOfAssigments = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(select_assigments_per_course);
            while (rs.next()) {
                int course_id = rs.getInt(1);
                int assigment_id = rs.getInt(2);
                listOfCourses.add(course_id);

                listOfAssigments.add(assigment_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(rs, stmt, conn);
        }
        for (int i = 0; i < listOfCourses.size(); i++) {
            System.out.println("COURSE: " + listOfCourses.get(i) + "   -   ASSIGMENT: " + listOfAssigments.get(i));

        }
    }

    protected static void selectAssigmentsPerCoursePerStudent() {
        ArrayList<ArrayList<Integer>> list = new ArrayList();
        ArrayList<Integer> listOfCourses = new ArrayList();
        ArrayList<Integer> listOfAssigments = new ArrayList();
        ArrayList<Integer> listOfStudents = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(select_assigments_per_course_per_student);
            while (rs.next()) {
                int students_id = rs.getInt(1);
                int course_id = rs.getInt(2);
                int assigment_id = rs.getInt(3);
                listOfStudents.add(students_id);
                listOfCourses.add(course_id);
                listOfAssigments.add(assigment_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(rs, stmt, conn);
        }
        for (int i = 0; i < listOfCourses.size(); i++) {
            System.out.println("STUDENT: " + listOfStudents.get(i) + "  -   COURSE: " + listOfCourses.get(i) + "   -   ASSIGMENT: " + listOfAssigments.get(i));

        }
    }

    protected static void selectMultipleStudentsPerCourse() {
        ArrayList<ArrayList<Integer>> list = new ArrayList();
        ArrayList<Integer> listOfStudents = new ArrayList();
        ArrayList<Integer> listOfNumberOfCourses = new ArrayList();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(select_students_with_multiple_courses);
            while (rs.next()) {
                int student_id = rs.getInt(1);
                int numberCourses_id = rs.getInt(2);
                listOfStudents.add(student_id);

                listOfNumberOfCourses.add(numberCourses_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(rs, stmt, conn);
        }
        for (int i = 0; i < listOfStudents.size(); i++) {
            System.out.println("STUDENT: " + listOfStudents.get(i) + "   -   NUMBER OF COURSES: " + listOfNumberOfCourses.get(i));

        }
    }

}
