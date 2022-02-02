package sky.pro;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Integer[] arr1 = generateRandomArray();
        Integer[] arr2 = generateRandomArray();
        Integer[] arr3 = generateRandomArray();

        long start;
        start = System.currentTimeMillis();
        sortBubble(arr1);
        System.out.println(System.currentTimeMillis() - start + " sortBubble");

        start = System.currentTimeMillis();
        sortSelection(arr2);
        System.out.println(System.currentTimeMillis() - start + " sortSelection");

        start = System.currentTimeMillis();
        sortInsertion(arr3);
        System.out.println(System.currentTimeMillis() - start + " sortInsertion");

    }


    public static Integer[] generateRandomArray() {
        Random random = new Random();
        Integer[] arr = new Integer[10000];
        for (int index = 0; index < arr.length; index++) {
            arr[index] = random.nextInt(100_000);
        }
        return arr;
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
