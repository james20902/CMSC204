package Assignments.Assignment4;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class CourseDBManager implements CourseDBManagerInterface {

    CourseDBStructure structure;

    public CourseDBManager(){
        structure = new CourseDBStructure(10);
    }

    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        structure.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
    }

    @Override
    public CourseDBElement get(int crn) {
        try {
            return structure.get(crn);
        } catch (IOException e){
            return null;
        }
    }

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

    @Override
    public ArrayList<String> showAll() {
        ArrayList<String> output = new ArrayList<>();
        for(LinkedList<CourseDBElement> list : structure.hashTable){
            for(CourseDBElement element : list){
                output.add(String.format("\nCourse:%s CRN:%s Credits:%s Instructor:%s Room:%s",
                        element.getCourseID(),
                        element.getCRN(),
                        element.getCredits(),
                        element.getInstructor(),
                        element.getRoom_no()));
            }
        }
        return output;
    }
}
