import java.util.Arrays;

public class Sorting {
    static int max(int[] arr) {
        int m = Integer.MIN_VALUE;
        for(int a: arr) {
            m = Math.max(a, m);
        }
        return m;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void selectionSort(int[] arr) {
        int n = arr.length;
        for(int i=0; i<n; i++) {
            int minIndex = i;
            for(int j=i+1; j<n; j++) {
                if(arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            swap(arr, i, minIndex);
        }
    }
    static void stableSelectionSort(int[] arr) {
        int n = arr.length;
        for(int i=0; i<n; i++) {
            int minIndex = i;
            for(int j=i+1; j<n; j++) {
                if(arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int min = arr[minIndex];
            while(minIndex > i) {
                arr[minIndex] = arr[--minIndex];
            }
            arr[i] = min;
        }
    }

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for(int i=n-1; i>=1; i--) {
            for(int j=0; j<i; j++) {
                if(arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }
    static void adaptiveBubbleSort(int[] arr) {
        int n = arr.length;
        for(int i=n-1; i>=1; i--) {
            boolean didSwap = false;

            for(int j=0; j<i; j++) {
                if(arr[j] > arr[j+1]) {
                    didSwap = true;
                    swap(arr, j, j+1);
                }
            }

            if(!didSwap) {
                break;
            }
        }
    }
    static void recursiveBubbleSort(int[] arr, int n) {
        if(n<=1) {
            return;
        }
        boolean didSwap = false; // used to make it adaptive (best case)
        for(int i=0; i<=n-2; i++) {
            if(arr[i]>arr[i+1]) {
                swap(arr, i, i+1);
                didSwap = true;
            }
        }
        if(!didSwap) {
            return;
        }

        recursiveBubbleSort(arr, n-1);
    }

    static void insertionSort(int[] arr) {
        int n = arr.length;
        for(int i=1; i<n; i++) {
            int j = i;
            while(j>0 && arr[j-1]>arr[j]) {
                swap(arr, j, j-1);
                j--;
            }
        }
    }
    static void recursiveInsertionSort(int[] arr, int i) {
        int n = arr.length;
        if(i==n) {
            return;
        }
        int j = i;
        while(j>0 && arr[j-1]>arr[j]) {
            swap(arr, j, j-1);
            j--;
        }
        recursiveInsertionSort(arr, i+1);
    }

    static void mergeSort(int[] arr, int low, int high) {
        if(low>=high) {
            return;
        }
        int mid = (low+high)/2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid+1, high);
        merge(arr, low, mid, high);
    }
    static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high-low+1];
        int i=low, j=mid+1;
        int k = 0;
        while(i<=mid && j<=high) {
            if(arr[i]<=arr[j]) {
                temp[k] = arr[i++];
            }
            else{
                temp[k] = arr[j++];
            }
            k++;
        }
        while(i<=mid) {
            temp[k++] = arr[i++];
        }
        while(j<=high) {
            temp[k++] = arr[j++];
        }

        for(int x=low; x<=high; x++) {
            arr[x] = temp[x-low];
        }
    }

    static void quikSort(int[] arr, int low, int high) {
        if(low>=high) {
            return;
        }
        int partitionIndex = partition(arr, low, high);
        quikSort(arr, low, partitionIndex-1);
        quikSort(arr, partitionIndex+1, high);
    }
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i=low, j=high;
        while(i<j) {
            while(i<high && arr[i]<=pivot) {
                i++;
            }
            while(j>low && arr[j]>pivot) {
                j--;
            }
            if(i<j) {
                swap(arr, i, j);
            }
        }
        swap(arr, low, j);

        return j;
    }

    static void countSort(int[] arr) {
        int mx = max(arr);
        int[] cnt = new int[mx+1];
        for(int num: arr) {
            cnt[num]++;
        }
        int j=0;
        for(int i=0; i<=mx; i++) {
            while(cnt[i]>0) {
                arr[j++] = i;
                cnt[i]--;
            }
        }
    }
    

    public static void main(String[] args) {
        int[] arr = new int[] {12, 47, 5, 29, 34, 1, 18, 42, 8, 25};
        int n = arr.length;


        // selectionSort(arr);
        // stableSelectionSort(arr);

        // bubbleSort(arr);
        // adaptiveBubbleSort(arr);
        // recursiveBubbleSort(arr, n);

        // insertionSort(arr);
        // recursiveInsertionSort(arr, 0);

        mergeSort(arr, 0, n-1);

        // quikSort(arr, 0, n-1);

        // countSort(arr);


        System.out.println(Arrays.toString(arr));
    }
}
