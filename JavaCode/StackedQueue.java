public class StackedQueue <T> {

    private Stack1Gen <T> stack;

    public static void main (String[] args) {
        StackedQueue testQueue = new StackedQueue <String> ();
        testQueue.enqueue("Maria");
        testQueue.enqueue("Owen");
        testQueue.enqueue("Alexis");
        testQueue.enqueue("Nick");
        testQueue.print();
        testQueue.enqueue("Budger");
        testQueue.print();
    }

    StackedQueue () {
        stack = new Stack1Gen <> ();
    }

    public void enqueue (T element) {
            if (isEmpty()) {
                stack.push(element);
            }
            else {
                Stack1Gen tempStack = new Stack1Gen <T> ();
                while (!stack.isEmpty()) {
                    tempStack.push(stack.pop());
                }
                if (element.equals("Budger")){
                    if (!tempStack.isEmpty()) {
                        stack.push((T) tempStack.pop());
                    }
                }
                tempStack.push(element);
                while (!tempStack.isEmpty()) {
                    stack.push((T) tempStack.pop());
                }
            }
    }

    public T dequeue() {
        return stack.pop();
    }

    public T peek() {
        return stack.top();
    }

    public boolean isEmpty() {
        if (stack.top() == null) {
           return true;
        }
        return false;
    }

    public void print() {
        stack.print();
    }
}
