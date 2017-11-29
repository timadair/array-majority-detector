package com.timadair.majorityvote;

/**
 * This was the third solution I implemented the night after the first two.  It bugged me that I hadn't been able to
 *  figure out a solution with space complexity of O(1), so I started googling.  During my research into space
 *  complexity I found the Boyer-Moore Majority Vote Algorithm, which operates in two passes:
 *    Pass 1: Find either the majority value or the most common near the end of the array.
 *    Pass 2: Determine which of the two was found.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Pros:  Extremely space-efficient.
 * Cons:  Requires two passes over the data, so it couldn't be used in streaming without a window.
 *        Slightly mind-bending.  This is a very non-obvious solution.
 *
 * Created by timadair on 10/18/2017.
 */
public class BoyerMooreMajorityVoteAlgorithm implements MajorityValueDetector {

  @Override
  public int detectMajorityValue(int[] A) {
    if (A.length == 0) {
      return -1;
    }
    int count = 1;
    int temporaryWinner = A[0];

    for (int i = 1; i < A.length; i++) {
      int nextNum = A[i];
      if (temporaryWinner == nextNum) {
        count++;
      }
      else if (count == 0) {
        temporaryWinner = nextNum;
        count = 1;
      }
      else {
        count--;
      }
    }
    if (count > 0 && countMode(A, temporaryWinner) > (A.length / 2)) {
      return temporaryWinner;
    }
    return -1;
  }

  private static int countMode(int[] a, int mode) {
    int count = 0;
    for (int num : a) {
      if (num == mode) {
        count++;
      }
    }
    return count;
  }
}
