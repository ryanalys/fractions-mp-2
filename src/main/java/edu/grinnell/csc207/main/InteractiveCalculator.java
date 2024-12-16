package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;
import edu.grinnell.csc207.util.BigFraction;

/**
 * Allows the user to continuously provide input to be calculated.
 * @author Alyssa Ryan
 */
public class InteractiveCalculator {
  /**
   * Big Integer value of zero.
   */
  private static BigInteger zero = BigInteger.valueOf(0);
  /**
   * Big Integer value of one.
   */
  private static BigInteger one = BigInteger.valueOf(1);

  /**
   * Starts the calculator; continuously asks the user for input until they input 'QUIT'.
   * @param args Any arguments from the user
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner reader = new Scanner(System.in);
    String[] expressions;
    while (true) {
      pen.printf("--> ");
      pen.flush();
      String moreStuff = reader.nextLine();
      expressions = moreStuff.split(" ");
      if (moreStuff.contains("QUIT")) {
        reader.close();
        return;
      } else if (expressions.length == 1) {
        if (Character.isLowerCase(moreStuff.charAt(0))) {
          BigFraction temp = QuickCalculator.getRegset().get(moreStuff.charAt(0));
          if (temp == null) {
            pen.println("Error: invalid expression");
          } //if
          String output = QuickCalculator.getCal().fracPrint(temp);
          if (output == null) {
            pen.println("Error: Invalid expression");
          } else {
            pen.println(output);
          } //if
        } else {
          String print = "";
          BigFraction val = QuickCalculator.stringtoBigFrac(moreStuff);
          QuickCalculator.getCal().setComp(val);
          if (QuickCalculator.getCal().get().denominator().compareTo(one) == 0) {
            print = print + (QuickCalculator.getCal().get().numerator()).toString();
          } else {
            print = print + QuickCalculator.getCal().fracPrint(QuickCalculator.getCal().get());
          } //if
          pen.println(expressions[0]);
        } //if
      } else {
        QuickCalculator.caller(expressions);
        pen.printf("\n");
      } //if
    } //while
  } //main(String)
} //class InteractiveCalculator
