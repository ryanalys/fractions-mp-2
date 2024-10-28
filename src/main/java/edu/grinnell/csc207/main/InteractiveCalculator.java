package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;
import edu.grinnell.csc207.util.BigFraction;

/**
 * Allows the user to continuously provide input to be calculated
 * @author Alyssa Ryan
 */
public class InteractiveCalculator{
  /**
   * Starts the calculator; continuously asks the user for input until they input 'QUIT'
   * @param args Any arguments from the user
   */
  public static void main(String[] args){
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner reader = new Scanner(System.in);
    String[] expressions;
    while(true){
      pen.printf("--> ");
      pen.flush();
      String moreStuff = reader.nextLine();
      expressions = moreStuff.split(" ");
      if(moreStuff.contains("QUIT")){
        reader.close();
        return;
      } else if(expressions.length == 1) {
        if(Character.isLowerCase(moreStuff.charAt(0))){
          String output = QuickCalculator.regset.get(moreStuff.charAt(0)).toString();
          pen.println(output);
        } else {
          BigFraction val = QuickCalculator.StringtoBigFrac(moreStuff);
          QuickCalculator.cal.computed = val;
        }
      }
      else{
        QuickCalculator.caller(expressions);
        pen.printf("\n");
      }
    }
  }
}