public class Owl {
  private String name;
  private int age;
  private double weight;

  public Owl(String name, int age, double weight) {
    this.name = name;
    this.age = age;
    this.weight = weight;
  }

  public String getName(){
    return name;
  }

  public int getAge(){
    return age;
  }

  public double getWeight(){
    return weight;
  }

  public void setAge(int age){
    this.age = age;
  }

  public void setWeight(double weight){
    this.weight = weight;
  }

  public boolean equals(Owl other){
    if((name == other.getName()) && (other.getAge() == age) && (other.getWeight() == weight)){
      return true;
    }
    return false;
    }

  public static void main(String[] args){
    Owl owl1 = new Owl("John", 5, 17.65);
    Owl owl2 = new Owl("Mary", 10, 12.34);

    System.out.println(owl1.equals(owl2));
    System.out.println(owl1.equals(owl1));
  }
}
