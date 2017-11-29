package com.timadair.majorityvote;

/**
 * Detects values that are more than half of an array.
 * Created by timadair on 11/28/2017.
 */
public interface MajorityValueDetector {
  /**
   * Detect if there is a majority value, a value that shows up as more than half of the values of the array.
   * @param A an array of integers
   * @return The majority value if present, else -1
   */
  int detectMajorityValue(int[] A);
}
