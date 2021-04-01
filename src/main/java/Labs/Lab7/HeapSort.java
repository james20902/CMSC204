package Labs.Lab7;

import java.util.Arrays;

public class HeapSort {

    int[] stuff = {5, 22, 9, 76, 63, 81, 48, 92, 54, 28};

    public HeapSort(){
        for (int i = (stuff.length / 2) - 1; i >= 0; i--) {
            heapify(stuff, stuff.length, i);
        }
        System.out.println(Arrays.toString(stuff));
        System.out.println("starting sort");
        sort(stuff);
        System.out.println(Arrays.toString(stuff));
    }

    static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            System.out.println(Arrays.toString(arr));

            heapify(arr, n, largest);
        }
    }

    public void sort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

}
