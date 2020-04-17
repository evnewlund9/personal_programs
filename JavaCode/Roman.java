import java.util.Scanner;
public class Roman
{
  public static void main(String[] args){
    boolean done;
    done = false;
    while(not(done)){
      Scanner input = new Scanner(System.in);
      System.out.println("Enter a Roman Numeral as a string: ");
      String romannumeral = input.nextLine();
      if(romannumeral.length() == 0){
        done = true;
      }
      else{
        System.out.println("Decimal value: "+ ConvertRoman(romannumeral));}
      }
    }
    public static int convertDigit(char letter){
      String digits = "IVXLCDM";
      int[] values = {1,5,10,50,100,500,1000};
      int i = 0;
      while(i < digits.length() &&(letter != digits[i])){
        i += 1;
      if(i<digits.length()){
        return values[i];}
      return 0;}
    }

    public static int ConvertRoman(String romannumeral){
      int lastvalue = 0;
      int decimalval = 0;
      romannumeral = romannumeral.tochararray();
      for(char i : romannumeral){
        char currentvalue = convertDigit(i);
        if(lastvalue<currentvalue){
          decimalval -= lastvalue;}
        else{
          decimalval += lastvalue;}
        lastvalue = currentvalue;
      }
      return(decimalval + lastvalue);
    }
}
