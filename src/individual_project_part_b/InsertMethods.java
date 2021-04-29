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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertMethods {

    private static final String InsertStudent = "INSERT INTO `indidual_project_part_b`.`students` (`first_name`, `last_name`, `date_of_birth`, `tuition_fees`) VALUES (?, ?, ?,?)";
    private static final String InsertTrainer = "INSERT INTO `indidual_project_part_b`.`trainers` (`first_name`, `last_name`)  VALUES ( ?, ?)";
    private static final String InsertSubjectOfTrainer = "INSERT INTO `indidual_project_part_b`.`subjects` (`Trainer_id`, `Subject_name`)  VALUES ( ?, ?)";
    private static final String InsertAssigment = "INSERT INTO `indidual_project_part_b`.`assigments` (`description`, `submission_date`, `course_idcourse`)  VALUES (?, ?, ?)";
    private static final String InsertCourse = "INSERT INTO `indidual_project_part_b`.`courses` (`title`, `stream`, `type`, `start_date`, `end_date`) VALUES (?, ?, ?,?,?)";
    private static final String InsertStudentsPerCourse = "INSERT INTO `indidual_project_part_b`.`studies` (`students_id`, `courses_id`)VALUES (?, ?)";
    private static final String InsertTrainersPerCourse = "INSERT INTO `indidual_project_part_b`.`studies` (`students_id`, `courses_id`)VALUES (?, ?)";
    private static final String InsertAssigmentPerStudentPerCourse = "INSERT INTO `indidual_project_part_b`.`assigments_per_student_per_course` (`students_idstudent`, `courses_idcourse`, `assigments_idassigment`)"
            + " VALUES (?, ?,?)";

    public static void insert(Course course) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(InsertCourse);

            pstm.setString(1, course.getTitle());
            pstm.setString(2, course.getStream());
            pstm.setString(3, course.getType());
            System.out.println(course.getStart_date());
            Timestamp start_date = Timestamp.valueOf(course.getStart_date());
            Timestamp end_date = Timestamp.valueOf(course.getEnd_date());
            pstm.setTimestamp(4, start_date);
            pstm.setTimestamp(5, end_date);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Course successfully created!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(pstm, conn);
        }
    }

    public static void insert(Student student) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(InsertStudent);
            pstm.setString(1, student.getFirstName());
            pstm.setString(2, student.getLastName());
            Timestamp dateOfBirth = Timestamp.valueOf(student.getDateOfBirth());
            pstm.setTimestamp(3, dateOfBirth);
            pstm.setDouble(4, student.getTuitionFees());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Student successfully created!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(pstm, conn);
        }
    }

    public static void insert(Assigment assigment) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(InsertAssigment);
            pstm.setString(1, assigment.getDescription());
            Timestamp dateOfSubmission = Timestamp.valueOf(assigment.getSubDate());
            pstm.setTimestamp(2, dateOfSubmission);
            pstm.setInt(3, assigment.getCourse_id());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Assigment successfully created!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(pstm, conn);
        }
    }

    public static void insert(Trainer trainer) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        Statement stmt = null;
        try {
            //TO ANTIKEIMENO TRAINER MAS DINEI ONOMA-EPONYMO-SUBJECT.
            //APOTHIKEYO TO ONOMA-EPONYMO STON PINAKA TRAINER
            pstm = conn.prepareStatement(InsertTrainer);
            pstm.setString(1, trainer.getFirstName());
            pstm.setString(2, trainer.getLastName());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Trainer successfully created!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(pstm, conn);
        }
        insertSubjectOfTrainer(trainer);
    }

    public static void insert(Student student, Course course) {
        //STUDENTS PER COURSE EINAI EISODOS STON TABLE STUDIES
        int student_id = selectIDOfStudent(student);
        int course_id = selectIDOfCourse(course);

        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            //APOTHIKEYO TO STUDENT-COURSE STON PINAKA STUDIES
            pstm = conn.prepareStatement(InsertStudentsPerCourse);
            pstm.setInt(1, student_id);
            pstm.setInt(2, course_id);

            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Student per course successfully created in table studies!!");
            }
        } catch (SQLIntegrityConstraintViolationException e ){
            System.err.println("At least one entry doesn't exist.");
        }
        catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            Methods.closeConnections(pstm, conn);
        }
    }

    public static void insert(Trainer trainer, Course course) {
        //TRAINERS PER COURSE EINAI EISODOS STO TABLE SUBJECTS
        int trainer_id = selectIDOfTrainer(trainer);
        int course_id = selectIDOfCourse(course);
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //KAUE PLEIADA TOY PINAKA SUBJECT THELEI TRAINER_ID, COURSE_ID KAI SUBJECT_NAME
            pstm = conn.prepareStatement(InsertTrainersPerCourse);
            pstm.setInt(1, trainer_id);
            pstm.setInt(2, course_id);

            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Student per course successfully created in table studies!!");
            }
        }catch (SQLIntegrityConstraintViolationException e ){
            System.err.println("At least one entry doesn't exist.");
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(pstm, conn);
        }
    }

    public static void insert(Assigment assigment, Student student, Course course) {
       
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        Statement stmt = null;
        ResultSet rs = null;
        int student_id = selectIDOfStudent(student);
        int course_id = selectIDOfCourse(course);
        int assigment_id = selectIDOfAssigment(assigment);
        try {
            //KAUE PLEIADA TOY PINAKA SUBJECT THELEI TRAINER_ID, COURSE_ID KAI SUBJECT_NAME
            pstm = conn.prepareStatement(InsertAssigmentPerStudentPerCourse);
            pstm.setInt(1, student_id);
            pstm.setInt(2, course_id);
            pstm.setInt(3, assigment_id);

            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Assigment per Student per course successfully created!!");
            }
        }catch (SQLIntegrityConstraintViolationException e ){
            System.err.println("At least one entry doesn't exist.");
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(pstm, conn);
        }
    }

    public static void insertSubjectOfTrainer(Trainer trainer) {
        int lastInsertedTrainerID = selectIDOfLastTrainer(trainer);
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(InsertSubjectOfTrainer);
            pstm.setInt(1, lastInsertedTrainerID);
            pstm.setString(2, trainer.getSubject());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Trainer's subject successfullu inserted!!");
            }
        }catch (SQLIntegrityConstraintViolationException e ){
            System.err.println("At least one entry doesn't exist.");
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(pstm, conn);
        }
    }

    private static int selectIDOfLastTrainer(Trainer trainer) {
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        int lastInsertedTrainerID = 0;
        try {
            //APOTHIKEYO TO SUBJECT TOY SYGKEKRIMENOY TRAINER (ME TO ID POY PAIRNEI AYTOMATA APO TH BASH APO AUTO-INCREMENT)STON PINAKA SUBJECT
            stmt = conn.createStatement();
            //PAIRNO TO ID TIS TELEYTAIAS EISAGVGHS STON PINAKA TRAINERS
            rs = stmt.executeQuery("SELECT TRAINERS.IDTRAINER FROM TRAINERS ORDER BY 1 DESC LIMIT 1;");
            while (rs.next()) {
                lastInsertedTrainerID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(rs, stmt, conn);
        }
        System.out.println("Last inserted ID is " + lastInsertedTrainerID);
        return lastInsertedTrainerID;
    }

    public static int selectIDOfStudent(Student student) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int selectedStudentID = 0;
        try {
            pstm = conn.prepareStatement("SELECT * FROM indidual_project_part_b.students where last_name like ?; ");
            pstm.setString(1, ("%"+student.getLastName())+"%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                selectedStudentID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(rs, pstm, conn);
        }
        System.out.println("Student ID of "+ student.getLastName()+" is " + selectedStudentID);
        return selectedStudentID;
    }

    private static int selectIDOfCourse(Course course) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int selectedCourseID = 0;
        try {
            pstm = conn.prepareStatement("SELECT idcourse FROM indidual_project_part_b.courses where courses.title like ?;");
            pstm.setString(1,"%"+ course.getTitle()+"%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                selectedCourseID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(rs, pstm, conn);
        }
        System.out.println("Course ID of "+ course.getTitle()+" is " + selectedCourseID);
        return selectedCourseID;
    }
    
    private static int selectIDOfTrainer(Trainer trainer) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int TrainerID = 0;
        try {
            pstm = conn.prepareStatement("SELECT * FROM indidual_project_part_b.trainers WHERE last_name like ?;");
            pstm.setString(1,"%"+ trainer.getLastName()+"%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                TrainerID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(rs, pstm, conn);
        }
        System.out.println("Trainer ID of "+ trainer.getLastName()+" is " + TrainerID);
        return TrainerID;
    }

    private static int selectIDOfAssigment(Assigment assigment) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int AssigmentID = 0;
        try {
            pstm = conn.prepareStatement("SELECT * FROM indidual_project_part_b.assigments WHERE description like ?;");
            pstm.setString(1,"%"+ assigment.getDescription()+"%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                AssigmentID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Methods.closeConnections(rs, pstm, conn);
        }
        System.out.println("Assigment ID of \""+  assigment.getDescription() +"\" is " + AssigmentID);
        return AssigmentID;
    }
}
