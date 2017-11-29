package com.timadair.majorityvote;

import java.util.*;

/**
 * This is the first detector that I implemented, because the position I was applying for wanted experience with Hadoop.
 * I don't have experience with Hadoop, but I wanted to demonstrate that I understood map-reduce and functional programming.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Pros:  Can be parallelized.
 *        Only requires one pass over the array.
 * Cons:  Does not satisfy the O(1) space complexity requirement of the problem.
 *        Takes thought to understand what's going on.
 *
 * Created by timadair on 10/16/2017.
 */
public class MapMutableReduceDetector implements MajorityValueDetector {

  @Override
  public int detectMajorityValue(int[] A) {
    Map.Entry<Integer, Integer> highestValueAndCount = MapMutableReduceDetector.getNumberWithHighestCount(A);

    if (highestValueAndCount.getValue() > (A.length / 2)) {
      return highestValueAndCount.getKey();
    }
    else {
      return -1;
    }
  }

  private static Map.Entry<Integer, Integer> getNumberWithHighestCount(int[] input) {
    return countValues(input)
      .entrySet()
      .stream()
      .max(Comparator.comparing(Map.Entry::getValue))
      .orElseGet(() -> new AbstractMap.SimpleEntry<>(0, -1));
  }

  private static HashMap<Integer, Integer> countValues(int[] input) {
    return Arrays.stream(input)
      .collect(HashMap::new,
        (map, value) -> {
          map.computeIfPresent(value, (k, v) -> v + 1);
          map.putIfAbsent(value, 1);
        },
        (mapOne, mapTwo) -> mapTwo.forEach((k, v) -> {
          mapOne.putIfAbsent(k, v);
          mapOne.computeIfPresent(k, (v1, v2) -> v1 + v2);
        })
      );
  }
}
