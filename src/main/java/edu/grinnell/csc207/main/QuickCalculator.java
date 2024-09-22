package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.math.BigInteger;
import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

public class QuickCalculator {
  public static BigInteger zero = BigInteger.valueOf(0);

  public static String StringtoBigInt(String input){
    return "hello";
  }

  public static void main(String[] args){
    BFCalculator cal = new BFCalculator(new BigFraction(zero, zero));
    //Go through everything in strings
      //Nested while --> between the "'s

    //First thing --> number
      //If number, computed.add(num)
    //Everything after that will be an operation or a number
      //If operation, look for the next number, then do the operation with computed and that number
      if(args[0]==null || args[0].isEmpty()){
        //Error message: invalid input
      }
      else if((int)args[0].charAt(0)<48 || (int)args[0].charAt(0)>57){
        //Error message: invalid input
      }
      else{
        //need a string to BigInt function
      }
      for(int i=1; i<args.length; i++){

    }
  }
}