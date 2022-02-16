package edu.ics211.h05;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.ics211.h04.StringComparator;

class SortableListTest {

  @Test
  void testSortableList() {
    SortableList<String> list = new SortableList<String>();
    assertNotNull(list);
    assertEquals(0, list.size());
  }


  @Test
  void testInsertionSort() {
    SortableList<String> list = new SortableList<String>();
    list.add("foo");
    list.add("bad");
    list.add("baz");
    list.insertionSort(new StringComparator());
    assertEquals("bad", list.get(0));
    assertEquals("baz", list.get(1));
    assertEquals("foo", list.get(2));
  }


  @Test
  void testBubbleSort() {
    SortableList<String> list = new SortableList<String>();
    list.add("foo");
    list.add("bad");
    list.add("baz");
    list.insertionSort(new StringComparator());
    assertEquals("bad", list.get(0));
    assertEquals("baz", list.get(1));
    assertEquals("foo", list.get(2));
  }


  @Test
  void testSelectionSort() {
    SortableList<String> list = new SortableList<String>();
    list.add("foo");
    list.add("bad");
    list.add("baz");
    list.insertionSort(new StringComparator());
    assertEquals("bad", list.get(0));
    assertEquals("baz", list.get(1));
    assertEquals("foo", list.get(2));
  }


  @Test
  void testGetNumberOfSwaps() {
    SortableList<String> list = new SortableList<String>();
    list.add("foo");
    list.add("bad");
    list.add("baz");
    list.insertionSort(new StringComparator());
    assertEquals(2, list.getNumberOfSwaps());
  }


  @Test
  void testGetNumberOfComparisons() {
    SortableList<String> list = new SortableList<String>();
    list.add("foo");
    list.add("bad");
    list.add("baz");
    list.insertionSort(new StringComparator());
    assertEquals(3, list.getNumberOfComparisons());
  }


//Testing two lists,
 // the list with much more items to sort should take longer than the list with less items
 @Test
 void testGetSortTime() {
   SortableList<String> list = new SortableList<String>();
   list.add("foo");
   list.add("bar");
   list.add("baz");
   list.selectionSort(new StringComparator());
   final double shorterTime = list.getSortTime();

   SortableList<String> list2 = new SortableList<String>();
   list2.add("foo");
   list2.add("bar");
   list2.add("baz");
   list2.add("car");
   list2.add("dog");
   list2.add("bat");
   list2.selectionSort(new StringComparator());
   final double longerTime = list2.getSortTime();

   assertTrue("shorterTime should be < longerTime", shorterTime < longerTime);
 }


  @Test
  void testGet() {
    SortableList<String> list = new SortableList<String>();
    list.add("foo");
    list.add("bad");
    list.add("baz");
    assertEquals("foo", list.get(0));
    assertEquals("baz", list.get(list.size() - 1));
    try {
      list.get(-1);
      fail("Not yet implemented");
    } catch(IndexOutOfBoundsException ioe) {
    }
    try {
      list.get(list.size());
      fail("Not yet implemented");
    } catch(IndexOutOfBoundsException ioe) {
    }
  }


  @Test
  void testSet() {
    SortableList<String> list = new SortableList<String>();
    list.add("bad");
    list.set(0, "foo");
    assertEquals("foo", list.get(0));
  }


  @Test
  void testIndexOf() {
    SortableList<String> list = new SortableList<String>();
    list.add("foo");
    assertEquals(0, list.indexOf("foo"));
  }


  @Test
  void testSize() {
    SortableList<String> list = new SortableList<String>();
    list.add("foo");
    assertEquals(1, list.size());
  }


  @Test
  void testAddE() {
    SortableList<String> list = new SortableList<String>();
    assertEquals(true, list.add("foo"));
  }


  @Test
  void testAddIntE() {
    SortableList<String> list = new SortableList<String>();
    list.add("foo");
    list.add(0, "bad");
    assertEquals("bad", list.get(0));
  }


  @Test
  void testRemove() {
    SortableList<String> list = new SortableList<String>();
    list.add("foo");
    list.remove(0);
    assertEquals(-1, list.indexOf("foo"));
  }

}



