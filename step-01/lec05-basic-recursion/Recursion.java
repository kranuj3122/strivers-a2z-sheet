import java.util.Arrays;

public class Recursion {
    static void print1toN(int n) {
        if(n==0) {
            return;
        }
        print1toN(n-1);
        System.out.print(n + " "); 
    }

    static void printNto1(int n) {
        if(n==0) {
            return;
        }
        System.out.print(n+" ");
        printNto1(n-1);
    }

    static int sumOfFirstN(int n) {
        if(n<=1) {
            return n;
        }
        return n+sumOfFirstN(n-1);
    }

    static long factorialFirstN(int n) {
        if(n<=1) {
            return 1;
        }
        return n*factorialFirstN(n-1);
    }

    static void reverseArray(int[] arr, int s, int e) {
        if(s>e) {
            return;
        }
        int temp = arr[s];
        arr[s] = arr[e];
        arr[e] = temp;
        reverseArray(arr, s+1, e-1);
    }

    static boolean isPallindromeString(String s, int i, int j) {
        if(i>j) {
            return true;
        }
        if(s.charAt(i)!=s.charAt(j)) {
            return false;
        }

        return isPallindromeString(s, i+1, j-1);
    }

    static int fibonacci(int n) {
        if(n<=1) {
            return n;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }
    
    public static void main(String[] args) {
        // print1toN(5);
        // printNto1(6);
        // System.out.println(sumOfFirstN(5));
        // System.out.println(factorialFirstN(4));
        int[] arr = {1,4,2,5,3,7};
        reverseArray(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
        // String s = "acba";
        // System.out.println(isPallindromeString(s, 0, s.length()-1));
        // System.out.println(fibonacci(5));
    }
}
