public class SparseVector {

	private Node head;
	private int length;

	public SparseVector(int len){
		head = new Node (-1,0.00);
		length = len;
	}

	// Prints out a sparse vector (including zeros)
	public String toString(){

		String result = "";
		Node currNode = head.getNext();
		int currIndex = 0;
		while( currNode != null ){
			int idx = currNode.getIndex();

			// Pad the space between nodes with zero
			while( currIndex < idx ){
				result += "0, ";
				currIndex++;
			}
			result += currNode;
			currNode = currNode.getNext();
			currIndex++;

			// Only add a comma if this isn't the last element
			if( currNode != null ){ result += ", "; }
		}
		return result;
	}

	// TODO: Implement me for milestone 1
	public void addElement(int index, double value){
		if (index < length) {
			Node ptr, trailer;
			ptr = head.getNext();
			trailer = head;

			while (ptr != null && ptr.getIndex() < index) {
				trailer = ptr;
				ptr = ptr.getNext();
			}
			trailer.setNext(new Node(index, value));
			Node newNode = trailer.getNext();
			if (ptr != null) {
				newNode.setNext(ptr);
			}
		}
		else{
			System.out.println("Not a valid index.");
		}
	}

	// TODO: Implement me for milestone 2
	public static double dot(SparseVector x, SparseVector y) throws RuntimeException{
		double sum = 0.0;
		if (x.length == y.length) {
			Node xPtr, yPtr;
			xPtr = x.head.getNext();
			yPtr = y.head.getNext();
			int i = 0;
			while (xPtr != null && yPtr != null && i < x.length) {
			    if (xPtr.getIndex() == i && yPtr.getIndex() == i) {
                    sum += (xPtr.getValue() * yPtr.getValue());
                    xPtr = xPtr.getNext();
                    yPtr = yPtr.getNext();
                }
			    else if (xPtr.getIndex() == i){
                    xPtr = xPtr.getNext();
                }
			    else if (yPtr.getIndex() == i){
			        yPtr = yPtr.getNext();
                }
			    i++;
			}
            return sum;
		}
		else{
			throw new RuntimeException("Vectors must be the same size.");
		}
	}

	public void removeElement(int index){

	}

	// TODO: Test out your code here!
	public static void main(String[] args) {
        SparseVector x = new SparseVector(100000000);
        x.addElement(0, 1.0);
        x.addElement(10000000, 3.0);
        x.addElement(10000001, -2.0);
        SparseVector y = new SparseVector(100000000);
        y.addElement(0, 2.0);
        y.addElement(10000001, -4.0);
        double result = dot(x, y);
        System.out.println(result);

        //Shows scenario with one empty vector
        SparseVector a = new SparseVector (8);
        a.addElement(0,3.0);
        a.addElement(3,9.0);
        SparseVector b = new SparseVector(8);
        result = dot(a, b);
        System.out.println(result);

        //Shows scenario with two vectors of different length
        SparseVector c = new SparseVector (5);
        c.addElement(0,5.0);
        c.addElement(3,7.0);
        SparseVector d = new SparseVector(6);
        d.addElement(0,4.0);
        d.addElement(3,8.0);
        result = dot(c, d);
        System.out.println(result);

    }
}


