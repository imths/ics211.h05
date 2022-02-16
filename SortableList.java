/**
 * 
 */
package edu.ics211.h05;

import java.util.Comparator;
import edu.ics211.h04.IList211;
import edu.ics211.h04.ISortableList;

/**
 * @author Isaac Segawa
 *
 */
public class SortableList<E> implements IList211<E>, ISortableList<E> {

  private DLinkedNode tail;
  private int size;
  private int swaps;
  private int compares;
  private double sortTime;
  
  /**
   * 
   */
  public SortableList() {
    this.tail = null;
    this.size = 0;
    this.swaps = 0;
    this.compares = 0;
    this.sortTime = 0.0;
  }

  @Override
  public void insertionSort(Comparator<E> compare) {
  //get the start time using System.nanoTime
    //setnumSwaps and numComps to 0
    //set up nextPos int
    double start = System.nanoTime();
    this.swaps = 0;
    this.compares = 0;
    int nextPos;
    //loop data.length times
    for (int i = 1; i < size; i++) {
      //set nextPos to i
      nextPos = i;
      //while nexPos is positive and compare data[nextPos] and data[nextPos - 1]
      while (nextPos > 0 && compare.compare(get(nextPos), get(nextPos - 1)) < 0) {
        //count compare
        this.compares++;
        //swap data[nextPos] and data[nextPos - 1]
        E nextVal = get(nextPos);
        set(nextPos, get(nextPos - 1));
        set(nextPos - 1, nextVal);
        //decrement nextPos for next smallest
        nextPos--;
        //count swap
        this.swaps++;
      }
      //if nextPos is pos
      if (nextPos > 0) {
        //count compare
        this.compares++;
      }
    }
    //get end time and get sortTime
    double end = System.nanoTime();
    this.sortTime = end - start;
    System.out.println(this.swaps + " " + this.compares);
  }

  @Override
  public void bubbleSort(Comparator<E> compare) {
  //get the start time using System.nanoTime
    //setnumSwaps and numComps to 0
    //boolean didSwap
    double start = System.nanoTime();
    this.swaps = 0;
    this.compares = 0;
    boolean didSwap;
    do {
      //set didSwap to false
      didSwap = false;
      //loop data.length - 1 times
      for (int j = 0; j < size - 1; j++) {
        //count compare
        this.compares++;
        //compare data[j] to data[j + 1]
        if (compare.compare(get(j), get(j + 1)) > 0) {
          //swap data[j] and data[j + 1]
          E key = get(j);
          set(j, get(j + 1));
          set(j + 1, key);
          //count swap
          this.swaps++;
          //set didSwap to true
          didSwap = true;
        }
      }
      //condition
    } while (didSwap);
    //get end time and get sortTime
    double end = System.nanoTime();
    this.sortTime = end - start;
  }

  @Override
  public void selectionSort(Comparator<E> compare) {
  //get the start time using System.nanoTime
    //setnumSwaps and numComps to 0
    double start = System.nanoTime();
    this.swaps = 0;
    this.compares = 0;
    
    int n = size;
    //loop data.length - 1 times
    for (int i = 0; i < n - 1; i++) {
      //set minimum index to i each interval
      int minIndex = i;
      //loop data.length times
      for (int j = i + 1; j < n; j++) {
        //compare data[j] to data[minIndex]
        if (compare.compare(get(j), get(minIndex)) < 0) {
          //swap data[j] and data[minIndex]
          set(minIndex, get(j));
          //count compare
          this.compares++;
        }
        //set data[i] to previous value
        E key = get(minIndex);
        set(minIndex, get(i));
        set(i, key);
        //count compare
        this.compares++;
      }
    }
    //get end time and get sortTime
    double end = System.nanoTime();
    this.sortTime = end - start;
  }

  @Override
  public int getNumberOfSwaps() {
    return this.swaps;
  }

  @Override
  public int getNumberOfComparisons() {
    return this.compares;
  }

  @Override
  public double getSortTime() {
    return this.sortTime;
  }

  @Override
  public E get(int index) {
    //checkIndex()
    checkIndex(index);
    //traverse the nodes starting with tail to index
    DLinkedNode temp = traverse(index);
    //return temp.item
    return temp.item;
  }

  @Override
  public E set(int index, E element) {
    //checkIndex
    checkIndex(index);
    //traverse to index
    DLinkedNode temp = traverse(index);
    //remember old value
    E key = get(index);
    //set temp.item = element
    temp.item = element;
    //return old value
    return key;
  }

  @Override
  public int indexOf(Object obj) {
    DLinkedNode temp = tail;
    //loop from size - 1 to 0
    //if temp.item.equals(obj)
    //return loop counter
    for (int i = size - 1; i >= 0; i--) {
      if (temp.item.equals(obj)) {
        return i;
      }
    }
    //return -1
    return -1;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public boolean add(E e) {
    //add(size, e)
    add(size, e);
    return true;
  }

  @Override
  public void add(int index, E element) {
    //checkIndex, size is ok
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
    //create a new DLinkedNode with element
    DLinkedNode node = new DLinkedNode(element, null, null);
    //if (index == size)
    if (size == 0) {
      tail = node;
    }
    if (index == size) {
      // point node to list
      node.prev = tail;
      // point list to node
      tail.next = node;
      // update tail
      tail = node;
    }
    //else
    else {
      // traverse to index
      DLinkedNode temp = traverse(index);
      // point node to list
      node.prev = temp.prev;
      node.next = temp;
      // point the list to the node
      temp.prev = node;
      if (node.prev != null) {
        node.prev.next = node;
      }
    }
    //increment size
    this.size++;
  }

  @Override
  public E remove(int index) {
    //checkIndex
    checkIndex(index);
    //traverse to index
    DLinkedNode temp = traverse(index);
    //make the list to point over temp
    //if (temp.prev != null)
    // temp.prev.next = temp.next
    if (temp.prev != null) {
      temp.prev.next = temp.next;
    }
    //if (temp.next != null)
    // temp.next.prev = temp.prev
    if (temp.next != null) {
      temp.next.prev = temp.prev;
    }
    size--;
    //decrement size
    if (index == size - 1) {
      tail = temp.prev;
    }
    //return temp.item
    return temp.item;
  }

  private class DLinkedNode {
    E item;
    DLinkedNode next;
    DLinkedNode prev;
    
    public DLinkedNode(E item, DLinkedNode next, DLinkedNode prev) {
      this.item = item;
      this.next = next;
      this.prev = prev;
    }
  }
  
  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
  }

  private DLinkedNode traverse(int index) {
    //create temp node to traverse
    DLinkedNode temp = tail;
    for (int i = size - 1; i > index; i--) {
      temp = temp.prev;
    }
    return temp;
  }

}


