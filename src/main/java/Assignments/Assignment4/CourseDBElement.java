package Assignments.Assignment4;

import java.util.Objects;

public class CourseDBElement implements Comparable<CourseDBElement>{

    String CourseID;
    int CRN;
    int credits;
    String room_no;
    String instructor;

    public CourseDBElement(){
        this(null, 0, 0, null, null);
    }

    public CourseDBElement(String courseID,
                           int CRN,
                           int credits,
                           String room_no,
                           String instructor){
        this.CourseID = courseID;
        this.CRN = CRN;
        this.credits = credits;
        this.room_no = room_no;
        this.instructor = instructor;
    }

    @Override
    public int compareTo(CourseDBElement o) {
        return (int)Math.signum(getCRN() - o.getCRN());
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    public int getCRN() {
        return CRN;
    }

    public void setCRN(int CRN) {
        this.CRN = CRN;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public int hashCode() {
        return CRN;
    }

    @Override
    public String toString(){
        return String.format("\nCourse:%s CRN:%s Credits:%s Instructor:%s Room:%s",
                getCourseID(),
                getCRN(),
                getCredits(),
                getInstructor(),
                getRoom_no());
    }
}
