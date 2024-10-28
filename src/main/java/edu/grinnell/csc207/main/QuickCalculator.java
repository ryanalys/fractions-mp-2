package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.math.BigInteger;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * Acknowledgments:
 *  GeekforGeeks tutorial on the substring function
 *  GeekforGeeks tutorial on string splitting
 */

public class QuickCalculator {
  public static BigInteger zero = BigInteger.valueOf(0);
  public static BigInteger one = BigInteger.valueOf(1);
  public static PrintWriter pen = new PrintWriter(System.out, true);
  public static BFCalculator cal = new BFCalculator(new BigFraction(zero, zero));
  public static BigFraction current = new BigFraction(zero, zero);
  public static BFRegisterSet regset = new BFRegisterSet(new BigFraction[26]);

  public static BigFraction StringtoBigFrac(String input){
    String[] expanded = input.split("/");
    BigFraction output = new BigFraction(zero,zero);
    boolean neg = false;
    boolean foundNum = false;
    String current;
    int temp = 0;
    BigInteger num = zero;
    BigInteger dem = BigInteger.valueOf(12);
    

    if(expanded[0].charAt(0)=='-'){
      neg = true;
      expanded[0] = expanded[0].substring(1);
    } //Is the fraction negative

    if(Character.isLowerCase(input.charAt(0))){
      output = regset.get(input.charAt(0));
      if(output == null) {
        pen.println("error: Invalid input");
      }
      return output;
    } //Is the input actually a stored register value

    if(expanded.length == 1){
      current = expanded[0];
      temp = Integer.parseInt(String.valueOf(current));
      num = BigInteger.valueOf(temp);
    }
    else{
      for(int i=0; i<expanded.length; i++){
        if(expanded[i].equals("/")){
          continue;
        }
        else{
          current = expanded[i];
          temp = Integer.parseInt(String.valueOf(current));
          if(foundNum==false){
            num = BigInteger.valueOf(temp);
            foundNum = true;
            if(neg == true){
              num = num.multiply(BigInteger.valueOf(-1));
            }
          }
          else{
            dem = BigInteger.valueOf(temp);
          }
        }
      }
    }
    if(input.contains("/") == false) {
      dem = BigInteger.valueOf(1);
    }
    output.set(num, dem);
    return output;
  }

  public static void caller(String[] expression){
    char reg = ' ';
    boolean end = false;
    String print = " ";
    for(int j=0; j<expression.length; j++){
      if(j==(expression.length-1) || expression[j+1]==null || expression[j+1].isEmpty()){
        end = true;
        return;
      } else if(expression[j].equals("STORE") && end ==false){
        if(cal.computedChanged == false){
          pen.println("Error: invalid input");
        }
        reg = expression[j+1].charAt(0);
        BigFraction value = new BigFraction(cal.computed.numerator(), cal.computed.denominator());
        regset.store(reg, value);
        cal.get().reduce();
        if(cal.get().denominator().compareTo(one) == 0){
          print = "--> STORED " + reg + " " + (regset.get(reg).numerator()).toString();
        } else{
          print = "--> STORED " + reg + " " + cal.fracPrint(regset.get(reg));
        }
        j++;
      } else if(expression[j].equals("+") && end==false){
        if(cal.computedChanged == false){
          pen.println("Error: invalid input");
        }
        current = StringtoBigFrac(expression[j+1]);
        print = "--> " + cal.fracPrint(cal.get()) + " + " + cal.fracPrint(current) + " = ";
        cal.add(current);
        cal.get().reduce();
        cal.get().reduce();cal.get().reduce();cal.get().reduce();
        if(cal.get().denominator().compareTo(one) == 0){
          print = print + (cal.get().numerator()).toString();
        } else{
          print = print + cal.fracPrint(cal.get());
        }
        j++;
      }
      else if(expression[j].equals("-") && end==false){
        if(cal.computedChanged == false){
          pen.println("Error: invalid input");
        }
        current = StringtoBigFrac(expression[j+1]);
        print = "--> " + cal.fracPrint(cal.get()) + " - " + cal.fracPrint(current) + " = ";
        cal.subtract(current);
        cal.get().reduce();
        if(cal.get().denominator().compareTo(one) == 0){
          print = print + (cal.get().numerator()).toString();
        } else{
          print = print + cal.fracPrint(cal.get());
        }
        j++;
      }
      else if(expression[j].equals("*") && end==false){
        if(cal.computedChanged == false){
          pen.println("Error: invalid input");
        }
        current = StringtoBigFrac(expression[j+1]);
        print = "--> " + cal.fracPrint(cal.get()) + " * " + cal.fracPrint(current) + " = ";
        cal.multiply(current);
        cal.get().reduce();
        if(cal.get().denominator().compareTo(one) == 0){
          print = print + (cal.get().numerator()).toString();
        } else{
          print = print + cal.fracPrint(cal.get());
        }
        j++;
      }
      else if(expression[j].equals("/") && end==false){
        if(cal.computedChanged == false){
          pen.println("Error: invalid input");
        }
        current = StringtoBigFrac(expression[j+1]);
        print = "--> " + cal.fracPrint(cal.get()) + " / " + cal.fracPrint(current) + " = ";
        cal.divide(current);
        cal.get().reduce();
        if(cal.get().denominator().compareTo(one) == 0){
          print = print + (cal.get().numerator()).toString();
        } else{
          print = print + cal.fracPrint(cal.get());
        }
        j++;
      }
      else{
          current = StringtoBigFrac(expression[j]);
          cal.computed.set(current.numerator(), current.denominator());
          cal.computedChanged = true;
      }
    }
    pen.println(print);
  }

  public static void main(String[] args){
    PrintWriter pen = new PrintWriter(System.out, true);
    for(int i=0; i<args.length; i++){
      if(args[i]==null || args[i].isEmpty()){
        pen.println("Invalid input");
        return;
      }
      else{
        String[] expression = args[i].split(" ");
        caller(expression);
      }
    }
    return;
  }
}