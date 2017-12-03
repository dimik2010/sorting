package ru.mail.polis.sort;

import ru.mail.polis.structures.Heap;

public class HeapSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {


  @Override
  public void sort(T[] array) {
    Heap<T> heap = new Heap<T>(array, (first, second) -> { return -(compare(first, second));});
    int i = array.length - 1;
    while (!heap.isEmpty()) {
      heap.getElementData()[i] = heap.extractMin();
      i--;
    }

  }
}
