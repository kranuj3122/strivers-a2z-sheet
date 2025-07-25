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
        for(int i=1; i<=n; i++) {
            int b = n-i;
            for(int j=0; j<b; j++) {
                System.out.print(" ");
            }
            int chars = (2*i) - 1;
            char ch = 'A';
            for(int j=0; j<chars; j++) {
                System.out.print(ch);
                if(j<chars/2) {
                    ch++;
                }
                else {
                    ch--;
                }
            }
            System.out.println();
        }
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
        for(int i=0; i<n; i++) {
            int stars = n-i;
            int blank = 2*i;
            for(int j=0; j<stars; j++) {
                System.out.print("*");
            }
            for(int j=0; j<blank; j++) {
                System.out.print(" ");
            }
            for(int j=0; j<stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for(int i=n-1; i>=0; i--) {
            int stars = n-i;
            int blank = 2*i;
            for(int j=0; j<stars; j++) {
                System.out.print("*");
            }
            for(int j=0; j<blank; j++) {
                System.out.print(" ");
            }
            for(int j=0; j<stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void horizontalDamroo(int n) {
        for(int i=0; i<n; i++) {
            int stars = i+1;
            int blank = 2*(n-i-1);
            for(int j=0; j<stars; j++) {
                System.out.print("*");
            }
            for(int j=0; j<blank; j++) {
                System.out.print(" ");
            }
            for(int j=0; j<stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for(int i=n-2; i>=0; i--) {
            int stars = i+1;
            int blank = 2*(n-i-1);
            for(int j=0; j<stars; j++) {
                System.out.print("*");
            }
            for(int j=0; j<blank; j++) {
                System.out.print(" ");
            }
            for(int j=0; j<stars; j++) {
                System.out.print("*");
            }
            System.out.println();
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

    // pattern 22
    static void spiralNumberSquare(int n) {
        int sz = 2*n-1;
        int[][] spiral = new int[sz][sz];

        int startRow = 0;
        int endRow = sz-1;
        int startCol = 0;
        int endCol = sz-1;


        while(startRow<=endRow && startCol<=endCol) {
            for(int i=startCol; i<=endCol; i++) {
                spiral[startRow][i] = n;
            }
            startRow++;
            for(int i=startRow; i<=endRow; i++) {
                spiral[i][endCol] = n;
            }
            endCol--;
            for(int i=endCol; i>=startCol; i--) {
                spiral[endRow][i] = n;
            }
            endRow--;
            for(int i=endRow; i>=startRow; i--) {
                spiral[i][startCol] = n;
            }
            startCol++;

            n--;
        }
        
        for(int[] sp: spiral) {
            for(int num: sp) {
                System.out.print(num);
            }
            System.out.println();
        }
    }

    // pattern 22
    static void spiralNumberSquareWithoutExtraSpace(int n) {
        int sz = 2*n - 1;
        // find min distance from the center of square
        // center co-ordinate (n-1, n-1)
        for(int i=0; i<sz; i++) {
            for(int j=0; j<sz; j++) {
                int a = Math.abs(n-1-i);
                int b = Math.abs(n-1-j);
                int max = Math.max(a, b);
                System.out.print(max+1);
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
        // alphabetTriangle(7);
        // reverseAlphabetTriangle(5);
        // hollowDiamond(6);
        // horizontalDamroo(5);
        // hollowRectangle(5);
        // spiralNumberSquare(3);
        spiralNumberSquareWithoutExtraSpace(4);
    }
}