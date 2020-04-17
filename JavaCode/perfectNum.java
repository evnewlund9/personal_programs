import java.util.Scanner;
public class perfectNum
{

public static void main(String[] args){
  Scanner input = new Scanner(System.in);
  System.out.println("Enter upper limit: ");
  int limit =input.nextInt();

  int n;
  n = 1;
  int i;
  int factorsum;
  while(n <= limit){
    i = 1;
    factorsum = 0;

    while(i < n){
      if(n%i == 0){
        factorsum +=i;}
      i +=1;}

    if(factorsum == n){
      System.out.println(n+" is a perfect number!");}
    n +=1;}
}
}
