package com.ostap.hackerrank.euler.no186;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ostap Gonchar
 */
@RunWith(Parameterized.class)
public class SolutionTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  private final String number;
  private final String percentile;
  private final String calls;

  @Parameterized.Parameters(name = "for number={0} and percentile={1}, number of calls={2}")
  public static Iterable<Object[]> data() {
    return Arrays.asList(new Object[][]{
      {"000000", "1", "622572"},
    });
  }

  public SolutionTest(String number, String percentile, String calls) {
    this.number = number;
    this.percentile = percentile;
    this.calls = calls;
  }

  @Test
  public void testCallerAndCalled() throws Exception {
    //given
    System.setOut(new PrintStream(outContent));
    System.setIn(new ByteArrayInputStream(number.concat(" ").concat(percentile).getBytes()));
    //when
    Solution.main(new String[]{});

    //then
    assertThat(outContent.toString().trim()).isEqualTo(calls);
  }
}