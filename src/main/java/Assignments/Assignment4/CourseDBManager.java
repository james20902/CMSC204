package Assignments.Assignment4;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class to handle course objects being added to a database
 *
 * @author James Pham
 */
public class CourseDBManager implements CourseDBManagerInterface {

    CourseDBStructure structure;

    /**
     * Constructor to create database instance
     */
    public CourseDBManager(){
        structure = new CourseDBStructure(10);
    }

    /**
     * Adds a given course to the database
     * @param id course ID
     * @param crn course unique number
     * @param credits number of credits offered
     * @param roomNum the room the course will be taught in
     * @param instructor the instructor teaching the course
     */
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        structure.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
    }

    /**
     * Retreives a course object based on the unique course number
     * @param crn the number to search for
     * @return the course object corresponding to that number
     */
    @Override
    public CourseDBElement get(int crn) {
        try {
            return structure.get(crn);
        } catch (IOException e){
            return null;
        }
    }

    /**
     * Reads a given file and adds the course parameters delimited by a space
     * @param input File object to index
     * @throws FileNotFoundException if the given parameter doesn't exist
     */
    @Override
    public void readFile(File input) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(input));
        reader.lines().forEach((String s) -> {
            String[] values = s.split(" ", 5);
            add(values[0],
                    Integer.parseInt(values[1]),
                    Integer.parseInt(values[2]),
                    values[3],
                    values[4]);
        });
    }

    /**
     * Shows all courses currently in the database
     * @return an array list containing all elements sorted by the hashing algorithm
     */
    @Override
    public ArrayList<String> showAll() {
        ArrayList<String> output = new ArrayList<>();
        for(LinkedList<CourseDBElement> list : structure.hashTable){
            for(CourseDBElement element : list){
                output.add(element.toString());
            }
        }
        return output;
    }
}
