package edu.grinnell.csc207.util;
import java.math.BigInteger;

/**
 * Creates a Big Fraction with a numerator and denominator.
 * @author Alyssa Ryan
 * Acknowledgements
 *     Some code adapted from the BigFraction class from the
 *     <designing your own classes> lab in CSC-207
 */
public class BigFraction {
  /**
   * Numerator of the fraction.
   */
  private BigInteger num;
  /**
   * Denominator of the fraction.
   */
  private BigInteger dem;
  /**
   * Big Integer value of zero.
   */
  private BigInteger zero = BigInteger.valueOf(0);

  /**
   * Initializes the BigFraction with numerator and denominator inputted by the user.
   * @param numer Numerator of BigFraction
   * @param denom Denominator of BigFraction
   */
  public BigFraction(BigInteger numer, BigInteger denom) {
    this.num = numer;
    this.dem = denom;
  } //BigFraction(BigInteger, BigInteger)

  /**
   * Reduces the fraction as much as possible.
   */
  public void reduce() {
    BigInteger bGCD = this.num.gcd(this.dem);
    if (bGCD != zero) {
      if (this.dem.compareTo(zero) == -1) {
        this.num.multiply(BigInteger.valueOf(-1));
        this.dem.multiply(BigInteger.valueOf(-1));
      } //If the denominator is negative, moves it to the numerator
      this.num = this.num.divide(bGCD);
      this.dem = this.dem.divide(bGCD);
    } //if
  } //reduce()

  /**
   * Returns the numerator of the fraction.
   * @return The numerator
   */
  public BigInteger numerator() {
    return this.num;
  } //numerator()

  /**
   * Returns the denominator of the fraction.
   * @return The denominator
   */
  public BigInteger denominator() {
    return this.dem;
  } //denominator()

  /**
   * Sets the fraction to have the numerator and denominator inputted by the user.
   * @param numer The numerator
   * @param denom The denominator
   */
  public void set(BigInteger numer, BigInteger denom) {
    this.num = numer;
    this.dem = denom;
  } //set(BigInteger, BigInteger)

  /**
   * Sets the numerator of the fraction.
   * @param numer The numerator
   */
  public void setNum(BigInteger numer) {
    this.num = numer;
  } //setNum(BigInteger)

  /**
   * Sets the denominator of the fraction.
   * @param denom The denominator
   */
  public void setDem(BigInteger denom) {
    this.dem = denom;
  } //setDem(BigInteger)

  /**
   * Outputs the string in the format ready to be printed.
   * @return String to be printed out
   */
  public String fracPrint() {
    String output = this.num.toString() + "/" + this.dem.toString();
    return output;
  } //fracPrint()
} //class BigFraction
