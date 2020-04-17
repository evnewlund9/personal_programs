public class BTNode {

    private BTNode left;
    private BTNode right;
    private Object data;

    public static void main (String[] args) {
        BTNode tree = new BTNode(100,new BTNode(50,new BTNode(20,new BTNode(15,null,null),new BTNode(30,new BTNode(25,null,null),new BTNode(45,null,null))),new BTNode(70,null,null)),new BTNode(150,new BTNode(120,null,null),new BTNode(170,new BTNode(160,null,null),new BTNode(180,null,null))));
        System.out.println(countLeaves(tree));
    }

    public BTNode(Object d, BTNode l, BTNode r) {
        data = d;
        left = l;
        right = r;
    }

    public BTNode getLeft() {
        return left;
    }

    public BTNode getRight() {
        return right;
    }
    public Object getData() {
        return data;
    }

    public void setLeft(BTNode l) {
        left = l;
    }

    public void setRight(BTNode r) {
        right = r;
    }

    public void setData(Object d) {
        data = d;
    }

    public static int countLeaves (BTNode tree) {
        if (tree.getLeft() == null && tree.getRight() == null) {
            return 1;
        }
        else if (tree.getLeft() == null) {
            return countLeaves(tree.getRight());
        }
        else if (tree.getRight() == null) {
            return countLeaves(tree.getLeft());
        }
        else {
            return countLeaves(tree.getLeft()) + countLeaves(tree.getRight());
        }
    }
}