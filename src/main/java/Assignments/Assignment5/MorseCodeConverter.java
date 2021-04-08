package Assignments.Assignment5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Utility class utilizing tree data structure to translate strings of dots and dashes
 * into english
 *
 * @author James Pham
 */
public class MorseCodeConverter {

    private static MorseCodeTree tree = new MorseCodeTree();

    /**
     * English translation wrapper to accept text files for translation
     * @param codeFile file to parse
     * @return morse code to english
     * @throws FileNotFoundException if given file does not exist or corrupt
     */
    public static String convertToEnglish(File codeFile) throws FileNotFoundException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(codeFile));
        reader.lines().forEach((String s) -> buffer.append(convertToEnglish(s)));
        return buffer.toString();
    }

    /**
     * English translation that accepts string to translate and recursively traverse tree to get characters
     * @param code morse string to translate
     * @return morse code to english
     */
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

    /**
     * String representation of the tree data structure
     * @return tree represented as an array
     */
    public static String printTree(){
        StringBuilder buffer = new StringBuilder();
        for(String val : tree.toArrayList()){
            buffer.append(val).append(" ");
        }
        return buffer.toString();
    }


}
