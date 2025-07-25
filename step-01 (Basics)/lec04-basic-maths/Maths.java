import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Maths {
    static int countDigit(int n) {
        int cnt = 0;
        while(n>0) {
            cnt++;
            n /= 10;
        }
        return cnt;
    }

    static int reverseNumber(int n) {
        int rev = 0;
        while(n>0) {
            rev = (rev*10) + (n%10);
            n /= 10;
        }
        return rev;
    }

    static boolean isPallindromeNumber(int x) {
        if(x<0 || (x!=0 && x%10==0)) {
            return false;
        }
        return reverseNumber(x)==x;
    }

    static int gcd(int a, int b) {
        if(b==0) {
            return a;
        }
        return gcd(b, a%b);
    }

    static boolean isArmstrong(int n) {
        int num = n;
        int p = countDigit(n);
        long temp = 0;
        while(n>0) {
            int r = n%10;
            temp += Math.pow(r, p);
            n /= 10;
        }
        return temp==num;
    }

    static List<Integer> divisors(int n) {
        List<Integer> list = new ArrayList<>();
        for(int i=1; i*i<=n; i++) {
            if(n%i==0) {
                list.add(i);
                if(i!=n/i) {
                    list.add(n/i);
                }
            }
        }
        Collections.sort(list);
        return list;
    }

    static boolean isPrime(int n) {
        if(n<=1) {
            return false;
        }
        if(n==2 || n==3) {
            return true;
        }
        if(n%2==0 || n%3==0) {
            return false;
        }
        for(int i=5; i*i<=n; i+=6) {
            if(n%i==0 || n%(i+2)==0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // System.out.println(countDigit(1234));
        // System.out.println(reverseNumber(2343));
        // System.out.println(isPallindromeNumber(1221));
        // System.out.println(gcd(4, 8));
        // System.out.println(isArmstrong(2025));
        // System.out.println(divisors(120));
        System.out.println(isPrime(20));
    }
}
