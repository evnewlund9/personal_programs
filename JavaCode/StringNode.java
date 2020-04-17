//Quick linked list class created to assist HashTable class
//Doesn't use generics because the HashTable only accommodates strings
public class StringNode {

    private String data;
    private StringNode next;

    public StringNode (String data) {
        this.data = data;
        this.next = null;
    }

    public String getData () {
        return data;
    }

    public StringNode getNext () {
        return next;
    }

    public void setNext (StringNode next) {
        this.next = next;
    }
}
