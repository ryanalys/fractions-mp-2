package edu.grinnell.csc207.util;

/**
 * Provides a set of registers to store values based on a letter
 * @author Alyssa Ryan
 */
public class BFRegisterSet {
  /**
   * The set of registers
   */
  public BigFraction[] reg = new BigFraction[26];

  /**
   * Initializes the set of registers
   * @param set The array to use as a register
   */
  public BFRegisterSet(BigFraction[] set){
    this.reg = set;
  }

  /**
   * Stores the BigFraction in the register for letter
   * @param letter The register to store the fraction in
   * @param val The value to store
   */
  public void store(char letter, BigFraction val){
    int index = (int) letter - (int) 'a';
    reg[index] = val;
  }

  /**
   * Gets the value stored at register letter
   * @param letter The register to get the value from
   * @return The value stored at register letter
   */
  public BigFraction get(char letter){
    int index = (int) letter - (int) 'a';
    BigFraction output = new BigFraction(reg[index].numerator(), reg[index].denominator());
    return output;
  }
}
