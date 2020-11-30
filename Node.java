public class Node {
    int a, b, c;

    public Node(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public void Fill(String jug, int capacity) {
        if (jug.equals("A")) {
            this.a = capacity;
        } else if (jug.equals("B")) {
            this.b = capacity;
        } else {
            this.c = capacity;
        }


    }

    public void Empty(String jug) {
        if (jug.equals("A")) {
            this.a = 0;
        } else if (jug.equals("B")) {
            this.b = 0;
        } else {
            this.c = 0;
        }
        //return ("("+this.a+", "+this.b+", "+this.c+")");
    }

    public void Pour(String fromJug, String toJug, int toJugCapacity) {
        if (fromJug.equals("A") && toJug.equals("B")) {
            if (this.b+this.a <= toJugCapacity) {
                this.b += this.a;
                this.a = 0;
            } else {
                this.a -= (toJugCapacity - this.b);
                this.b = toJugCapacity;
            }
        } else if (fromJug.equals("A") && toJug.equals("C")) {
            if (this.c + this.a <= toJugCapacity) {
                this.c += this.a;
                this.a = 0;
            } else {
                this.a -= (toJugCapacity - this.c);
                this.c = toJugCapacity;
            }
        } else if (fromJug.equals("B") && toJug.equals("C")) {
            if (this.c+this.b <= toJugCapacity) {
                this.c += this.b;
                this.b = 0;
            } else {
                this.b -= (toJugCapacity - this.c);
                this.c = toJugCapacity;
            }
        } else if (fromJug.equals("B") && toJug.equals("A")) {
            if (this.a+this.b <= toJugCapacity) {
                this.a += this.b;
                this.b = 0;
            } else {
                this.b -= (toJugCapacity - this.a);
                this.a = toJugCapacity;
            }
        } else if (fromJug.equals("C") && toJug.equals("B")) {
            if (this.b+this.c <= toJugCapacity) {
                this.b += this.c;
                this.c = 0;
            } else {
                this.c -= (toJugCapacity - this.b);
                this.b = toJugCapacity;
            }
        } else if (fromJug.equals("C") && toJug.equals("A")) {
            if (this.a+this.c <= toJugCapacity) {
                this.a += this.c;
                this.c = 0;
            } else {
                this.c -= (toJugCapacity - this.a);
                this.a = toJugCapacity;
            }
        }
        //return ("("+this.a+", "+this.b+", "+this.c+")");
    }
}
