package com.ostap.hackerrank.euler.no186;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ostap Gonchar
 */
@RunWith(Parameterized.class)
public class SolutionTest {

  private final int n;
  private final int expectedCaller;
  private final int expectedCalled;

  @Parameterized.Parameters(name = "{index}: for n={0}: caller={1} and called={2}")
  public static Iterable<Object[]> data() {
    return Arrays.asList(new Object[][]{
      {1, 200_007, 100_053},
      {2, 600_183, 500_439},
      {3, 600_863, 701_497},
    });
  }

  public SolutionTest(int n, int expectedCaller, int expectedCalled) {
    this.n = n;
    this.expectedCaller = expectedCaller;
    this.expectedCalled = expectedCalled;
  }

  @Test
  public void testCallerAndCalled() throws Exception {
    //given/when
    final int caller = Solution.getCaller(n);
    final int called = Solution.getCalled(n);

    //then
    assertThat(caller).isEqualTo(expectedCaller);
    assertThat(called).isEqualTo(expectedCalled);
  }
}