public class List<T> {

    public Node<T> ls;

    public List () {
        ls = new Node (null, ls);
    }

    public void add (T o) {
        Node newNode = new Node (o, ls.getNext());
        ls.setNext(newNode);
    }

    public void moveToEnd (int n) {
        Node ptr1 = ls.getNext();
        Node trailer1 = ls.getNext();
        Node ptr2 = ls.getNext();
        Node trailer2 = ls.getNext();

        while (ptr1 != null) {
            trailer1 = ptr1;
            ptr1 = ptr1.getNext();
        }

        int count = 1;
        while (ptr2 != null && count <= n) {
            trailer2 = ptr2;
            ptr2 = ptr2.getNext();
            count++;
        }
        if (ptr2 != null) {
            trailer1.setNext(ls.getNext());
            ls.setNext(ptr2);
            trailer2.setNext(null);
        }
    }

    public void print() {
        Node ptr = ls.getNext();
        while (ptr != null) {
            System.out.println(ptr.getData());
            ptr = ptr.getNext();
        }
    }

    public static void main (String[] args) {
        List <Integer> testList = new List <> ();
        testList.add(0);
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.add(5);
        testList.print();
        System.out.println();
        testList.moveToEnd(4);
        testList.print();
        System.out.println();

    }
}
