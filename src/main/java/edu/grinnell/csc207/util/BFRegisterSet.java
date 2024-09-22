package edu.grinnell.csc207.util;

public class BFRegisterSet {
  public BigFraction[] reg = new BigFraction[26];

  public BFRegisterSet(BigFraction[] set){
    this.reg = set;
  }

  public void store(char letter, BigFraction val){
    int index = (int) letter - (int) 'a';
    reg[index] = val;
  }
}
