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
    BigInteger bGCD = this.num.gcd(this.dem);
    this.num = this.num.divide(bGCD);
    this.dem = this.dem.divide(bGCD);
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
