import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

import static java.util.Arrays.binarySearch;

class BinarySearch {
    static int binarySearch(int arr[], int value) {
        int start = 0;
        int end = arr.length-1;
        int iteration=0;
        while (start <= end) {
            iteration++;
            int mid = (start + end) / 2;
            if (value == arr[mid]) {
                System.out.printf("Value %d found at index %d in iteration %d",value, mid,iteration);
                return mid;
            } else if (value < arr[mid]) {
                end = mid - 1;

            } else if (value > arr[mid]) {
                start = mid+ 1;
            }
        }
        System.out.printf("Value %d not found after %d iteration",value,iteration);
        return -1;
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 4, 6, 8, 9};
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter value to search");
        int value= sc.nextInt();
        sc.close();
        binarySearch(arr, value);

    }
}