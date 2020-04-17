import java.util.Scanner;
import java.util.Arrays;

public class Sieve
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter an upper limit for a range of numbers: ");
    int n = Integer.parseInt(input.nextLine());
    int numbers[] = new int[n + 1];

    for(int i = 2; i * i <= n; i += 1)
    {
      if(numbers[i] == 0)
      {
        for(int x = i * i; i <= n; x += i)
        {
          numbers[x] = 0;
        }
      }
    }

    for(int i = 2; i <= n; i += 1)
    {
        if(numbers[i] == 0)
        {
            System.out.print(i + " ");
        }
      }
  }
}
