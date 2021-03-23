package Assignments.Assignment4;

import java.io.IOException;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface{

    int size;
    LinkedList<CourseDBElement>[] hashTable;

    public CourseDBStructure(int size){
        hashTable = new LinkedList[size];
        for(int i = 0; i < hashTable.length; i++){
            hashTable[i] = new LinkedList<>();
        }
        this.size = size;
    }

    public CourseDBStructure(String test, int size){
        this(size);
    }

    @Override
    public void add(CourseDBElement element) {
        hashTable[element.hashCode() % hashTable.length].add(element);
    }

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

    @Override
    public int getTableSize() {
        return size;
    }

}
