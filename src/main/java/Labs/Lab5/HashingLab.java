package Labs.Lab5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class HashingLab {

    ArrayList<Integer> stuff = new ArrayList<>(Arrays.asList(27,
            53,
            13,
            10,
            138,
            109,
            49,
            174,
            26,
            24));;
    int[] storage = new int[13];
    public static final int PRIME = 19;

    LinkedList<Integer>[] buckets = new LinkedList[10];

    public HashingLab(){
        for(int i = 0; i < buckets.length; i++){
            buckets[i] = new LinkedList<>();
        }
    }

    public void hashIt(){
        boolean collisionFlag;
        int i_p;
        int q;
        int offset;
        int comparison_counter;
        for(int x : stuff){
            comparison_counter = 1;
            i_p = x % storage.length;
            q = x / storage.length;
            offset = q % storage.length != 0 ? q : PRIME;
            collisionFlag = storage[i_p] != 0;
            while(collisionFlag){
                i_p = (i_p + offset) % storage.length;
                collisionFlag = storage[i_p] != 0;
                comparison_counter++;
            }
            System.out.println(x + " took " + comparison_counter + " comparisons");
            storage[i_p] = x;
        }
        System.out.println(Arrays.toString(storage));
    }

    public void bucketIt(){
        int i_p;
        for(int x : stuff){
            i_p = x % buckets.length;
            buckets[i_p].add(x);
        }
        for(LinkedList<Integer> bucket : buckets){
            System.out.println(bucket);
        }
    }


}
