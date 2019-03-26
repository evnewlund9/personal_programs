import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OwlPopulation {
  private String fileName;
  private Owl[] data;

  public void populateData() throws FileNotFoundException{
    File f = new File(fileName);
    Scanner scanner = new Scanner(f);
    int numLines = 0;
    while(scanner.hasNextLine()){
      numLines++;
      String s = scanner.nextLine();
    }
    scanner.close();
    data = new Owl[numLines];
    scanner = new Scanner(f);
    int numLines2 = 0;
    while(scanner.hasNextLine()){
      String s = scanner.nextLine();
      String[] a;
      a = s.split(",");
      data[numLines2] = new Owl(a[0], Integer.parseInt(a[1]), Double.parseDouble(a[2]));
      numLines2++;
    }
  }

  public OwlPopulation(String fileName) throws FileNotFoundException{
    this.fileName = fileName;
    populateData();
  }

  public double averageAge(){
    double total = 0;
    for(int i=0; i<data.length; i++){
      total = total + (double) data[i].getAge();
    }
    return total / data.length;
  }

  public Owl getYoungest(){
    Owl youngest = data[0];
    for(int i=1; i<(data.length-1); i++){
      if(data[i].getAge() < youngest.getAge()){
        youngest = data[i];
      }
    }
    return youngest;
  }

  public Owl getHeaviest(){
    Owl heaviest = data[0];
    for(int i=1; i<(data.length); i++){
      if(data[i].getWeight() > heaviest.getWeight()){
        heaviest = data[i];
      }
    }
    return heaviest;
  }

  public String toString(){
    return ("The youngest owl is " + (getYoungest()).getName() + " with an age of " + (getYoungest()).getAge() + "." + "\n" + "The heaviest owl is " + (getHeaviest()).getName() + " with a weight of " + (getHeaviest()).getWeight() + "." + "\n" + "The average age is " + averageAge() + ".\n");
  }

  public boolean isNewOwl(Owl other){
    for(int i = 0; i<popSize(); i++){
      if(other.equals(data[i])){
        return false;
      }
    }
    return true;
  }

  public int popSize(){
    return data.length;
  }

  public void merge(OwlPopulation other){
    int duplicates = 0;
    for(int i = 0; i<other.popSize(); i++){
      if(isNewOwl(other.data[i]) != true){
        duplicates++;
      }
    }
    Owl[] temp = new Owl[popSize() + other.popSize() - duplicates];
    for(int i = 0; i<popSize(); i++){
      temp[i] = data[i];
    }
    for(int i = 0; i<other.popSize(); i++){
      temp[i + popSize()] = other.data[i];
    }
    data = temp;
  }



  public static void main(String[] args) {
    try {
    OwlPopulation pop1 = new OwlPopulation("owlPopulation1.csv");
    System.out.println("Population 1 Summary: ");
    System.out.println(pop1);
    System.out.println("Population 1 Size: ");
    System.out.println(pop1.popSize() + "\n");

    OwlPopulation pop2 = new OwlPopulation("owlPopulation2.csv");
    System.out.println("Population 2 Summary: ");
    System.out.println(pop2);
    System.out.println("Population 2 Size: ");
    System.out.println(pop2.popSize() + "\n");

    System.out.println("Merging population 1 and 2...\n");
    pop1.merge(pop2);

    System.out.println("Merged Population Summary: ");
    System.out.println(pop1);
    System.out.println("Merged Population Size: ");
    System.out.println(pop1.popSize());
    }
    catch (FileNotFoundException f){
      System.out.println("File not found.");
    }
  }
}
