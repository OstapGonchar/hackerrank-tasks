package com.ostap.hackerrank.euler.no186;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @author Ostap Gonchar
 * <a href="https://www.hackerrank.com/contests/projecteuler/challenges/euler186">Project Euler #186: Connectedness of a network.</a>
 */
public class Solution {
  private static final int CACHE_SIZE = 100;

  private static List<Phone> phoneList = new ArrayList<>(1_000_000);
  private static List<BigInteger> historyOfS = new ArrayList<>();

  private static final BigInteger _100_003 = BigInteger.valueOf(100_003);
  private static final BigInteger _200_003 = BigInteger.valueOf(200_003);
  private static final BigInteger _300_007 = BigInteger.valueOf(300_007);
  private static final BigInteger _1_000_000 = BigInteger.valueOf(1_000_000);

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    final int number = in.nextInt();
    final int percentile = in.nextInt();

    fillRootLeaders();

    int calls = 0;
    while (phoneList.get(findRootLeader(number)).count < 1_000_000 * percentile / 100) {
      final int caller = getS();
      final int called = getS();
      if (caller == called) {
        continue;
      }
      calls++;
      merge(caller, called);
    }
    System.out.println(calls);
  }

  private static void fillRootLeaders() {
    IntStream.range(0, 1_000_000).forEach(i -> phoneList.add(new Phone(i)));
  }

  private static int findRootLeader(int n) {
    int current = n;
    while (true) {
      final int leader = phoneList.get(current).leader;
      if (leader == current) {
        final Phone phone = phoneList.get(n);
        if (phone.leader != leader) {
          phone.leader = leader;
        }
        return leader;
      }
      current = leader;
    }
  }

  private static void merge(int x, int y) {
    final int rootLeaderX = findRootLeader(x);
    final int rootLeaderY = findRootLeader(y);

    if (rootLeaderX != rootLeaderY) {
      final Phone phoneX = phoneList.get(rootLeaderX);
      final Phone phoneY = phoneList.get(rootLeaderY);
      if (phoneX.count >= phoneY.count) {
        phoneX.count += phoneY.count;
        phoneY.leader = phoneX.leader;
      } else {
        phoneY.count += phoneX.count;
        phoneX.leader = phoneY.leader;
      }
    }
  }

  private static int getS() {
    final BigInteger current;
    int currentIteration = historyOfS.size() + 1;
    if (currentIteration <= 55) {
      final BigInteger val = BigInteger.valueOf(currentIteration);
      current = _100_003
        .subtract(_200_003.multiply(val))
        .add(_300_007.multiply(val.pow(3)))
        .mod(_1_000_000);
    } else {
      current = historyOfS.get(currentIteration - 1 - 24).add(historyOfS.get(currentIteration - 1 - 55)).mod(_1_000_000);
      if (historyOfS.size() > 55 + CACHE_SIZE) {
        historyOfS.subList(0, CACHE_SIZE).clear();
      }
    }
    historyOfS.add(current);
    return current.intValue();
  }

  private static class Phone {
    private int leader;
    private int count = 1;

    Phone(int leader) {
      this.leader = leader;
    }
  }
}
