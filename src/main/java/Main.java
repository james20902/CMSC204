import Assignments.Assignment1.PasswordDriverFX;

import java.util.Arrays;

public class Main {

    public static void main(String[] args){
//        PasswordDriverFX.main(args);
        String[] lines = {
                "Full many a glorious morning have I seen",
                        "Flatter the mountain-tops with sovereign eye,",
                        "Kissing with golden face the meadows green,",
                        "Gilding pale streams with heavenly alchemy;",
                        "Anon permit the basest clouds to ride",
                        "With ugly rack on his celestial face,",
                        "And from the forlorn world his visage hide,",
                        "Stealing unseen to west with this disgrace:",
                        "Even so my sun one early morn did shine",
                        "With all triumphant splendor on my brow;",
                        "But out! alack! he was but one hour mine,",
                        "The region cloud hath mask'd him from me now.",
                        "Yet him for this my love no whit disdaineth;",
                        "Suns of the world may stain when heaven's sun staineth."
        };

        char[][] stripped = new char[lines.length][];
        for(int i = 0; i < lines.length; i++){
            stripped[i] = lines[i].replace(" ", "").replaceAll("[^A-Za-z0-9]", "").toCharArray();
        }

        for(char[] x : stripped){
            System.out.println(Arrays.toString(x));
        }


        StringBuilder buffer = new StringBuilder();
        buffer.append("youtube.com/watch?v=")
                .append(stripped[2][27])
                .append(stripped[11][13])
                .append("0")
                .append(stripped[0][19])
                .append(stripped[3][2])
                .append(stripped[10][10])
                .append(stripped[4][9])
                .append(stripped[1][32])
                .append("5")
                .append(stripped[6][5])
                .append(stripped[12][13]);
        System.out.println(buffer.toString());
        System.out.println(nullPrint(null));
    }

    public static String nullPrint(Object val){
        return val != null ? val.toString() : "null";
    }




}
