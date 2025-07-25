import java.util.*;

public class EasyRecursionProblem {
    static int handleBounds(long num, boolean isNegative) {
        num = isNegative ? -num : num;
        if (num < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if (num > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) num;
    }
    static int convert(String s, int ind, long num, boolean isNegative) {
        if (ind == s.length() || num > Integer.MAX_VALUE) {
            return handleBounds(num, isNegative);
        }
    
        char c = s.charAt(ind);
        if (c < '0' || c > '9') {
            return handleBounds(num, isNegative);
        }
    
        num = num * 10L + (c - '0');
        return convert(s, ind + 1, num, isNegative);
    }
    static int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
    
        boolean isNegative = false;
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            isNegative = s.charAt(0) == '-';
            s = s.substring(1);
        }
    
        return convert(s, 0, 0L, isNegative);
    }

    static double power(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double half = power(x, n / 2);
        double result = half * half;
        if (n % 2 != 0) {
            result *= x;
        }
        return result;
    }
    static double myPow(double x, int n) {
        if (n < 0) {
            return 1 / power(x, -n);
        }
        return power(x, n);
    }

    static long pow(long x, long p) {
        int mod = (int)1e9 + 7;
        long result = 1;
        while(p > 0) {
            if(p%2 == 1) {
                result *= x;
                result %= mod;
            }
            x *= x;
            x %= mod;
            p /= 2;
        }
        return result%mod;
    }
    static long pow2(long x, long p) {
        // recursive
        if(p==0) {
            return 1L;
        }
        int mod = (int)1e9 + 7;
        long half = pow2(x, p/2)%mod;
        long result = (half*half)%mod;
        if(p%2 == 1) {
            result *= x;
        }
        return result%mod;
    }
    static int countGoodNumbers(long n) {
        int mod = (int)1e9 + 7;
        // long ans = pow(20L, n/2);
        long ans = pow2(20L, n/2);
        if(n%2==1) {
            ans *= 5L;
        }
        return (int)(ans%mod);
    }

    static void insert(Stack<Integer> stk, int val) {
        if(stk.isEmpty() || stk.peek()<=val) {
            stk.push(val);
            return;
        }
        int peek = stk.pop();
        insert(stk, val);
        stk.push(peek);
    }
    static Stack<Integer> sort(Stack<Integer> s) {
        if(s.isEmpty() || s.size()==1) {
            return s;
        }
        int top = s.pop();
        Stack<Integer> stk = sort(s);
        insert(stk, top);
        return stk;
    }

    static void insertAtBottom(Stack<Integer> stk, int num) {
        if(stk.isEmpty()) {
            stk.push(num);
            return;
        }
        int p = stk.pop();
        insertAtBottom(stk, num);
        stk.push(p);
    }
    static void reverse(Stack<Integer> s) {
        if(s.isEmpty() || s.size()==1) {
            return;
        }
        int p = s.pop();
        reverse(s);
        insertAtBottom(s, p);
    }

    public static void main(String[] args) {
        // String s = " -042";
        // System.out.println(myAtoi(s));

        // double x = 2.10000;
        // int n = 3;
        // System.out.println(myPow(x, n));

        // long n = 50;
        // System.out.println(countGoodNumbers(n));

        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(1);
        stack.push(2);
        System.out.println("Sorted Stack: " + sort(stack));
        stack.push(3);
        reverse(stack);
        System.out.println("Reversed Stack: " + stack);

    }
}
