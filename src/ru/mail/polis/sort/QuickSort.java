package ru.mail.polis.sort;

import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

public class QuickSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

  public static final int MIN_ARRAY_LENGTH = 15;
  private Random rand = new Random();
  private int partition(T[] array, int left, int right) {

    int indexOfPivot = left + rand.nextInt(right-left+1);
    T valOfPivot = array[indexOfPivot];
    int i = left;
    int j = right;
    while (i < j) {
      while (lesser(array[i], valOfPivot)) i++;
      while (greater(array[j], valOfPivot)) j--;
      if (i < j)
        SortUtils.swap(array, i++, j--);
    }
    return j;
  }


  private void doSort(T[] array, int left, int right ) {
    if (right-left + 1 < MIN_ARRAY_LENGTH) {
      InsertionSort.sort(array, left, right);
      return;
    }
    int pivot = partition(array, left, right);
    doSort(array, left, pivot );
    doSort(array, pivot+1, right);
  }
  @Override
  public void sort(T[] array) {
    doSort(array, 0, array.length-1);
  }

  public static void main(String[] args) {
    QuickSort<Integer> sorter = new QuickSort<>();
    try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
      StringTokenizer str = new StringTokenizer(bf.readLine(), " ");
      Integer[] array = new Integer[str.countTokens()];
      int i = 0;
      while (str.hasMoreElements()) {
        array[i++] = Integer.parseInt(str.nextToken());
      }
      sorter.sort(array);
      for (Integer integer : array) {
        bw.append(integer.toString());
        bw.append(" ");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }




}