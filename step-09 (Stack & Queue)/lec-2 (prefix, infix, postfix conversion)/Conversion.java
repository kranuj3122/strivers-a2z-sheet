import java.util.Stack;

public class Conversion {
    static int priority(char c) {
        if(c=='^') {
            return 3;
        }
        if(c=='/' || c=='*') {
            return 2;
        }
        if(c=='+' || c=='-') {
            return 1;
        }
        return 0;
    }
    static boolean isOperator(String c) {
        return "^/*+-".indexOf(c)>-1;
    }

    public static String infixToPostfix(String s) {
        String ans = "";
        Stack<Character> stk = new Stack<>();
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isLetterOrDigit(c)) {
                ans += c;
            }
            else if (c=='(') {
                stk.push('(');
            }
            else if(c==')') {
                while(!stk.empty() && stk.peek()!='(') {
                    ans += stk.pop();
                }
                stk.pop();
            }
            else {
                while(!stk.empty() && priority(stk.peek())>=priority(c)) {
                    ans += stk.pop();
                }
                stk.push(c);
            }
        }
        
        while(!stk.empty()) {
            ans += stk.pop();
        }
        
        return ans;
    }

    static String infixToPrefix(String s) {
        // find postfix of revered string s
        // return reverse of postfix
        String postfix = "";
        int n = s.length();
        Stack<Character> stk = new Stack<>();
        
        for(int i=n-1; i>=0; i--) {
            char c = s.charAt(i);
            if(Character.isLetterOrDigit(c)) {
                postfix += c;
            }
            else if(c==')') {
                stk.push(c);
            }
            else if(c=='(') {
                while(!stk.empty() && stk.peek()!=')') {
                    postfix += stk.pop();
                }
                if(!stk.empty()) {
                    stk.pop();
                }
            }
            else {
                while(!stk.empty() && priority(stk.peek())>=priority(c)) {
                    postfix += stk.pop();
                }
                stk.push(c);
            }
        }
        while(!stk.empty()) {
            postfix += stk.pop();
        }
        
        return new StringBuilder(postfix).reverse().toString();
    }

    static String postToInfix(String exp) {
        int n = exp.length();
        Stack<String> stk = new Stack<>();
        for(int i=0; i<n; i++) {
            String c = ""+exp.charAt(i);
            if(isOperator(c)) {
                String num1 = stk.pop();
                String num2 = stk.pop();
                stk.push("("+num2+c+num1+")");
            }
            else {
                stk.push(c);
            }
        }
        
        return stk.pop();
    }

    static String preToInfix(String pre_exp) {
        int n = pre_exp.length();
        Stack<String> stk = new Stack<>();
        for(int i=n-1; i>=0; i--) {
            String c = ""+pre_exp.charAt(i);
            if(isOperator(c)) {
                String num1 = stk.pop();
                String num2 = stk.pop();
                stk.push("("+num1+c+num2+")");
            }
            else {
                stk.push(c);
            }
        }
        return stk.pop();
    }

    static String preToPost(String pre_exp) {
        int n = pre_exp.length();
        Stack<String> stk = new Stack<>();
        for(int i=n-1; i>=0; i--) {
            String c = ""+pre_exp.charAt(i);
            if(isOperator(c)) {
                String op1 = stk.pop();
                String op2 = stk.pop();
                stk.push(op1+op2+c);
            }
            else {
                stk.push(c);
            }
        }
        return stk.pop();
    }

    static String postToPre(String post_exp) {
        int n = post_exp.length();
        Stack<String> stk = new Stack<>();
        for(int i=0; i<n; i++) {
            String c = ""+post_exp.charAt(i);
            if(isOperator(c)) {
                String op1 = stk.pop();
                String op2 = stk.pop();
                stk.push(c+op2+op1);
            }
            else {
                stk.push(c);
            }
        }
        return stk.pop();
    }


    public static void main(String[] args) {
        // String s = "a+b*(c^d-e)^(f+g*h)-i";
        // String s1 = "A*B+C/D";
        // System.out.println(infixToPostfix(s));
        // System.out.println(infixToPostfix(s1));

        // String s1 = "(a-b/c)*(a/k-l)";
        // String s2 = "a*b+c/d";
        // System.out.println(infixToPrefix(s1));
        // System.out.println(infixToPrefix(s2));

        // String postfix = "ab*c+";
        // System.out.println(postToInfix(postfix));

        // String preExp = "*-A/BC-/AKL";
        // System.out.println(preToInfix(preExp));

        // String preExp = "*-A/BC-/AKL";
        // System.out.println(preToPost(preExp));

        String postExp = "ABC/-AK/L-*";
        System.out.println(postToPre(postExp));
    }
}
