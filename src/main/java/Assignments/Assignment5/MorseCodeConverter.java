package Assignments.Assignment5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class MorseCodeConverter {

    private static MorseCodeTree tree;

    public MorseCodeConverter(){
        tree = new MorseCodeTree();
    }

    public static String convertToEnglish(File codeFile) throws FileNotFoundException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(codeFile));
        reader.lines().forEach((String s) -> {
            buffer.append(convertToEnglish(s));
        });
        return buffer.toString();
    }

    public static String convertToEnglish(String code){
        StringBuilder buffer = new StringBuilder();
        String[] words = code.split("/");
        for(String letters : words){
            
        }
        String[] letters = code.split(" ");
        for(String letter : letters){
            for(String character : letter.split("")){
                buffer.append(tree.fetch(character));
            }
        }
        return buffer.toString();
    }

    public static String printTree(){
        return tree.toArrayList().toString();
    }


}
