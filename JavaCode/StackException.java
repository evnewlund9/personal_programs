public class StackException extends Exception {

    private int size;

    StackException (int size) {
        this.size = size;
    }

    int getSize () {
        return size;
    }
}
