package ru.mail.polis.structures;


import java.util.Comparator;

public class Heap<Key extends Comparable<Key>>{
  private static final int INITIAL_SIZE = 10;

  private Key[] elementData;

  private Comparator<Key> comparator;
  private int size = 0;

  public Key[] getElementData() {
    return elementData;
  }

  public Heap(Key[] array, Comparator<Key> comparator) {
    this.comparator = comparator;
    elementData = array;
    size = elementData.length;
    for (int i = elementData.length/2; i >= 0; i--) {
      siftDown(i);
    }
  }

  public Key extractMin() {
    if (size == 0)
      throw new IndexOutOfBoundsException();
    Key value = elementData[0];
    elementData[0] = elementData[--size];
    siftDown(0);
    return value;
  }

  public boolean isEmpty() {
    return size < 1;
  }

  public int size() {
    return size;
  }

  private void swap(int i, int j) {
    Key tmp = elementData[i];
    elementData[i] = elementData[j];
    elementData[j] = tmp;
  }


  private void siftDown(int i) {
    while (2 * i + 1 < size) {
      int index;
      int left = index = 2 * i + 1;
      int right = 2 * i + 2;
      if (right < size && greater(left, right)) {
        index = right;
      }
      if (greater(i, index)) {
        swap(index, i);
        i = index;
      } else
        break;
    }

  }


  private boolean greater(int i, int j) {
    return comparator == null
            ? elementData[i].compareTo(elementData[j]) > 0
            : comparator.compare(elementData[i], elementData[j]) > 0
            ;
  }

}
