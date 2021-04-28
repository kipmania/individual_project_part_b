/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author JOHN
 */
public class Course {

    private int course_id;
    private String title;
    private String stream;
    private String type;
    //private Date start_date = new Date(0, 0, 0, 0, 0, 0);
    //private Date end_date= new Date(0, 0, 0, 23, 59, 59);
    private String start_date;
    private String end_date;
    private LocalDateTime start_date_time;
    private LocalDateTime end_date_time;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final ArrayList<Student> students = new ArrayList<>();
    private final ArrayList<Trainer> trainers = new ArrayList<>();
    private final ArrayList<Assigment> assigments = new ArrayList<>();

    public Course(String title, String stream, String type, String dateStart, String dateEnd) {
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.start_date = dateStart;
        this.end_date = dateEnd;
//        try {
//            start_date_time = LocalDateTime.parse(dateStart, format);
//            end_date_time = LocalDateTime.parse(dateEnd, format);
//        } catch (DateTimeParseException e) {
//            System.err.println("Wrong date format  (format YYYY-MM-DD)");
//        }

    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Course(int course_id, String title, String stream, String type, LocalDateTime dateStart, LocalDateTime dateEnd) {
        this.course_id = course_id;
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.start_date_time = dateStart;
        this.end_date_time = dateEnd;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public LocalDateTime getStart_date_time() {
        return start_date_time;
    }

    public void setStart_date_time(LocalDateTime start_date_time) {
        this.start_date_time = start_date_time;
    }

    public LocalDateTime getEnd_date_time() {
        return end_date_time;
    }

    public void setEnd_date_time(LocalDateTime end_date_time) {
        this.end_date_time = end_date_time;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.title);
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
        final Course other = (Course) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }

    public void addTrainer(Trainer trainer) {
        this.trainers.add(trainer);
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void addAssigment(Assigment assigment) {
        this.assigments.add(assigment);
    }

    public ArrayList<Assigment> getAssigments() {
        return this.assigments;
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public String getStudentsNames() {
        for (int i = 0; i < students.size(); i++) {
            return students.get(i).getLastName();
        }
        return "";
    }

    public ArrayList<Trainer> getTrainers() {
        return this.trainers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
