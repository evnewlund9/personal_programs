//Evan Newlund, ID: 5473869, X-500: newlu004

import java.util.Scanner;
import java.util.Random;

public class PList implements PListInterface {

    private ObjectNode start;
    private ObjectNode header;

    //Creates a header node in addition to start node as per the style of "headed" linked lists
    public PList () {
        header = new ObjectNode("", null);
        start = new ObjectNode("", header);
    }

    //Displays the various methods in PList
    public static void main (String[] args) {
        PList testList = new PList();
        testList.add(1);
        testList.add(0);
        testList.add(2);
        testList.add(0);
        testList.add(3);
        testList.add(1);
        testList.add(2);
        testList.add(4);
        testList.add(0);
        testList.add(4);
        testList.print();
        testList.removeDuplicates();
        testList.print();




//        PList testList = new PList();
//        boolean shouldExit = false;
//        System.out.println("An empty PList has been created.");
//        while (! shouldExit) { //Continues asking for input until user types 'exit'
//            System.out.println("Choose one of the following methods or type 'exit' to exit: ");
//            System.out.println("add, append, create, delete, get, insert, length, print, remove, removeEvery, sort");
//            Scanner s = new Scanner(System.in);
//            String input = s.next();
//            input = input.toLowerCase(); //Accounts for capital letters
//            switch (input) {
//                case "add":
//                    System.out.println("What String would you like to add?");
//                    testList.add(s.next());
//                    break;
//
//                case "append":
//                    System.out.println("What String would you like to append?");
//                    testList.append(s.next());
//                    break;
//
//                case "create":
//                    testList.create();
//                    System.out.println("The current PList has been replaced with a new, empty PList.");
//                    break;
//
//                case "delete":
//                    System.out.println("From what index do you wish to delete a list object?");
//                    testList.delete(s.nextInt());
//                    break;
//
//                case "get":
//                    System.out.println("From what index do you wish to get a list entry?");
//                    try { //Try block is necessary because get() catches (from getNode()) and then throws a runtime exception
//                        System.out.println(testList.get(s.nextInt()));
//                    }
//                    catch (RuntimeException e) {
//                        System.out.println("Index is not valid.");
//                    }
//                    break;
//
//                case "insert":
//                    System.out.println("What would you like to add to the PList?");
//                    String item = s.next();
//                    System.out.println("Where should it be inserted?");
//                    int index = s.nextInt();
//                    testList.insert(item, index);
//                    break;
//
//                case "length": //Since length() only returns the length as an int, the switch statement uses a print statement
//                    System.out.println(testList.length());
//                    break;
//
//                case "print":
//                    testList.print();
//                    break;
//
//                case "remove":
//                    System.out.println("What do you want to remove from the list?");
//                    testList.remove(s.next());
//                    break;
//
//                case "removeEvery":
//                    System.out.println("Choose an index multiple to be removed from the list: ");
//                    testList.removeEvery(s.nextInt());
//                    break;
//
//                case "sort":
//                    testList.sort();
//                    break;
//
//                case "exit":
//                    shouldExit = true;
//                    break;
//
//                default: //If the input doesn't match any of the above methods, the switch statement assumes that the user made an error
//                    System.out.println("Not a valid method. Try again or type 'exit' to exit: ");
//            }
//        }
    }

    //Adds an object to the front of the PList, right after the header
    public void add (Object item) {
        ObjectNode newNode = new ObjectNode(item, header.getNext()); //Creates a new node that points to whatever the header was pointing at
        header.setNext(newNode); //Sets the 'next' field of the header to the new node
    }

    //Adds an object to the end of the PList
    public void append (Object item) {
        ObjectNode lastNode = getNode(length() - 1); //Makes the last node point to the new node and the new node point to null
        lastNode.setNext(new ObjectNode(item, null)); // The new node always points to null since it's at the end of the list
    }

    //Adds one PList to the other
    // The PList that calls this method is the one being mutated, not the "other" Plist
    public void concatenate (PListInterface plist) {
        ObjectNode lastNode = getNode(length() - 1);
        for (int i = 0; i < plist.length(); i++) {
            append(plist.get(i)); //Each object from the "other" list is added, one by one, to the end of the current list
        }
    }

    //Creates a new PList and replaces (in a way) the current PList with the new one
    public void create () {
        PList newPList = new PList(); //Since the current list cannot be changed (something like 'this = new PList()' is not valid),
        header = newPList.header; //The method simply sets the start and header nodes of the new list to the current ones.
        start = newPList.start; //This effectively deletes the data in the current list since the header node now points to null instead of the first node in the list
    }

    //Removes the object at the specified index from the list
    public void delete (int index) {
        if (index < length() && index >= 0) { //Checks that the index is valid
            ObjectNode ptr, trailer;
            trailer = header; //Uses a trailer node because, once the node is found, the previous node must be altered
            ptr = header.getNext();
            while (! ptr.equals(getNode(index))) {
                trailer = ptr;
                ptr = ptr.getNext();
            }
            trailer.setNext(ptr.getNext()); //Simply makes the previous node point to the one after the node being deleted, skipping this node entirely
        }                                  //With nothing pointing at the specified node, it is marked for garbage collection and is deleted from the list
        else { //Methods that don't return anything have the benefit of simply being able to print "index is not valid" instead of throwing an error
            System.out.println("Index is not valid.");
        }
    }

    //Returns the node at a specified index
    //This method was not required, but I added it avoid repeating code
    // I found that being able to index the nodes in the list, not just the objects in it (get()), was quite useful
    //I spoke with a TA who said that this was okay since I would be using this exact code anyways throughout the program
    public ObjectNode getNode (int index) throws RuntimeException {
        if (index < length() && index >= 0) { //Checks that the index is valid
            ObjectNode ptr = header.getNext(); //Starts at the first node after header (doesn't include the header node when iterating)
            int i = 0;
            while (ptr != null && i < index) {
                ptr = ptr.getNext();
                i++;
            }
            return ptr;
        }
        else { //Since the method returns an ObjectNode, the method must throw and error if the index is not valid
            throw new RuntimeException("Index is out of bounds.");
        }
    }

    //Returns the data item at a given index
    //Simply utilizes the previous method to find the node at a given index and then accesses its data
    public Object get (int index) throws RuntimeException {
        try {
            return getNode(index).getData();
        }
        catch (RuntimeException e) { //Cannot simply print "index is not valid" like other methods (must throw an error) since it must return an object
            throw new RuntimeException("Index is not valid.");
        }
    }

    //Places an item at the specified index
    public void insert (Object item, int index) {
        if (index < length() && index > 0) { //Doesn't need try block around getNode() because the index validity is already verified with this line
            ObjectNode newNode = new ObjectNode(item, getNode(index)); //Makes new node point to the node currently at the specified index
            getNode(index - 1).setNext(newNode); //Makes the previous node point to the new node
        }
        else if (index > length()) {
            append(item); //If the specified index is larger than the size of the list, the object is simply added onto the end of the list
        }
        else if (index < 0) {
            System.out.println("Index is not valid.");
        }
    }

    //Returns the length of the PList as an integer
    public int length () {
        ObjectNode ptr = header.getNext(); //Starts at the first node after header (doesn't include the header node when counting)
        int count = 0;
        while (ptr != null) { //Iterates through the list until it hits the end, counting as it goes
            count++;
            ptr = ptr.getNext();
        }
        return count;
    }

    //Prints the PList by calling the toString() methods that converts the list into a string
    public void print () {
        System.out.println(toString());
    }

    //Removes a specified item from the PList
    //Similar to delete() except the user specifies the object they wish to remove, not the index that the object is at
    public void remove (Object item) {
        boolean inList = false;
        for (int i = 0; i < length(); i++) {
            String currentObject = (String) get(i); //Doesn't need try block because i will never exceed the bounds of the list
            String stringItem = (String) item;
            if (currentObject.equals(stringItem)) { //Once the method finds the item in the list, it breaks out of the loop and sets inList to true
                getNode(i - 1).setNext(getNode(i + 1));
                inList = true;
                break;
            }
        }
        if (! inList) { //After the for loop completes, if inList is never changed to true, the method prints this message
            System.out.println("There is no such item in the list to delete.");
        }
    }

    public void removeEvery (int n) {
        if (!(n > length()) && !(n<=0)) {
            ObjectNode ptr = header;
            ObjectNode trailer = header;
            while (true) {
                int count = 0;
                while (ptr != null && count < n) {
                    trailer = ptr;
                    ptr = ptr.getNext();
                    count++;
                }
                if (ptr != null) {
                    trailer.setNext(ptr.getNext());
                }
                else {
                    break;
                }
            }
        }
    }

    public void removeDuplicates () {
        ObjectNode ptr = header;
        ObjectNode ptr2;
        ObjectNode trailer;
        ptr = ptr.getNext();
        while (ptr != null) {
            int currentObject = (int) ptr.getData();
            ptr2 = ptr;
            trailer = ptr2;
            ptr2 = ptr2.getNext();
            while (ptr2 != null) {
                int potentialDuplicate = (int) ptr2.getData();
                if (currentObject == potentialDuplicate) {
                    trailer.setNext(ptr2.getNext());
                }
                trailer = ptr2;
                ptr2 = ptr2.getNext();
            }
            ptr = ptr.getNext();
        }
        ptr = header;
        ptr = ptr.getNext();
        while (ptr != null) {
            if ((int) ptr.getData() == (int) get(length() - 1)) {
                getNode(length() - 2).setNext(null);
            }
            ptr = ptr.getNext();
        }
    }

    //Uses a bubble sort algorithm to sort the list objects from smallest to largest based on their toString() values
    public void sort () {
        boolean swapped = true; //Checks that a loop has swapped two objects at least once. Set to true so that the while loop will run
        while (swapped) { //Continues until a for loop is run in which no objects change position (meaning the list is fully sorted)
            swapped = false; //Sets swapped to false at the start of each for loop
            for (int i = 1; i < length(); i++) {
                String firstString = get(i - 1).toString(); //Doesn't need try block around get() because i will never exceed the bounds of the list
                String secondString = get(i).toString();
                if (firstString.length() > secondString.length()) { //The sort algorithm is stable because two objects won't switch places if they have the same length (relative positions are maintained)
                    Object temp = getNode(i).getData(); //Swaps the first node with the second by using a temporary variable
                    getNode(i).setData(getNode(i - 1).getData());
                    getNode(i - 1).setData(temp);
                    swapped = true; //Once two objects are swapped, this boolean variable is set to true, meaning that another for loop iteration will trigger after the current one completes
                }
            }
        }
    }

    //Boolean method that determines if two PLists are equivalent in their data
    //Doesn't check memory addresses (they don't have to literally be the same PList object) but, rather, if all their data is equivalent
    //Since it checks the objects toString() values this means that a list containing the string '3' is considered equal to a list containing the integer 3
    public boolean equals (PList other) {
        boolean isEqual = false;
        if (length() == other.length()) {
            isEqual = true;
            for (int i = 0; i < length(); i++) { //Doesn't need try block around get() because i will never exceed the bounds of the list
                if (! get(i).toString().equals(other.get(i).toString())) { //Checks that the string values of two objects are equivalent (doesn't check their memory addresses)
                    isEqual = false;
                    break;
                }
            }
        }
        return isEqual;
    }

    //Converts a PList to a string
    public String toString () {
        String listString = "";
        if (length() != 0) { //Checks that the list is not empty
            for (int i = 0; i < length() - 1; i++) {
                listString = listString + get(i).toString() + ", "; //Adds commas after all the objects except the last
            }
            listString = listString + get(length() - 1).toString(); //Special case; adds the last object to the string without a comma
            return listString;
        }
        else {
            return "PList is empty.";
        }
    }
} //PList class
