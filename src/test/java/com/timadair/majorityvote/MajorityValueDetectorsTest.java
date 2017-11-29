package com.timadair.majorityvote;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Apply the same tests to each new detector
 * Created by timadair on 10/16/2017.
 */
public class MajorityValueDetectorsTest {

  @DataProvider(name="detectors")
  public static Object[][] majorityDetectors() {
    return new Object[][] {
      {new MapMutableReduceDetector()},
      {new ImperativeDetector()},
      {new BoyerMooreMajorityVoteAlgorithm()}
    };
  }

  @Test(dataProvider = "detectors")
  public void shouldFindMajorityValue(MajorityValueDetector detector) {
    assertEquals(detector.detectMajorityValue(new int[]{1, 2, 1}), 1);
  }

  @Test(dataProvider = "detectors")
  public void shouldNotFindModeThatIsNotAMajority(MajorityValueDetector detector) {
    assertEquals(detector.detectMajorityValue(new int[]{1, 2, 3, 1, 2, 3, 1}), -1);
  }

  @Test(dataProvider = "detectors")
  public void shouldSatisfyTheSpecficTestFromProblemStatement(MajorityValueDetector detector) {
    assertEquals(detector.detectMajorityValue(new int[]{3, 4, 3, 2, 3, -1, 3, 3}), 3);
  }

  @Test(dataProvider = "detectors")
  public void shouldNotFindModeThatIsExactlyHalfOfValues(MajorityValueDetector detector) {
    assertEquals(detector.detectMajorityValue(new int[]{1, 2, 3, 1}), -1);
  }

  @Test(dataProvider = "detectors")
  public void shouldHandleEmptyArray(MajorityValueDetector detector) {
    assertEquals(detector.detectMajorityValue(new int[0]), -1);
  }

}
