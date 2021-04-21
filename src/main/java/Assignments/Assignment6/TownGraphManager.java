package Assignments.Assignment6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TownGraphManager implements TownGraphManagerInterface{
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {
        return false;
    }

    @Override
    public String getRoad(String town1, String town2) {
        return null;
    }

    @Override
    public boolean addTown(String v) {
        return false;
    }

    @Override
    public Town getTown(String name) {
        return null;
    }

    @Override
    public boolean containsTown(String v) {
        return false;
    }

    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        return false;
    }

    @Override
    public ArrayList<String> allRoads() {
        return null;
    }

    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {
        return false;
    }

    @Override
    public boolean deleteTown(String v) {
        return false;
    }

    @Override
    public ArrayList<String> allTowns() {
        return null;
    }

    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        return null;
    }

    public void populateTownGraph(File selectedFile) throws FileNotFoundException,
            IOException {

    }
}
