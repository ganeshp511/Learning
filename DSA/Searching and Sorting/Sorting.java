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
                if (arr[j] < arr[minIndex]) {//
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

    static void mergeSort(int[] arr, int start, int end) {
        if (start >= end) return;
        //divide array into two subarray
        if (start < end) {
            int mid = (start + end) / 2;
            // Recursively sort first and second halves
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            // Merge the sorted halves
            merge(arr, start, mid, end);
        }

    }

    static void merge(int[] arr, int start, int mid, int end) {
        int leftSubArraySize = mid - start + 1;
        int rightSubArraySize = end - mid;
        int[] leftSubArray = new int[leftSubArraySize];
        int[] rightSubArray = new int[rightSubArraySize];
        for (int i = 0; i < leftSubArraySize; i++) {
            leftSubArray[i] = arr[start + i];//filling the new created array left
        }
        for (int i = 0; i < rightSubArraySize; i++) {
            rightSubArray[i] = arr[mid + 1 + i];//filling the new created array right
        }
        // Merge the temporary arrays

        // Initial indexes of first and second subarrays
        int i = 0;
        int j = 0;
        int k = start;
        while (i < leftSubArraySize && j < rightSubArraySize) {
            if (leftSubArray[i] < rightSubArray[j]) {
                arr[k] = leftSubArray[i]; //compare left subarray value with right one and increment those value whicch is lower
                k++;
                i++;
            } else {
                arr[k] = rightSubArray[j]; //compare right subarray value with left one and increment those value whicch is lower
                k++;
                j++;
            }
        }
        while (i < leftSubArraySize) { //if left array is left behind
            arr[k] = leftSubArray[i];
            k++;
            i++;
        }
        while (j < rightSubArraySize) { // if right array is left behind
            arr[k] = rightSubArray[j];
            k++;
            j++;
        }
    }
    static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(arr, start, end);
            quickSort(arr, start, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, end);
        }
    }
    static int partition(int[] arr, int start, int end){
        int lowerValueIndex=start-1;
        int pivot=arr[end];
        for (int i = start; i < end; i++) {
            if(arr[i]<pivot){
                lowerValueIndex++;
                int temp=arr[lowerValueIndex];
                arr[lowerValueIndex]=arr[i];
                arr[i]=temp;
            }

        }
        lowerValueIndex++;
        int temp=arr[lowerValueIndex];
        arr[lowerValueIndex]=pivot;
        arr[end]=temp;
        return lowerValueIndex;
    }

    public static void main(String[] args) {
        int arr[] = {5, 7, 4, 3, 1, 2};
        int length = arr.length;
        int start = 0;
        int end = length - 1;
        //bubbleSort(arr);
        //selectionSort(arr);
        //insertionSort(arr);
        //mergeSort(arr, start, end);
        quickSort(arr,start,end);

        System.out.println("array after sorting: ");
        for (int elements : arr) {
            System.out.print(elements);
        }
    }


}