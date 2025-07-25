import java.util.HashMap;
import java.util.Map;

public class Hashing {
    static void printFrequencies(int[] arr) {
        Map<Integer, Integer> mp = new HashMap<>();
        for(int num: arr) {
            mp.put(num, mp.getOrDefault(num, 0)+1);
        }
        for(int k: mp.keySet()) {
            System.out.println(k+" occurs "+mp.get(k)+" times.");
        }
    }

    // if collission happens print lowest one
    static void highestOccurringElement(int[] arr) {
        Map<Integer, Integer> mp = new HashMap<>();
        for(int num: arr) {
            mp.put(num, mp.getOrDefault(num, 0)+1);
        }
        int ans = Integer.MAX_VALUE;
        int mxFreq = 0;
        for(int k: mp.keySet()) {
            int val = mp.get(k);
            if(val>mxFreq) {
                mxFreq = val;
                if(k<ans) {
                    ans = k;
                }
            }
        }
        System.out.println(ans+" occurs maximum "+mxFreq+" times.");
    }
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,1,4,3,1,2,5,2,4,6,7};
        // printFrequencies(arr);
        highestOccurringElement(arr);
    }
}
