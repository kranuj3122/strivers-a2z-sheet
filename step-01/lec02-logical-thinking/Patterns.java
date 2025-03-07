public class Patterns {
    // pattern 01
    static void printSquare(int n) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
    
    // pattern 02
    static void printTriangle(int n) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<=i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    // pattern 03
    static void printTriangleWithNumber(int n) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<=i; j++) {
                System.out.print((j+1)+" ");
            }
            System.out.println();
        }
    }

    // pattern 04
    static void printTriangleWithSameNumberEachRow(int n) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<=i; j++) {
                System.out.print((i+1)+" ");
            }
            System.out.println();
        }
    }

    // pattern 05
    static void downwardTriangle(int n) {
        for(int i=n; i>=1; i--) {
            for(int j=0; j<i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    // pattern 06
    static void downwardTriangleWithNumber(int n) {
        for(int i=n; i>=1; i--) {
            for(int j=1; j<=i; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    // pattern 07 (Not Right-Angled)
    static void topToBottomTriangle(int n) {
        for(int i=0; i<n; i++) {
            int space = n-1-i;
            for(int j=0; j<space; j++) {
                System.out.print(" ");
            }
            int stars = 2*i+1;
            for(int j=0; j<stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // pattern 08 (Not Right-Angled)
    static void bottomToTopTriangle(int n) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                System.out.print(" ");
            }
            for(int j=0; j<2*(n-i)-1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // pattern 09
    static void printDiamond(int n) {
        topToBottomTriangle(n);
        bottomToTopTriangle(n);
    }

    // pattern 10
    static void triangleWithVerticesAtMiddle(int n) {
        printTriangle(n);
        downwardTriangle(n-1);
    }

    // pattern 11
    static void triangleWithAlternateBinary(int n) {
        boolean flag = true;
        for(int i=0; i<n; i++) {
            boolean tempFlag = flag;
            for(int j=0; j<=i; j++) {
                System.out.print((tempFlag ? 1 : 0)+" ");
                tempFlag = !tempFlag;
            }
            flag = !flag;
            System.out.println();
        }
    }

    // pattern 12
    static void decreasingIncreasingLadderTriangle(int n) {
        for(int i=0; i<n; i++) {
            int cols = 2*n;
            for(int j=0; j<cols; j++) {
                if(j<=i || (cols-j)<=(i+1)) {
                    System.out.print(j<=i ? j+1 : cols-j);
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    // pattern 13
    static void triangleWithIncreasingNumbers(int n) {
        int cnt = 1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<=i; j++) {
                System.out.print((cnt++)+" ");
            }
            System.out.println();
        }
    }

    // pattern 14
    static void triangleWithAlphabet(int n) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<=i; j++) {
                System.out.print((char)('A'+j)+" ");
            }
            System.out.println();
        }
    }

    // pattern 15
    static void downwardTriangleWithAlphabet(int n) {
        for(int i=n; i>=1; i--) {
            for(int j=0; j<i; j++) {
                System.out.print((char)('A'+j)+" ");
            }
            System.out.println();
        }
    }

    // pattern 16
    static void triangleWithSameAlphabetAtEachRow(int n) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<=i; j++) {
                System.out.print((char)('A'+i)+" ");
            }
            System.out.println();
        }
    }

    // pattern 17
    static void alphabetTriangle(int n) {
        
    }

    // pattern 18
    static void reverseAlphabetTriangle(int n) {
        for(int i=0; i<n; i++) {
            for(int j=i; j>=0; j--) {
                System.out.print((char)('A'+(n-1-j))+" ");
            }
            System.out.println();
        }
    }

    // pattern 19 (hollow diamon star pattern)
    static void hollowDiamond(int n) {
        int rows=2*n, cols=2*n;
        for(int i=1; i<=rows; i++) {
            for(int j=1; j<=cols; j++) {
                if(i==1 || i==rows || j==1 || j==cols) {
                    System.out.print("*");
                }
                else {
                    System.out.print(" ");
                }
            }
        }
    }

    // pattern 21
    static void hollowRectangle(int n) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i==0 || i==n-1 || j==0 || j==n-1) {
                    System.out.print("*");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // printSquare(5);
        // printTriangle(4);
        // printTriangleWithNumber(5);
        // printTriangleWithSameNumberEachRow(5);
        // downwardTriangle(4);
        // downwardTriangleWithNumber(4);
        // topToBottomTriangle(3);
        // bottomToTopTriangle(4);
        // printDiamond(5);
        // triangleWithVerticesAtMiddle(5);
        // triangleWithAlternateBinary(5);
        // decreasingIncreasingLadderTriangle(5);
        // triangleWithIncreasingNumbers(5);
        // triangleWithAlphabet(5);
        // downwardTriangleWithAlphabet(5);
        // triangleWithSameAlphabetAtEachRow(5);
        // alphabetTriangle(5); TO-DO
        // reverseAlphabetTriangle(5);
        // To-Do
        // To-Do
        // hollowRectangle(5);
    }
}