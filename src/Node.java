
public class Node implements Comparable<Node> {

    int value;
    int freq;
    Node leftNode;
    Node rightNode;
    Node Parent;

    public Node() {
        Parent = null;
    }

    public Node(int value, int freq, Node leftNode, Node rightNode, Node Parent) {
        this.value = value;
        this.freq = freq;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.Parent = Parent;
    }

    @Override
    public int compareTo(Node n) {
        if (freq - n.freq > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        String str = "";
        str += this.value;
        return str;
    }

}
