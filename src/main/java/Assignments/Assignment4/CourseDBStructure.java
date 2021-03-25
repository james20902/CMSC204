package Assignments.Assignment4;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Data structure class to handle hashing course objects for database manipulation purposes
 *
 * @author James Pham
 */
public class CourseDBStructure implements CourseDBStructureInterface{

    int size;
    LinkedList<CourseDBElement>[] hashTable;

    /**
     * Constructor to control hashing algorithm
     * @param size The number of buckets that objects should be hashed into
     */
    public CourseDBStructure(int size){
        hashTable = new LinkedList[size];
        for(int i = 0; i < hashTable.length; i++){
            hashTable[i] = new LinkedList<>();
        }
        this.size = size;
    }

    /**
     * Parameterized constructor for testing purposes
     * @param test N/A
     * @param size The number of buckets that objects should be hashed into
     */
    public CourseDBStructure(String test, int size){
        this(size);
    }

    /**
     * Adds a given element into the data structure
     * @param element the CDE to be added and hashed by length
     */
    @Override
    public void add(CourseDBElement element) {
        hashTable[element.hashCode() % hashTable.length].add(element);
    }

    /**
     * Searches for a course object based on unique course number
     * @param crn Course number to look for
     * @return Course object with unique course number
     * @throws IOException if the given course number wasn't found
     */
    @Override
    public CourseDBElement get(int crn) throws IOException {
        LinkedList<CourseDBElement> list = hashTable[crn % hashTable.length];
        for(CourseDBElement element : list){
            if(element.getCRN() == crn){
                return element;
            }
        }
        throw new IOException();
    }

    /**
     * The number of buckets in the array
     * @return the size of the array
     */
    @Override
    public int getTableSize() {
        return size;
    }

}
