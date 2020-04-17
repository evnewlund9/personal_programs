public class BinaryTree<K extends Comparable<K>, V> {

    private Node<K, V> root;

    public BinaryTree (Node root) {
        this.root = root;
    }

    public static void main (String args[]) {
        //Tree given for testing on
        BinaryTree<Integer, String> p1Tree = new BinaryTree<>(new Node<>(40, "forty",
                new Node<>(20, "twenty",
                        new Node<>(10, "ten"), new Node<>(30, "thirty")),
                new Node<>(60, "sixty",
                        new Node<>(50, "fifty"), new Node<>(70, "seventy"))));
        BinaryTree<Integer, String> p2Tree = new BinaryTree <> (new Node<>(60, "sixty",
                        new Node<>(50, "fifty"), new Node<>(70, "seventy")));

        Object[] array = p1Tree.flatten();
        for (Object item : array) {
            System.out.println(item);
        }
        System.out.println(p1Tree.containsSubtree(p2Tree));
    }

    public Node<K, V> getRoot () {
        return this.root;
    }

    public void add (K key, V value) {
        if (this.root == null) {
            this.root = new Node<K, V>(key, value);
        }
        else {
            this.root.add(key, value);
        }
    }

    public void printInOrder (Node node) {
        if (node.getLeft() == null && node.getRight() == null) {
            System.out.print(node.getValue() + " ");
        }
        else {
            printInOrder(node.getLeft());
            System.out.print(node.getValue() + " ");
            printInOrder(node.getRight());
        }
    }

    public void printPreOrder (Node node) {
        if (node.getLeft() == null && node.getRight() == null) {
            System.out.print(node.getValue());
        }
        else {
            System.out.print(node.getValue() + " ");
            printPreOrder(node.getLeft());
            printPreOrder(node.getRight());
        }
    }

    public void printPostOrder (Node node) {
        if (node.getLeft() == null && node.getRight() == null) {
            System.out.print(node.getValue());
        }
        else {
            printPostOrder(node.getLeft());
            printPostOrder(node.getRight());
            System.out.print(node.getValue() + " ");
        }
    }

    @SuppressWarnings("unchecked")
    public V[] flatten () {
        int numberOfNodes = getNumberOfNodes(root);
        V[] flattenedTree = (V[]) new Object[numberOfNodes];
        convertToArray(root,flattenedTree,0);
        return flattenedTree;
    }

    public int convertToArray (Node node, V[] array, int i) {
        if (node.getLeft() == null && node.getRight() == null) {
            array[i] = (V) node.getValue();
        }
        else {
            i = convertToArray(node.getLeft(),array, i);
            array[++i] = (V) node.getValue();
            convertToArray(node.getRight(), array, ++i);
        }
        return i;
    }

    public int getNumberOfNodes (Node node) {
        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        }
        else if (node.getLeft() == null) {
            return 1 + getNumberOfNodes(node.getRight());
        }
        else if (node.getRight() == null) {
            return 1 + getNumberOfNodes(node.getLeft());
        }
        else {
            return 1 + getNumberOfNodes(node.getLeft()) + getNumberOfNodes(node.getRight());
        }
    }

    public boolean containsSubtree (BinaryTree<K, V> other) {
        if (other == null) {
            return true;
        }
        Object[] thisTree = flatten();
        Object[] otherTree = other.flatten();
        int i = 0;
        while (i < thisTree.length) {
            String item = (String) thisTree[i];
            String otherRoot = (String) otherTree[0];
            if (item.equals(otherRoot)) {
                break;
            }
            i++;
        }
        if (i != thisTree.length) {
            int j = 0;
            while (j < otherTree.length) {
                if (!(thisTree[i + j].equals(otherTree[j]))) {
                    return false;
                }
                j++;
            }
            return true;
        }
        else {
            return false;
        }
    }

}
