package Assignments.Assignment5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class MorseCodeConverter {

    private static MorseCodeTree tree = new MorseCodeTree();

    public MorseCodeConverter(){ }

    public static String convertToEnglish(File codeFile) throws FileNotFoundException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(codeFile));
        reader.lines().forEach((String s) -> buffer.append(convertToEnglish(s)));
        return buffer.toString();
    }

    public static String convertToEnglish(String code){
        StringBuilder buffer = new StringBuilder();
        String[] words = code.split("/");
        for(String letters : words){
            for(String letter : letters.strip().split(" ")){
                buffer.append(tree.fetch(letter));
            }
            buffer.append(" ");
        }
        return buffer.toString().stripTrailing();
    }

    public static String printTree(){
        StringBuilder buffer = new StringBuilder();
        for(String val : tree.toArrayList()){
            buffer.append(val).append(" ");
        }
        return buffer.toString();
    }


}
