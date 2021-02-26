
import java.util.ArrayList;

public class Tree {

    public Node root = new Node();
    private int size = 0;
    private PriorityQueue pq = new PriorityQueue();
    ArrayList<String> pathTable = new ArrayList();
    ArrayList<Character> valueTable = new ArrayList();

    public Tree(ArrayList freq, ArrayList chars) {

        size = chars.size();
        for (int i = 0; i < freq.size(); i++) {
            pq.insert(new Node((char) chars.get(i), (int) freq.get(i), null, null, null));
        }

        HuffmanTree();

        createTable(this.root, "");
    }

    private void HuffmanTree() {

        while (pq.size() > 1) {
            //Assign the 2 chars with the smallest freq
            Node tempL = pq.remove();
            Node tempR = pq.remove();

            //Create a tree with parent whose weight is the sum of it's childs freq
            Node Parent = new Node(0, tempL.freq + tempR.freq, tempL, tempR, null);
            tempL.Parent = Parent;
            tempR.Parent = Parent;

            //Insert new tree to the PriorityQueue
            pq.insert(Parent);
            this.size++;
        }

        //Set the remaining Node as root
        this.root = pq.peekMin();
    }

    private void createTable(Node curr, String str) {

        if (curr == null) {
            return;
        }

        //If curr is a leaf node, add path and value
        if (curr.leftNode == null && curr.rightNode == null) {

            char tempChar;

            if (curr.value == 32) {

                tempChar = ' ';

            }

            if (curr.value == 10) {

                tempChar = '\n';

            } else {

                tempChar = (char) curr.value;

            }

            //Value and path to code tables
            this.valueTable.add(tempChar);
            this.pathTable.add(str);
        }

        //Add 0 before moving to left child
        str += "0";
        //PreOrder Recursion
        createTable(curr.leftNode, str);

        //Adjust path and add 1 before moving to right child
        str = str.substring(0, str.length() - 1);
        str += "1";
        createTable(curr.rightNode, str);
    }

    String level = "";

    public void printTree(Node curr) {

        if (curr == null) {
            return;
        }

        //If leaf, display level, freq, and value
        if (curr.leftNode == null && curr.rightNode == null) {
            //To display new line or space
            switch (curr.value) {
                case 32:
                    System.out.println(level + curr.freq + ": sp");
                    break;
                case 10:
                    System.out.println(level + curr.freq + ": lf");
                    break;
                default:
                    System.out.println(level + curr.freq + ": " + (char) curr.value);
                    break;
            }
        } //Else display level and freq
        else {
            System.out.println(level + curr.freq);
        }

        //Increment level marker
        level += "- ";
        //Recursive PreOrder
        printTree(curr.leftNode);
        printTree(curr.rightNode);
        //Decrement level marker
        level = level.substring(0, level.length() - 2);
    }

    public int getSize() {
        return this.size;
    }

    public String encode(String message) {
        //Create empty string to hold code
        String bits = "";

        //Iterate through given string
        for (int i = 0; i < message.length(); i++) {
            //Iterate through code tables
            for (int j = 0; j < valueTable.size(); j++) {
                //If char in string matches code in table, add path to string
                if (valueTable.get(j) == message.charAt(i)) {
                    bits += pathTable.get(j);
                }
            }
        }
        return bits;
    }

    public String decode(String bits) {
        //Create empty string to hold decoded message
        String message = "";

        //Iterate through bits
        for (int i = 0; i < bits.length(); i++) {
            if (!getChar(bits.substring(0, i + 1)).equals("")) {
                message += getChar(bits.substring(0, i + 1));
                bits = bits.substring(i + 1);
                i = 0;
            }
        }
        return message;
    }

    private String getChar(String bits) {
        //Create string to hold potential character
        String character = "";
        //Traverse code table to seek match
        for (int i = 0; i < pathTable.size(); i++) {
            //Add to string if match is found
            if (pathTable.get(i).equals(bits)) {
                character = valueTable.get(i).toString();
            }
        }
        return character;
    }
}
