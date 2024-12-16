package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.math.BigInteger;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * Calculates based off of strings from user.
 * @author Alyssa Ryan
 * Acknowledgments:
 *  GeekforGeeks tutorial on the substring function
 *  GeekforGeeks tutorial on string splitting
 */
public class QuickCalculator {
  /**
   * Big Integer value of zero.
   */
  private static BigInteger zero = BigInteger.valueOf(0);
  /**
   * Big Integer value of one.
   */
  private static BigInteger one = BigInteger.valueOf(1);
  /**
   * Pen to use throughout the class.
   */
  private static PrintWriter pen = new PrintWriter(System.out, true);
  /**
   * Calculator we work with.
   */
  private static BFCalculator cal = new BFCalculator(new BigFraction(zero, zero));
  /**
   * The current value we are working with.
   */
  private static BigFraction current = new BigFraction(zero, zero);
  /**
   * The max number of registers we can have.
   */
  public static final int MAX_REGISTERS = 26;
  /**
   * Regset we are working with.
   */
  private static BFRegisterSet regset = new BFRegisterSet(new BigFraction[MAX_REGISTERS]);

  /**
   * Returns the regset belonging to this calculator.
   * @return the regset
   */
  public static BFRegisterSet getRegset() {
    return regset;
  } //getRegset()

  /**
   * Returns the calculator belonging to this calculator.
   * @return the calculator
   */
  public static BFCalculator getCal() {
    return cal;
  } //getCal()

  /**
   * Converts the string to the BigFraction.
   * @param input string to be converted
   * @return BigFraction version of the string inputted
   */
  public static BigFraction stringtoBigFrac(String input) {
    String[] expanded = input.split("/");
    BigFraction output = new BigFraction(zero, zero);
    boolean neg = false;
    boolean foundNum = false;
    String currentString = "";
    int temp = 0;
    BigInteger num = zero;
    BigInteger dem = one;


    if (expanded[0].charAt(0) == '-') {
      neg = true;
      expanded[0] = expanded[0].substring(1);
    } //Is the fraction negative

    if (Character.isLowerCase(input.charAt(0))) {
      output = regset.get(input.charAt(0));
      if (output == null) {
        pen.println("error: Invalid input");
      } //if
      return output;
    } //Is the input actually a stored register value

    if (expanded.length == 1) {
      currentString = expanded[0];
      temp = Integer.parseInt(String.valueOf(current));
      num = BigInteger.valueOf(temp);
    } else {
      for (int i = 0; i < expanded.length; i++) {
        if (expanded[i].equals("/")) {
          continue;
        } else {
          currentString = expanded[i];
          temp = Integer.parseInt(String.valueOf(current));
          if (!foundNum) {
            num = BigInteger.valueOf(temp);
            foundNum = true;
            if (neg) {
              num = num.multiply(BigInteger.valueOf(-1));
            } //if
          } else {
            dem = BigInteger.valueOf(temp);
          } //if
        } //if
      } //for
    } //if
    if (!input.contains("/")) {
      dem = BigInteger.valueOf(1);
    } //if
    output.set(num, dem);
    return output;
  } //stringtoBigFrac(String)

  /**
   * Takes in an array of arguments of calculations to be performed, outputs the answers.
   * @param expression array of expressions inputted by user
   */
  public static void caller(String[] expression) {
    char reg = ' ';
    boolean end = false;
    String print = " ";
    for (int j = 0; j < expression.length; j++) {
      if (j == (expression.length - 1)
          || expression[j + 1] == null
          || expression[j + 1].isEmpty()) {
        end = true;
        return;
      } else if (expression[j].equals("STORE") && !end) {
        if (!(cal.getComputedChanged())) {
          pen.println("Error: invalid input");
        } //if
        reg = expression[j + 1].charAt(0);
        BigFraction value = new BigFraction(cal.get().numerator(), cal.get().denominator());
        regset.store(reg, value);
        cal.get().reduce();
        if (cal.get().denominator().compareTo(one) == 0) {
          print = "--> STORED " + reg + " " + (regset.get(reg).numerator()).toString();
        } else {
          print = "--> STORED " + reg + " " + cal.fracPrint(regset.get(reg));
        } //if
        j++;
      } else if (expression[j].equals("+") && !end) {
        if (!(cal.getComputedChanged())) {
          pen.println("Error: invalid input");
        } //if
        current = stringtoBigFrac(expression[j + 1]);
        print = "--> " + cal.fracPrint(cal.get()) + " + " + cal.fracPrint(current) + " = ";
        cal.add(current);
        cal.get().reduce();
        cal.get().reduce();
        if (cal.get().denominator().compareTo(one) == 0) {
          print = print + (cal.get().numerator()).toString();
        } else {
          print = print + cal.fracPrint(cal.get());
        } //if
        j++;
      } else if (expression[j].equals("-") && !end) {
        if (!(cal.getComputedChanged())) {
          pen.println("Error: invalid input");
        } //if
        current = stringtoBigFrac(expression[j + 1]);
        print = "--> " + cal.fracPrint(cal.get()) + " - " + cal.fracPrint(current) + " = ";
        cal.subtract(current);
        cal.get().reduce();
        if (cal.get().denominator().compareTo(one) == 0) {
          print = print + (cal.get().numerator()).toString();
        } else {
          print = print + cal.fracPrint(cal.get());
        } //if
        j++;
      } else if (expression[j].equals("*") && !end) {
        if (!(cal.getComputedChanged())) {
          pen.println("Error: invalid input");
        } //if
        current = stringtoBigFrac(expression[j + 1]);
        print = "--> " + cal.fracPrint(cal.get()) + " * " + cal.fracPrint(current) + " = ";
        cal.multiply(current);
        cal.get().reduce();
        if (cal.get().denominator().compareTo(one) == 0) {
          print = print + (cal.get().numerator()).toString();
        } else {
          print = print + cal.fracPrint(cal.get());
        } //if
        j++;
      } else if (expression[j].equals("/") && !end) {
        if (!(cal.getComputedChanged())) {
          pen.println("Error: invalid input");
        } //if
        current = stringtoBigFrac(expression[j + 1]);
        print = "--> " + cal.fracPrint(cal.get()) + " / " + cal.fracPrint(current) + " = ";
        cal.divide(current);
        cal.get().reduce();
        if (cal.get().denominator().compareTo(one) == 0) {
          print = print + (cal.get().numerator()).toString();
        } else {
          print = print + cal.fracPrint(cal.get());
        } //if
        j++;
      } else {
        current = stringtoBigFrac(expression[j]);
        cal.get().set(current.numerator(), current.denominator());
        cal.setComputedChanged(true);
      } //if
    } //for
    pen.println(print);
  } //caller(String[])

  /**
   * Gets input from the user.
   * @param args the expressions to be calculated
   */
  public static void main(String[] args) {
    for (int i = 0; i < args.length; i++) {
      if (args[i] == null || args[i].isEmpty()) {
        pen.println("Invalid input");
        return;
      } else {
        String[] expression = args[i].split(" ");
        caller(expression);
      } //if
    } //for
    return;
  } //main(String[])
} //class QuickCalculator
