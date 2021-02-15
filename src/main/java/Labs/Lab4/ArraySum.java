package Labs.Lab4;

public class ArraySum {

    public int sumOfArray (Integer[] a,int index){
        if(a.length < 1 || index < 1) {
            return a[0];
        } else {
            return a[index] + sumOfArray(a, --index);
        }
    }

}
