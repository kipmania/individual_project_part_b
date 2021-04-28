package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author JOHN
 */
public class Assigment {

    private int assigment_id;
    private String title;
    private String Description;
    private String subDate;
    private LocalDateTime subDateTime;
    int course_id;
    private DateTimeFormatter format = DateTimeFormatter.ISO_DATE_TIME;

    public Assigment(String Description, String inputDate, int course_id) {

        this.Description = Description;
        this.subDate=inputDate;
        this.course_id = course_id;
  //      System.out.println(inputDate);
        
        
//        try {
//           // subDate = LocalDate.parse("skata", format);
//            subDateTime = LocalDateTime.parse(inputDate, format);
//        } catch (DateTimeParseException e) {
//            System.err.println("Wrong date format ");
//        }
//
//        System.out.println("parsed " + subDate.toString());
//        subDateTime = subDate.atTime(00, 00, 00);
//        System.out.println(subDateTime.toString());
    }

    public Assigment(int assigment_id, String Description, LocalDateTime inputDateTime, int course_id) {
        this.assigment_id = assigment_id;
        this.Description = Description;
        this.subDateTime = inputDateTime;
        this.course_id = course_id;

    }

    public LocalDateTime getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(LocalDateTime subDateTime) {
        this.subDateTime = subDateTime;
    }

    public int getAssigment_id() {
        return assigment_id;
    }

    public void setAssigment_id(int assigment_id) {
        this.assigment_id = assigment_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
    private double oralMark;
    private double totalMark;
    private ArrayList<Student> students = new ArrayList<>();

    public void setStudent(Student student) {
        this.students.add(student);

        //System.out.println(subDateTime2.getDayOfWeek());
    }

    public ArrayList<Student> getStudent() {
        return students;
    }

    public void getStudentname() {
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getLastName());

        }
        //return "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

    }

    public String getDescription() {

        //subDateTime2 = LocalDate.parse("2021-3-21");
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getSubDate() {
        return subDate;
    }

    public void setSubDate(String input) {
        this.subDate=input;
//        try {
//            subDate = LocalDate.parse(input, format);
//        } catch (DateTimeParseException e) {
//            System.err.println("Wrong date format  (format DD-MM-YYYY)");
//        }
    }

    public double getOralMark() {
        return oralMark;
    }

    public void setOralMark(double oralMark) {
        this.oralMark = oralMark;
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }

}
