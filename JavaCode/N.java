// N.java
// A *simplified* node class for use with the Stack1 class 
// and other uses as desired
// Posted previously, but used for simulation

public class N {

    private Object data;
    private N next;


    public N () {}

    public N (Object o, N link) {
        data = o;
        next = link;
    }

    public Object getData () {
        return data;
    }

    public void setData (Object o) {
        data = o;
    }

    // instance variables

    public N getNext () {
        return next;
    }

    public void setNext (N link) {
        next = link;
    }
}  // N class
