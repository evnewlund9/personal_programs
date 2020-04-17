//Evan Newlund, ID: 5473869, X-500: newlu004

import java.util.Scanner;

public class HashTable {

    private StringNode[] hashTable; //The most essential part of the HashTable class. Linked lists are used (instead of strings) to handle collided elements
    private int mostCollisions;
    private int leastCollisions;


    public HashTable (int size) {
        hashTable = new StringNode[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = new StringNode(" "); //Blank spaces in the hashtable manifest as empty strings instead of null, to avoid NullPointerExceptions
        }
        mostCollisions = 0;
        leastCollisions = 100; //Least collisions must start out large so that variable will be overwritten initially
    }

    //Main driver method lets the user pick a file to get tokens from and then adds those tokens to an example hash table and displays the result
    //Afterwards, a hash table example is shown using a known data set; java keywords, specifically.
    public static void main (String[] args) {
        HashTable table = new HashTable(101); //Sample hash table where input is not known. Using a prime number for the length spreads the entries out better
        Scanner s = new Scanner(System.in);

        System.out.println("Choose a file to create a hash table from: "); //Choose from canterbury.txt, gettysburg.txt, proverbs.txt, or that_bad.txt
        String input = s.next();

        String[] tokens = TextScan.scan(input); //Modified TextScan class returns an array of tokens rather than simply printing them
        for (String token : tokens) {
            table.add(token);
        }
        table.display();

        System.out.println();
        System.out.println("Displaying example hash table where input is known: ");
        HashTable keyTable = new HashTable(50); //Since there are 50 java keywords, the minimum length a hash table (with no collisions) storing them can be is 50
        String[] keywords = TextScan.scan("keywords.txt");
        keyTable.hash2(keywords);
        keyTable.display();
    }

    //Out of all the tested hash functions, this one spread the collisions out the most
    public int hash (String token) {
        int index = 0;
        char[] arr = token.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            index = index + (int) arr[i]; //Adds up the ASCII values of each character in the token...
        }
        index = (index * 7 * arr.length) % 101; //...and the multiplies it by a prime number and the token's length to further spread out the values.
        return index;                          //The modulus operator ensures that the index will never be larger than the size of the hash table.
    } //First hash function (for unknown data sets)


    //The second hash function is specific to the keywords example
    //Thus, it simply sorts the keywords in alphabetical order before adding them to the hash table
    public void hash2 (String[] keywords) {
        String temp;
        int i = 0;
        while (i < keywords.length) {
            int j = i + 1;
            while (j < keywords.length) {
                if (keywords[i].compareTo(keywords[j]) > 0) { //Sorts keywords
                    temp = keywords[i];
                    keywords[i] = keywords[j];
                    keywords[j] = temp;
                }
                j++;
            }
            i++;
        }
        for (int k = 0; k < hashTable.length; k++) { //Adds them to the hash table
            hashTable[k] = new StringNode(keywords[k]);
        }
    }//Second hash function (for known data sets; java keywords, specifically)


    //Method that goes along with the first hash function for readability's sake
    //The first hash function was implemented as a seperate method in order to succinctly show the computational process, whereas this method does the grunt work of adding each item to the hash table
    public void add (String token) {
        int index = hash(token);

        if (hashTable[index].getData().equals(" ")) { //If there is no entry at the hashed index, then the method simply adds the string to that index
            hashTable[index] = new StringNode(token);
        }
        else {
            boolean isCopy = false; //Otherwise, the method must check to see if the string has already been added to the hash table, since duplicates are not allowed
            StringNode ptr = hashTable[index];
            while (ptr != null) {
                if (ptr.getData().equals(token)) {
                    isCopy = true;
                    break;
                }
                ptr = ptr.getNext();
            }
            if (! isCopy) {
                StringNode ptr2 = hashTable[index];
                StringNode trailer = ptr2;
                while (ptr2 != null) {
                    trailer = ptr2;
                    ptr2 = ptr2.getNext();
                }
                trailer.setNext(new StringNode(token)); //Adds string to the end of the linked list at the specified index
            }
        }
    }//add() method


    //Method used for testing hash table
    //Displays all items in the table, as well as the most and least number of collisions
    public void display () {
        for (StringNode key : hashTable) {
            if (key.getData().equals(" ")) {
                System.out.print("null"); //Displays blank indexes as "null," for readability
            }
            else {
                int count = 0;
                while (key != null) { //Iterates through all the strings at a given index
                    count++;
                    System.out.print(key.getData() + " "); //If multiple strings exist at a single index (collided strings), they are printed on a single line, separated by spaces
                    key = key.getNext();
                }
                if (count > mostCollisions) { //Overrides the current statistic for most...
                    mostCollisions = count;
                }
                if (count < leastCollisions) { //...and least collisions for the hash table
                    leastCollisions = count;
                }
            }
            System.out.print("\n");
        }
        System.out.println();
        System.out.println("Most collisions: " + mostCollisions);
        System.out.println("Least collisions: " + leastCollisions);
    } //display() method
} //HashTable class
