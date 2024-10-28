package edu.grinnell.csc207.util;
import java.math.BigInteger;

/**
 * Allows the user to perform calculations
 * @author Alyssa Ryan
 */
public class BFCalculator {
  /**
   * Big Integer value of zero
   */
  public BigInteger zero = BigInteger.valueOf(0);
  /**
   * The current running total of what has been computed
   */
  public BigFraction computed = new BigFraction(zero, zero);
  /**
   * Whether we've updated computed (used for error checking)
   */
  public boolean computedChanged = false;


  /**
   * Initializes the calculator
   * @param comp The original value of computed
   */
  public BFCalculator(BigFraction comp){
    this.computed = comp;
  }

  public void setComp(BigFraction frac){
    this.computed.set(frac.num, frac.dem);
  }

  /**
   * Converts a BigFraction to a String formatted correctly to be printed out
   * @param frac The BigFraction to convert
   * @return The String version of frac
   */
  public String fracPrint(BigFraction frac){
    String output = frac.numerator().toString() + "/" + frac.denominator().toString();
    return output;
  }

  /**
   * Prints the calculator's computed field
   * @return The String version of computed, formatted to be printed out
   */
  public String compPrint(){
    String output = computed.numerator().toString() + "/" + computed.denominator();
    return output;
  }
  
  /**
   * Returns the last computed value
   * Returns 0 if no such value exists
   * @return BigFraction last computed value
   */
  public BigFraction get(){
    return computed;
  }

  /**
   * Converts computed and val to have a common denominator, returns val after converting
   * @param val The other fracton to have a common denominator with computed
   * @return The value of frac after getting a common denomiator with computed
   */
  public BigFraction commonDenominator(BigFraction val){
    //The greatest common denominator of the two fractions
    if(computed.num.equals(zero)){
      return val;
    } else if(computed.dem.equals(zero)){
      return val;
    } else if(computed.dem.equals(val.dem)){
      return val;
    } else{
      BigInteger gcd = computed.denominator().multiply(val.denominator());
      if(gcd.equals(BigInteger.valueOf(0))){
        return val;
      }
      else{
      //What we need to multiply a given fraction by to get the correct denominator
      BigInteger multiple;
      //The new num for the fractions
      BigInteger newNum;
      //Changes val
      multiple = gcd.divide(val.denominator());
      newNum = val.numerator().multiply(multiple);
      val.set(newNum, gcd);
      //Changes computed
      multiple = gcd.divide(computed.denominator());
      newNum = computed.numerator().multiply(multiple);
      computed.set(newNum, gcd);
      return val;
    }
    }
  }

  /**
   * Adds computed and the inputed BigFraction
   * @param val The fraction to add to computed
   */
  public void add(BigFraction val){
    if(computed.numerator().equals(zero) && computed.denominator().equals(zero)){
      computed.num = val.num;
      computed.dem = val.dem;
    }
    else if(val.numerator().equals(zero)){
      return;
    }
    else{
      val = commonDenominator(val);

      //Adds the numerators together
      BigInteger newNum = val.numerator().add(computed.numerator());
      computed.setNum(newNum);
    }
  }

  /**
   * Subtracts computed and the inputed BigFraction
   * @param val The fraction to subtract from computed
   */
  public void subtract(BigFraction val){
    if(computed.numerator().equals(zero) && computed.denominator().equals(zero)){
      computed.num = val.num;
      computed.dem = val.dem;
    } else if(val.numerator().equals(zero)){
      return;
    } else{
      val = commonDenominator(val);

      BigInteger newNum = computed.numerator().subtract(val.numerator());
      computed.setNum(newNum);
    }
  }

  /**
   * Multiplies computed and the inputed BigFraction
   * @param val The fraction to multiply with computed
   */
  public void multiply(BigFraction val){
    if(computed.numerator().equals(zero) || val.numerator().equals(zero)){
      computed.setNum(zero);
    }
    else{
      BigInteger newNum = computed.numerator().multiply(val.numerator());
      BigInteger newDem = computed.denominator().multiply(val.denominator());
      computed.set(newNum, newDem);
    }
  }

  /**
   * Divides computed by inputted BigFraction
   * @param val The fraction to divide by computed
   */
  public void divide(BigFraction val){
    if(computed.numerator().equals(zero) && computed.denominator().equals(zero)){
      //Error: divide by zero, exit program
      return;
    }
    else{
      BigInteger newNum = computed.numerator().multiply(val.denominator());
      BigInteger newDem = computed.denominator().multiply(val.numerator());
      computed.set(newNum, newDem);
    }
  }

  /**
   * Resets computed back to zero over zero
   */
  public void clear(){
    computed.set(zero, zero);
  }
}
