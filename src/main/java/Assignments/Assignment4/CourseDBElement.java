package Assignments.Assignment4;


/**
 * Class to containerize a course at Montgomery College with comparison capability
 * @author James Pham
 */
public class CourseDBElement implements Comparable<CourseDBElement>{

    String CourseID;
    int CRN;
    int credits;
    String room_no;
    String instructor;

    /**
     * Constructor to create empty course for later editing
     */
    public CourseDBElement(){
        this(null, 0, 0, null, null);
    }

    /**
     * Parameterized constructor to represent aspects of a course
     * @param courseID the four letter, three digit course ID
     * @param CRN the unique course number
     * @param credits the amount of credits the course offers
     * @param room_no the room number where the course is located
     * @param instructor the instructor teaching the course
     */
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

    /**
     * Compares a given course by course number
     * @param o course to compare
     * @return 1 if greater than, 0 if equal, -1 if less than
     */
    @Override
    public int compareTo(CourseDBElement o) {
        return (int)Math.signum(getCRN() - o.getCRN());
    }

    /**
     * Gets the course name
     * @return the course name
     */
    public String getCourseID() {
        return CourseID;
    }

    /**
     * Sets the course name
     * @param courseID course name to set
     */
    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    /**
     * Gets the unique course number
     * @return course number
     */
    public int getCRN() {
        return CRN;
    }

    /**
     * Sets the course number
     * @param CRN course number to set
     */
    public void setCRN(int CRN) {
        this.CRN = CRN;
    }

    /**
     * Gets the number of credits offered
     * @return the number of credits offered
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Sets the number of credits offered
     * @param credits the number of credits to offer
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Gets the room number the course is being held in
     * @return the course room number
     */
    public String getRoom_no() {
        return room_no;
    }

    /**
     * Sets the room number the course is to be held in
     * @param room_no the course room number
     */
    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    /**
     * Gets the name of the instructor teaching this course
     * @return the name of the instructor
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Sets the name of the instructor teaching this course
     * @param instructor the instructor's name
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * Hash code for each object
     * @return unique course number
     */
    @Override
    public int hashCode() {
        return CRN;
    }

    /**
     * String representation for debugging purposes
     * @return All information about a course nicely represented as a string
     */
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
