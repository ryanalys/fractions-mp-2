package edu.grinnell.csc207.util;

/**
 * Provides a set of registers to store values based on a letter.
 * @author Alyssa Ryan
 */
public class BFRegisterSet {
  /**
   * The max number of registers we can have.
   */
  private static final int MAX_REGISTERS = 26;
  /**
   * The set of registers.
   */
  private BigFraction[] reg = new BigFraction[MAX_REGISTERS];

  /**
   * Initializes the set of registers.
   * @param set The array to use as a register
   */
  public BFRegisterSet(BigFraction[] set) {
    this.reg = set;
  } //BFRegisterSet(BigFraction[])

  /**
   * Stores the BigFraction in the register for letter.
   * @param letter The register to store the fraction in
   * @param val The value to store
   */
  public void store(char letter, BigFraction val) {
    int index = (int) letter - (int) 'a';
    reg[index] = val;
  } //store(char, BigFraction)

  /**
   * Gets the value stored at register letter.
   * @param letter The register to get the value from
   * @return The value stored at register letter
   */
  public BigFraction get(char letter) {
    int index = (int) letter - (int) 'a';
    BigFraction output = new BigFraction(reg[index].numerator(), reg[index].denominator());
    return output;
  } //get(char)
} //class BFRegisterSet
