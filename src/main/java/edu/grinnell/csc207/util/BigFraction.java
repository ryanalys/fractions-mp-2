package edu.grinnell.csc207.util;
import java.math.BigInteger;

/**
 * Acknowledgements
 *     Some code adapted from the BigFraction class from
 *     the designing your own classes lab
 */

public class BigFraction {
  /**
   * Numerator and denominator of the fraction
   * Type BigInteger
   */
  BigInteger num;
  BigInteger dem;

  BigInteger zero = BigInteger.valueOf(0);

  public BigFraction(BigInteger numer, BigInteger denom){
    this.num = numer;
    this.dem = denom;
  }
  public void reduce(){
    //If the fraction is zero, simplifies it
    if(this.num.equals(zero)){
      if(this.dem.equals(zero)){
        return;
      }
      else{
        this.dem = BigInteger.valueOf(1);
        return;
      }
    }
    //If the fraction is not zero
    for(int i=this.dem.intValue(); i>0; i--){
      if((this.num.intValue())%i == 0){
        //Greatest common denominator of num and dem
        BigInteger gcd = BigInteger.valueOf(i);
        this.num = this.num.divide(gcd);
        this.dem = this.dem.divide(gcd);
      }
    }
  }

  public BigInteger numerator(){
    return this.num;
  }

  public BigInteger denominator(){
    return this.dem;
  }

  public void set(BigInteger numer, BigInteger denom){
    this.num = numer;
    this.dem = denom;
  }

  public void setNum(BigInteger numer){
    this.num = numer;
  }
  
  public void setDem(BigInteger denom){
    this.dem = denom;
  }

  public String fracPrint(){
    String output = this.num.toString() + "/" + this.dem.toString();
    return output;
  }
}
