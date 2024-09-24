package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;

public class InteractiveCalculator{
  //Remember to clear() before doing anything else
  
  public static void main(String[] args){
    PrintWriter pen = new PrintWriter(System.out, true);
    while(true){
      pen.printf("--> ");
      pen.flush();
      Scanner eyes = new Scanner(System.in);
      String moreStuff = eyes.next();
      pen.printf("\n");
      String[] values = moreStuff.split(" ");
      if(values[0].equals("QUIT")){
        eyes.close();
        return;
      }
      else{
        QuickCalculator.caller(values);
      }
      eyes.close();
    }
  }
}