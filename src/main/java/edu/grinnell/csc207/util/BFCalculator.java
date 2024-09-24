package edu.grinnell.csc207.util;
import java.math.BigInteger;

public class BFCalculator {
  BigInteger zero = BigInteger.valueOf(0);
  BigInteger one = BigInteger.valueOf(1);
  BigInteger two = BigInteger.valueOf(2);
  BigInteger three = BigInteger.valueOf(3);
  BigInteger four = BigInteger.valueOf(4);
  BigInteger five = BigInteger.valueOf(5);
  BigFraction computed;

  public BFCalculator(BigFraction comp){
    this.computed = comp;
  }

  public String fracPrint(BigFraction frac){
    String output = frac.numerator().toString() + "/" + frac.denominator().toString();
    return output;
  }

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

  public BigFraction commonDenominator(BigFraction val){
    //The greatest common denominator of the two fractions
    if(computed.num.equals(zero)){
      return val;
    } else if(computed.dem.equals(zero)){
      //Error message divide by zero here
      val.set(zero, zero);
      return val;
    } else if(computed.dem.equals(val.dem)){
      return val;
    } else{
      BigInteger gcd = computed.denominator().gcd(val.denominator());
      if(gcd.equals(BigInteger.valueOf(0))){
        return val;
      }
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


  public void clear(){
    computed.set(zero, zero);
  }
}
