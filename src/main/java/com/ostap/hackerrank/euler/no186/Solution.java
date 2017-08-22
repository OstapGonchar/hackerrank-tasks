package com.ostap.hackerrank.euler.no186;

import java.util.Scanner;

/**
 * @author Ostap Gonchar
 * <a href="https://www.hackerrank.com/contests/projecteuler/challenges/euler186">Project Euler #186: Connectedness of a network.</a>
 */
public class Solution {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

  }

  static int getCaller(int n) {
    return getS(2 * n - 1);
  }

  static int getCalled(int n) {
    return getS(2 * n);
  }

  private static int getS(int k) {
    int[] ints = new int[k];
    for (int i = 1; i <= k; i++) {
      if (i <= 55) {
        ints[i - 1] = (100_003 - 200_003 * i + 300_007 * i * i * i) % 1_000_000;
      } else {
        ints[i - 1] = (ints[i - 25] + ints[i - 56]) % 1_000_000;
      }
    }
    return ints[k - 1];
  }
}
