/**
 * 
 */
package edu.ics211.h04;

import java.util.Comparator;

import edu.ics211.h02.Cheese;

/**
 * @author Isaac Segawa
 *
 */
public class SortedCheeseList implements IList211<Cheese> {

  private SortableList<Cheese> theCheeses;
  private Comparator<Cheese> comp;
  
  /**
   * 
   */
  public SortedCheeseList(Comparator<Cheese> comp) {
    //initialize the variables
    theCheeses = new SortableList<Cheese>();
    this.comp = comp;
  }

  @Override
  public Cheese get(int index) {
    //return theCheeses.get(index);
    return theCheeses.get(index);
  }

  @Override
  public Cheese set(int index, Cheese element) {
    /*
     * remember the cheese return
     * sort the list (any sort method) prof doesn't like selection sort
     * return remembered cheese
     */
    Cheese key = theCheeses.set(index, element);
    theCheeses.insertionSort(comp);
    return key;
  }

  @Override
  public int indexOf(Object obj) {
    return theCheeses.indexOf(obj);
  }

  @Override
  public int size() {
    return theCheeses.size();
  }

  @Override
  public boolean add(Cheese e) {
    boolean test = theCheeses.add(e);
    theCheeses.bubbleSort(comp);
    return test;
  }

  @Override
  public void add(int index, Cheese element) {
    theCheeses.add(index, element);
    theCheeses.bubbleSort(comp);
  }

  @Override
  public Cheese remove(int index) {
    return theCheeses.remove(index);
  }

}