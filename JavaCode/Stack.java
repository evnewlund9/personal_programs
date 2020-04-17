public class Stack <T extends Comparable<T>> {

    private int howMany;
    private T[] stack;

    Stack () {
       howMany = 0;
        stack = (T[]) new Comparable [5];
    }

    Stack (int size) {
        howMany = 0;
        stack = (T[]) new Comparable [size];
    }

    T pop () throws RuntimeException {
        if (howMany == stack.length) {
            throw new RuntimeException("Cannot pop an empty stack.");
        }
        else {
            return stack[--howMany];
        }
    }

    void push (T item) {
        if (howMany == stack.length) {
            //throw new StackException (length);
        }
        else {
            stack[howMany++] = item;
        }
    }
}
