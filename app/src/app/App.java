/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author user
 */
public class App {
 
  /** Display the contents of the list. */
  public static void print(int[] list) {
    for(int i = 0; i < list.length; i++) {
      System.out.print(list[i] + " ");
    }
    System.out.println();
  }
  
  /**
   * Bubble sort:
   * 
   * - traverse the list comparing pairs of elements
   * - move the larger element into the higher position
   * - repeat list.length times
   */ 
  public static void bubbleSort(int[] list) {
    int comps = 0;
    int swaps = 0;
    
    for(int j = 0; j < list.length - 1; j++) {
      for(int i = 0; i < list.length - 1; i++) {
        
        // count the number of comparisons
        comps++;
        
        // compare neighboring elements
        if(list[i] > list[i+1]) {
          
          // swap i with i+1
          int temp = list[i];
          list[i] = list[i + 1];
          list[i + 1] = temp;
          
          // count number of swaps
          swaps++;
        }
      }
    }
    print(list);
    System.out.println("Comparisons: " + comps 
                         + " Swaps: " + swaps);
  }
  
  /**
   * Improved bubble sort:
   *  - stop sorting when the array becomes sorted
   *  - do not compare elements of the array with
   *    the elements that are in their correct sorted
   *    position
   */ 
  public static void bubbleSort2(int[] list) {
    int comps = 0;
    int swaps = 0;
    boolean didSwap = true;
    
    // didSwap tracks whether any elements of the array
    // were swapped during the last iteration of the loop.
    // If no elements were swapped, the the array is 
    // sorted, so we can stop.
    for(int j = 0; j < list.length - 1 && didSwap; j++) {
      didSwap = false;
      
      // at this point, j elements are already in their
      // correct sorted position, so we do not compare
      // with the last j elements in the list
      for(int i = 0; i < list.length - 1 - j; i++) {
          
        // count the number of comparisons
        comps++;
        
        // compare neighboring elements
        if(list[i] > list[i+1]) {
          
          didSwap = true;
          
          // swap i with i+1
          int temp = list[i];
          list[i] = list[i + 1];
          list[i + 1] = temp;
          
          // count number of swaps
          swaps++;
        }
      }
    }
    print(list);
    System.out.println("Comparisons: " + comps 
                         + " Swaps: " + swaps);
  }
  
  /**
   * Insertion sort:
   * - pick an element and put it into its correct
   * position in the sorted part of the list
   * - repeat list.length times
   */ 
  public static void insertionSort(int[] list) {
    int comps = 0, swaps = 0;
    
    for(int i = 1; i < list.length; i++) {
    
      int j = i;      
            
      // compare i with sorted elements and insert it
      // sorted elements: [0..i-1]
      while (j > 0 && list[j] < list[j - 1]) {
        
        int temp = list[j];
        list[j] = list[j - 1];
        list[j - 1] = temp;
        
        swaps++;
        comps++;  // loop condition true
         
        j--;
      }
      comps++; // checking loop condition when false
    }
    print(list);
    
    System.out.println("Comparisons: " + comps 
                         + " Swaps: " + swaps);
  }
}
