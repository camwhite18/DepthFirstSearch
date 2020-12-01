import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class DepthFirstSearch {
    public static int A;
    public static int B;
    public static int C;
    public static HashSet<String> Visited = new HashSet<>();
    public static Stack<Node> TraveledPast = new Stack<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Get the capacities of the jugs
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
            System.out.println("(" + a + ", " + b + ", " + c + ")");

            //From each node there are 12 operations that can be done:
            //Fill a, Fill b, Fill c, Empty a, Empty b, Empty c, Pour a into b, Pour a into c, Pour b into a,
            //Pour b into c, Pour c into a, Pour c into b

            if (a > 0) {
                //Pour a into b
                childNode(pour("A", "B", a, b, c));
                //Pour a into c
                childNode(pour("A", "C", a, b, c));
                //Empty a
                childNode(empty("A", a, b, c));
            }
            if (b > 0) {
                //Pour b into a
                childNode(pour("B", "A", a, b, c));
                //Pour b into c
                childNode(pour("B", "C", a, b, c));
                //Empty b
                childNode(empty("B", a, b, c));
            }
            if (c > 0) {
                //Pour c into b
                childNode(pour("C", "B", a, b, c));
                //Pour c into a
                childNode(pour("C", "A", a, b, c));
                //Empty c
                childNode(empty("C", a, b, c));
            }
            //Fill a
            if(a<A) {
                childNode(fill("A", a, b, c));
            }
            //Fill b
            if(b<B) {
                childNode(fill("B", a, b, c));
            }
            //Fill c
            if(c<C) {
                childNode(fill("C", a, b, c));
            }
            }
        }


    public static void childNode(Node newNode) {
        int a = newNode.a;
        int b = newNode.b;
        int c = newNode.c;
        Node child = new Node(a, b, c);
        if (!Visited.contains("(" + a + ", " + b + ", " + c + ")")) {
            TraveledPast.push(child);
            Visited.add("(" + a + ", " + b + ", " + c + ")");
        }
    }

    public static Node fill(String jug, int a, int b, int c) {
        Node newNode;
        if (jug.equals("A")) {
            newNode = new Node(A, b, c);
        } else if (jug.equals("B")) {
            newNode = new Node(a, B, c);
        } else {
            newNode = new Node(a, b, C);
        }
        return newNode;
    }

    public static Node empty(String jug, int a, int b, int c) {
        Node newNode;
        if (jug.equals("A")) {
            newNode = new Node(0, b, c);
        } else if (jug.equals("B")) {
            newNode = new Node(a, 0, c);
        } else {
            newNode = new Node(a, b, 0);
        }
        return newNode;
    }

    public static Node pour(String fromJug, String toJug, int a, int b, int c) {
        Node newNode = null;
        if (fromJug.equals("A") && toJug.equals("B")) {
            if (b+a <= B) {
                b += a;
                a = 0;
            } else {
                a -= (B - b);
                b = B;
            }
            newNode = new Node(a, b, c);
        } else if (fromJug.equals("A") && toJug.equals("C")) {
            if (c + a <= C) {
                c += a;
                a = 0;
            } else {
                a -= (C - c);
                c = C;
            }
            newNode = new Node(a, b, c);
        } else if (fromJug.equals("B") && toJug.equals("C")) {
            if (c+b <= C) {
                c += b;
                b = 0;
            } else {
                b -= (C - c);
                c = C;
            }
            newNode = new Node(a, b, c);
        } else if (fromJug.equals("B") && toJug.equals("A")) {
            if (a+b <= A) {
                a += b;
                b = 0;
            } else {
                b -= (A - a);
                a = A;
            }
            newNode = new Node(a, b, c);
        } else if (fromJug.equals("C") && toJug.equals("B")) {
            if (b+c <= B) {
                b += c;
                c = 0;
            } else {
                c -= (B - b);
                b = B;
            }
            newNode = new Node(a, b, c);
        } else if (fromJug.equals("C") && toJug.equals("A")) {
            if (a+c <= A) {
                a += c;
                c = 0;
            } else {
                c -= (A - a);
                a = A;
            }
            newNode = new Node(a, b, c);
        }
        return newNode;
    }
}