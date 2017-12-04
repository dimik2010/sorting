package ru.mail.polis.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class QuickSortWithTriplePartition<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

  public static final int MIN_ARRAY_LENGTH = 5;
  private Random rand = new Random();

  private int[] partition(T[] array, int left, int right) {
    int indexOfPivot = left + rand.nextInt(right - left + 1);
    T valOfPivot = array[indexOfPivot];
    int i = left;
    SortUtils.swap(array, indexOfPivot, right);
    int j = right - 1;
    int eqJ = right;
    int eqL = left-1;
    while (i < j) {
      while (lesser(array[i], valOfPivot)) i++;
      while (greater(array[j], valOfPivot) && (i<j) ) j--;
      if (i < j) {
        SortUtils.swap(array, i, j);
        if (array[j].compareTo(valOfPivot) == 0) {
          SortUtils.swap(array, j, --eqJ);
        }
        if (array[i].compareTo(valOfPivot) == 0) {
          SortUtils.swap(array, i, ++eqL);
        }
        i++;
        j--;
      }
    }
    SortUtils.swap(array, right, i);
    j = i-1;
    i++;
    for (int k = left; k <= eqL; k++, j--) {
      SortUtils.swap(array, k, j);
    }
    int startPosOfEquals = j+1;
    for (int k = right-1; k >= eqJ ; k--, i++) {
      SortUtils.swap(array, i, k);
    }
    int finishPosOfEquals = i-1;
    return new int[]{startPosOfEquals, finishPosOfEquals};
  }

  private void doSort(T[] array, int left, int right) {
    if (right - left + 1 < MIN_ARRAY_LENGTH) {
      InsertionSort.sort(array, left, right);
      return;
    }
    int[] positions = partition(array, left, right);
    doSort(array, left, positions[0]-1);
    doSort(array, positions[1]+1, right);
  }

  @Override
  public void sort(T[] array) {
    doSort(array, 0, array.length - 1);
  }

  public static void main(String[] args) {
    QuickSortWithTriplePartition<Integer> sorter = new QuickSortWithTriplePartition<>();
//    Integer[] array = new Integer[]{0};
    int[] data = SortUtils.generateArray(100);
    for (int j = 10; j < 100; j++) {
      data[j] = 100;
    }
    Integer[] dataInteger = IntStream.of(data).boxed().toArray(Integer[]::new);
    sorter.sort(dataInteger);
    System.out.println(SortUtils.isArraySorted(dataInteger));
    System.out.println(Arrays.toString(dataInteger));
  }
}
