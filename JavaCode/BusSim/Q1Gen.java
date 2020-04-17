// Q1Gen.java
// Generic queue implementation using a linked list of nodes (see NGen.java)
//Provided by the CSCI 1933 Lecture Examples on canvas.umn.edu

public class Q1Gen <T> implements QGen <T> {

    private int size;
    private NGen <T> front;
    private NGen <T> rear;

    // constructor
    public Q1Gen () {}

    // selectors
    public void add(T o) {

        if (size == 0) {
          front = new NGen <T> (o, null);
          rear = front;
        }
        else {
          rear.setNext(new NGen <T> (o, null));
          rear = rear.getNext();
        }
        size++;
    }

    public T remove() {

        T answer;

        if (size == 0)
          return null;

        answer = front.getData();
        front = front.getNext();
        size--;
        if (size == 0)
          rear = null;
        return answer;
    }

    public int length() {
        return size;
    }
}  // Q1Gen class

