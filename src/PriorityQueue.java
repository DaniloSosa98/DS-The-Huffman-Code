
public class PriorityQueue {

    private int maxSize = 50;
    private Node[] queArray;
    private int nItems;

    public PriorityQueue() {

        queArray = new Node[maxSize];
        nItems = 0;
    }

    public void insert(Node item) {

        int j;

        if (nItems == 0) {

            queArray[nItems++] = item;

        } else {

            for (j = nItems - 1; j >= 0; j--) {

                if (item.compareTo(queArray[j]) > 0) {

                    queArray[j + 1] = queArray[j];

                } else {

                    break;
                }
            }
            queArray[j + 1] = item;
            nItems++;
        }
    }

    public Node remove() {

        return queArray[--nItems];
    }

    public Node peekMin() {

        return queArray[nItems - 1];
    }

    public boolean isEmpty() {

        return (nItems == 0);
    }

    public boolean isFull() {

        return (nItems == maxSize);
    }

    public int size() {
        return nItems;
    }
}
