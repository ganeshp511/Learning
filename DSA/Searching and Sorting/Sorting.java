class Sorting {
    static void bubbleSort(int arr[]) {
        int iteration = 0;
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            iteration++;
            System.out.print("after iteration " + iteration + ": ");
            for (int elements : arr) {
                System.out.print(elements + " ");
            }
            System.out.println();
        }

    }

    static void selectionSort(int arr[]) {
        int iteration = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            //arr[i] is current element
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                    //Min element found
                }

            }
            //swap element with current
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;

        }
    }

    static void insertionSort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int j = i;//start from second element
            while (j > 0 && arr[j] < arr[j - 1]) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;//current element is reduced from index
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = {5, 7, 4, 3, 1};
        //bubbleSort(arr);
        //selectionSort(arr);
        insertionSort(arr);
        System.out.println("array after sorting: ");
        for (int elements : arr) {
            System.out.print(elements);
        }
    }
}