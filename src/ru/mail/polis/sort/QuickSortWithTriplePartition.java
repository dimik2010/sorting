package ru.mail.polis.sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSortWithTriplePartition<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

  public static final int MIN_ARRAY_LENGTH = 5;
  private Random rand = new Random();

  private int[] partition(T[] array, int left, int right) {
    int indexOfPivot = left + rand.nextInt(right - left + 1);
    T valOfPivot = array[indexOfPivot];
    int i = left;
    SortUtils.swap(array, indexOfPivot, right);
    int j = right - 1;
    int eqP = right;
    while (i < j) {
      while (lesser(array[i], valOfPivot)) i++;
      while (greater(array[j], valOfPivot)) j--;
      if (i < j) {
        SortUtils.swap(array, i++, j--);
        if (array[i].compareTo(valOfPivot) == 0)
          SortUtils.swap(array, i, --eqP);
        if (array[j].compareTo(valOfPivot) == 0)
          SortUtils.swap(array, j, --eqP);
      }
    }
    int startPosOfEquals = j;
    for (int k = eqP; k <= right; k++) {
      SortUtils.swap(array, j++, k);
    }
    int finishPosOfEquals = j - 1;
    return new int[]{startPosOfEquals, finishPosOfEquals};
  }

  private void doSort(T[] array, int left, int right) {
    if (right - left + 1 < MIN_ARRAY_LENGTH) {
      InsertionSort.sort(array, left, right);
      return;
    }
    int[] positions = partition(array, left, right);
    doSort(array, left, positions[0]);
    doSort(array, positions[1], right);
  }

  @Override
  public void sort(T[] array) {
    doSort(array, 0, array.length - 1);
  }

  public static void main(String[] args) {
    QuickSortWithTriplePartition<Integer> sorter = new QuickSortWithTriplePartition<>();
    Integer[] array = new Integer[]{0};
//    Integer[] array = Arrays.stream(SortUtils.generateArray(10)).boxed().toArray(Integer[]::new);
    sorter.sort(array);
    System.out.println(SortUtils.isArraySorted(array));
    System.out.println(Arrays.toString(array));
  }
}
