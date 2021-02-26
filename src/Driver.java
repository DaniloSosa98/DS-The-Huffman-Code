
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String message;
        ArrayList<Character> chars = new ArrayList();
        ArrayList<Integer> freq = new ArrayList();

        System.out.println("Insert the message: ");
        message = sc.nextLine();

        //FOR YOU TO TRY ANY MESSAGE
        //message = "SUSIE SAYS IT IS EASY";
        //System.out.println(message);
        
        //PROCESS OF GETTING AL CHARACTERS AND FREQUENCIES
        for (int i = 0; i < message.length(); i++) {
            char c;
            int count = 0;
            if (!chars.contains(message.charAt(i))) {
                c = message.charAt(i);
                chars.add(c);
                for (int j = 0; j < message.length(); j++) {
                    if (c == message.charAt(j)) {
                        count++;
                    }
                }
                freq.add(count);
            }
        }

        //GETTING THE AMOUNT OF LINES
        chars.add('\n');
        freq.add(getLineCount(message));

        //PRINT CHARACTERS AND FREQUENCIES
        print(freq);
        print(chars);

        //CREATE TREE
        Tree t = new Tree(freq, chars);

        //PREORDER PRINT
        if (message.length() < 15) {
            System.out.println("Huffman Tree:");
            Node current = t.root;
            t.printTree(current);
            System.out.println();
        }

        //ENCODE
        System.out.println("Encode: " + message + "\n" + t.encode(message) + "\n");

        //DECODE
        System.out.println("Decode: " + t.encode(message) + "\n" + t.decode(t.encode(message)));

    }

    public static int getLineCount(String message) {

        return message.split("[\n|\r]").length;
    }

    public static void print(ArrayList a) {
        for (int i = 0; i < a.size(); i++) {
            System.out.print(a.get(i) + " ");
        }
        System.out.println();
    }
}
