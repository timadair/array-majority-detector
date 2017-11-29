package com.timadair.majorityvote;

import java.util.HashMap;
import java.util.Map;

/**
 * After spending about an hour on the map-reduce solution, I decided to come up with something as simple as possible
 *  to see if there were any more obvious optimizations that became clear with simpler code.  This took about 5 minutes.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Pros:  Very simple to understand
 *        Only requires one pass over the array.
 * Cons:  Does not satisfy the O(1) space complexity requirement of the problem.
 *
 * Created by timadair on 10/16/2017.
 */
public class ImperativeDetector implements MajorityValueDetector {

  @Override
  public int detectMajorityValue(int[] A) {
    int minMajoritySize = (A.length / 2) + 1;
    Map<Integer, Integer> numberAndCountMap = new HashMap<>();
    for (int number : A) {
      numberAndCountMap.computeIfPresent(number, (k, v) -> v + 1);
      numberAndCountMap.putIfAbsent(number, 1);
    }

    return numberAndCountMap.entrySet().stream()
      .filter(e -> e.getValue() >= minMajoritySize)
      .findFirst()
      .map(Map.Entry::getKey)
      .orElse(-1);
  }
}
