import java.util.*;

public class StockSpanner {
    Stack<Integer> stk;
    List<Integer> list;

    public StockSpanner() {
        stk = new Stack<>();
        list = new ArrayList<>();
    }
    
    public int next(int price) {
        while(!stk.empty() && list.get(stk.peek())<=price) {
            stk.pop();
        }
        int val = stk.empty() ? list.size()+1 : list.size()-stk.peek();
        stk.push(list.size());
        list.add(price);
        return val;
    }

    public static void main(String[] args) {
        StockSpanner obj = new StockSpanner();
        System.out.println(obj.next(100)); // 1
        System.out.println(obj.next(80));  // 1
        System.out.println(obj.next(60));  // 1
        System.out.println(obj.next(70));  // 2
        System.out.println(obj.next(60));  // 1
        System.out.println(obj.next(75));  // 4
        System.out.println(obj.next(85));  // 6
    }
}
