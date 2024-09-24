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

  public static BigFraction StringtoBigFrac(String input){
    String[] expanded = input.split("/");
    BigFraction output = new BigFraction(zero,zero);
    boolean neg = false;
    boolean foundNum = false;
    String current;
    int temp = 0;
    BigInteger num = zero;
    BigInteger dem = BigInteger.valueOf(1);
    
    if(expanded[0].charAt(0)=='-'){
      neg = true;
      expanded[0] = expanded[0].substring(1);
    }

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
    output.set(num, dem);
    return output;
  }

  public static void caller(String[] expression){
    PrintWriter pen = new PrintWriter(System.out, true);
    BFCalculator cal = new BFCalculator(new BigFraction(zero, zero));
    BigFraction current = new BigFraction(zero, zero);
    BFRegisterSet regset = new BFRegisterSet(new BigFraction[26]);
    char reg = ' ';
        boolean end = false;
        for(int j=0; j<expression.length; j++){
          if(j==(expression.length-1) || expression[j+1]==null || expression[j+1].isEmpty()){
            end = true;
            return;
          }
          else if(expression[j].equals("STORE") && end ==false){
            reg = expression[j+1].charAt(0);
            regset.store(reg, cal.get());
            pen.println("STORED");
            j++;
          }
          else if(expression[j].equals("+") && end==false){
            current = StringtoBigFrac(expression[j+1]);
            cal.add(current);
            j++;
          }
          else if(expression[j].equals("-") && end==false){
            current = StringtoBigFrac(expression[j+1]);
            cal.subtract(current);
            j++;
          }
          else if(expression[j].equals("*") && end==false){
            current = StringtoBigFrac(expression[j+1]);
            cal.multiply(current);
            j++;
          }
          else if(expression[j].equals("/") && end==false){
            current = StringtoBigFrac(expression[j+1]);
            cal.divide(current);
            j++;
          }
          else{
            current = StringtoBigFrac(expression[j]);
            cal.add(current);
          }
        }
        cal.get().reduce();
        pen.printf("--> " + cal.compPrint() + "\n");
        cal.clear();
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