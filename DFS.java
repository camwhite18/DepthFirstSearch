import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class DFS {
    public static int A;
    public static int B;
    public static int C;
    public static HashSet<String> Visited = new HashSet<>();
    public static Stack<Node> TraveledPast = new Stack<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter the capacity of water-jug A: ");
            A = scanner.nextInt();
        } while (A < 0);
        do {
            System.out.print("Enter the capacity of water-jug B: ");
            B = scanner.nextInt();
        } while (B < 0);
        do {
            System.out.print("Enter the capacity of water-jug C: ");
            C = scanner.nextInt();
        } while (C < 0);

        Node treeRoot = new Node(0, 0, 0);
        Visited.add("(0, 0, 0)");
        TraveledPast.push(treeRoot);

        while (!TraveledPast.isEmpty()) {
            Node currentNode = TraveledPast.pop();
            int a = currentNode.a;
            int b = currentNode.b;
            int c = currentNode.c;

            System.out.println("( " + a + " , " + b + " , " + c + " )");

            //At each stage there are 12 operations that can be done:
            //Fill a, Fill b, Fill c, Empty a, Empty b, Empty c, Pour a into b, Pour a into c, Pour b into a,
            //Pour b into c, Pour c into a, Pour c into b

            if (a > 0) {                 // check if jugs a is not empty

                //pour a into b first
                if ((a + b) <= B) {
                    // empty a into b
                    childNode(0,a + b, c);
                } else {
                    childNode((a + b - B), B, c);   //leave the remain water in A

                }
                //pour a into c first
                if ((a + c) <= C) {
                    // empty a into c
                    childNode(0, b, a + c);
                } else {
                    childNode(a + c - C, b, C);

                }
                //Empty A
                childNode(0, b, c);


            }
            // pour b into a|c
            if (b > 0) {                // check if b has water

                //pour b into A then into C
                if ((a + b) <= A) {
                    // empty b into a
                    childNode(a + b, 0, c);
                } else {
                    childNode(A, a + b - A, c);  // leave the remain in
                }

                //pour b into C then into A
                if ((b + c) <= C) {
                    childNode(a, 0, b + c);
                } else {
                    childNode(a, b + c - C, C);
                }

                //Empty B
                childNode(a,0,c);

            }
            if (c > 0) {     //check if c is not empty
                //System.out.println("C scoop");
                //pour C into B then into A
                if ((c + b) <= B) {
                    //empty c into B
                    childNode(a, b + c, 0);
                } else {
                    childNode(a, B, b + c - B);
                }

                //pour C into A then into B
                if ((c + a) <= A) {
                    //empty C into A
                    childNode(a + c, b, 0);
                } else {
                    childNode(A, b, a + b - C);
                }

                //Empty C
                childNode(a,b,0);

            }

            //fill A
            if(a<A)
                childNode(A,b,c);

            //fill B
            if(b<B)
                childNode(a,B,c);

            //fill C
            if(c<C)
                childNode(a,b,C);

            }
        }


    public static void childNode(int a, int b, int c) {
        if (a <= A && b <= B && c <= C && a >= 0 && b >= 0 && c >= 0) {
            Node child = new Node(a, b, c);
            if (!Visited.contains("(" + a + ", " + b + ", " + c + ")")) {
                TraveledPast.push(child);
                Visited.add("(" + a + ", " + b + ", " + c + ")");
            }
        }
    }
}