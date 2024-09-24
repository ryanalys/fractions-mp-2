package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;

public class InteractiveCalculator{
  //Remember to clear() before doing anything else
  
  public static void main(String[] args){
    PrintWriter pen = new PrintWriter(System.out, true);
    boolean finished = false;
    while(finished == false){
      Scanner eyes = new Scanner(System.in);
      pen.flush();
      String moreStuff = eyes.nextLine();
      String[] values = moreStuff.split(" ");
      if(values[0].equals("QUIT")){
        finished = true;
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