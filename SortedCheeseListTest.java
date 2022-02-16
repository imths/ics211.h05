package edu.ics211.h04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import edu.ics211.h02.Cheese;
import edu.ics211.h02.FatComparator;
import edu.ics211.h02.ManoaCheeseFromager;
import org.junit.Before;
import org.junit.Test;

/**
 * Represents a SortedCheeseListTest.
 *
 * @author Cam Moore
 *
 */
public class SortedCheeseListTest {
  private ManoaCheeseFromager fromager;
  private FatComparator fatC;
  private Cheese[] inFatOrder;

  /**
   * Sets up the fromager.
   *
   * @throws java.lang.Exception if there is a problem.
   */
  @Before
  public void setUp() throws Exception {
    this.fromager = ManoaCheeseFromager.getInstance();
    this.fatC = new FatComparator();
    this.inFatOrder = new Cheese[5];
    for (int i = 0; i < inFatOrder.length; i++) {
      inFatOrder[i] = fromager.makeCheddar("cheese" + i, 10.0 * i + 5);
    }
  }


  /**
   * Test method for {@link edu.ics211.h05.SortedCheeseList#SortedCheeseList(java.util.Comparator)}.
   */
  @Test
  public void testSortedCheeseList() {
    SortedCheeseList list = new SortedCheeseList(fatC);
    assertNotNull("Didn't create a list", list);
    assertEquals("Got wrong size", 0, list.size());
  }


  /**
   * Test method for {@link edu.ics211.h05.SortedCheeseList#get(int)}.
   */
  @Test
  public void testGet() {
    SortedCheeseList list = new SortedCheeseList(fatC);
    list.add(inFatOrder[4]);
    list.add(inFatOrder[2]);
    list.add(inFatOrder[0]);
    // try some good values
    assertEquals("Got wrong cheese", inFatOrder[0], list.get(0));
    // try some bad values
    try {
      list.get(-1);
      fail("Didn't throw exception for index -1");
    } catch (IndexOutOfBoundsException ioob) {
      // this is what we want.
    }
    try {
      list.get(list.size());
      fail("Didn't throw exception for index size");
    } catch (IndexOutOfBoundsException ioob) {
      // this is what we want.
    }
  }


  /**
   * Test method for {@link edu.ics211.h05.SortedCheeseList#set(int, edu.ics211.h02.Cheese)}.
   */
  @Test
  public void testSet() {
    SortedCheeseList list = new SortedCheeseList(fatC);
    list.add(inFatOrder[4]);
    list.add(inFatOrder[2]);
    list.add(inFatOrder[0]);
    // try good value
    Cheese old = list.set(0, inFatOrder[1]);
    assertEquals("Got wrong old value", inFatOrder[0], old);
    assertEquals("Got wrong new value", inFatOrder[1], list.get(0));
    // try some bad values
    try {
      list.set(-1, inFatOrder[3]);
      fail("Didn't throw exception for index -1");
    } catch (IndexOutOfBoundsException ioob) {
      // this is what we want.
    }
    try {
      list.set(list.size(), inFatOrder[3]);
      fail("Didn't throw exception for index size");
    } catch (IndexOutOfBoundsException ioob) {
      // this is what we want.
    }
  }


  /**
   * Test method for {@link edu.ics211.h05.SortedCheeseList#indexOf(java.lang.Object)}.
   */
  @Test
  public void testIndexOf() {
    SortedCheeseList list = new SortedCheeseList(fatC);
    list.add(inFatOrder[4]);
    list.add(inFatOrder[2]);
    list.add(inFatOrder[0]);
    assertEquals("Got wrong index", 0, list.indexOf(inFatOrder[0]));
    assertEquals("Got wrong index", 1, list.indexOf(inFatOrder[2]));
    assertEquals("Got wrong index", 2, list.indexOf(inFatOrder[4]));
    assertEquals("Got wrong index", -1, list.indexOf(inFatOrder[1]));

  }


  /**
   * Test method for {@link edu.ics211.h05.SortedCheeseList#size()}.
   */
  @Test
  public void testSize() {
    SortedCheeseList list = new SortedCheeseList(fatC);
    assertEquals(0, list.size());
    list.add(inFatOrder[4]);
    assertEquals(1, list.size());
    list.add(inFatOrder[2]);
    assertEquals(2, list.size());
    list.add(inFatOrder[0]);
    assertEquals(3, list.size());
  }


  /**
   * Test method for {@link edu.ics211.h05.SortedCheeseList#add(edu.ics211.h02.Cheese)}.
   */
  @Test
  public void testAddCheese() {
    SortedCheeseList list = new SortedCheeseList(fatC);
    assertEquals(0, list.size());
    list.add(inFatOrder[4]);
    assertEquals(1, list.size());
    assertEquals(inFatOrder[4], list.get(0));
    list.add(inFatOrder[2]);
    assertEquals(2, list.size());
    assertEquals(inFatOrder[2], list.get(0));
    assertEquals(inFatOrder[4], list.get(1));
    list.add(inFatOrder[0]);
    assertEquals(3, list.size());
  }


  /**
   * Test method for {@link edu.ics211.h05.SortedCheeseList#add(int, edu.ics211.h02.Cheese)}.
   */
  @Test
  public void testAddIntCheese() {
    SortedCheeseList list = new SortedCheeseList(fatC);
    assertEquals(0, list.size());
    // try good values
    list.add(0, inFatOrder[4]);
    assertEquals(1, list.size());
    assertEquals(inFatOrder[4], list.get(0));
    list.add(1, inFatOrder[2]);
    assertEquals(2, list.size());
    assertEquals(inFatOrder[2], list.get(0));
    assertEquals(inFatOrder[4], list.get(1));
    // try some bad values
    try {
      list.add(-1, inFatOrder[3]);
      fail("Didn't throw exception for index -1");
    } catch (IndexOutOfBoundsException ioob) {
      // this is what we want.
    }
    try {
      list.add(list.size() + 1, inFatOrder[3]);
      fail("Didn't throw exception for index size + 1");
    } catch (IndexOutOfBoundsException ioob) {
      // this is what we want.
    }
  }


  /**
   * Test method for {@link edu.ics211.h05.SortedCheeseList#remove(int)}.
   */
  @Test
  public void testRemove() {
    SortedCheeseList list = new SortedCheeseList(fatC);
    // try a bad value
    try {
      list.remove(0);
      fail("Can't remove from an empty list");
    } catch (IndexOutOfBoundsException ioob) {
      // this is what we want
    }
    list.add(inFatOrder[4]);
    Cheese removed = list.remove(0);
    assertEquals("Got wrong removed value", inFatOrder[4], removed);
    assertEquals("Didn't decrement size", 0, list.size());
  }

}