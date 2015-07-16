/*
 * Sorting
 *
 * Version 1.0
 *
 * 2015-07-14
 *  
 * (c) Michael Nahas
 *
 * CTY 2015 DATA LAN
 * 
 * A file to organize sorting functions.
 */

import java.lang.UnsupportedOperationException;

import java.util.Random;


class Sorting {
    
    public static void shuffle(int[] array) {
	Random r = new Random();

	for (int i = 0; i < array.length; i++) {
	    int swap_index = i + r.nextInt(array.length - i);
	    int temp = array[i];
	    array[i] = array[swap_index];
	    array[swap_index] = temp;
	}
    }

    public static void bubbleSort(int[] array) {
	throw new UnsupportedOperationException();
    }

    public static void insertionSort(int[] array) {
	throw new UnsupportedOperationException();
    }

    public static void selectionSort(int[] array) {
    	
    
    	for(int i=0; i<array.length;i++){
    		int smallest = i;
    		int count = array[i];
    		for(int z=i; z<array.length;z++){
    			if(array[z] <count){
    				   count = array[z]; 	
    				   smallest = z;
    			}
    			
				

    		}
    		array[smallest] = array[i];
			array[i] = count;
    	}
    	
    }

    // USE partition() FUNCTION BELOW
    public static void quickSort(int[]array){
    	quickSortRecursive(array,0,array.length);
    }
    public static void quickSortRecursive(int[] array,int start, int end) {
    	if(end-start<=1){
    		return;
    	}
    	int part = partition(array,start,end);
    	quickSortRecursive(array,start,part);
    	quickSortRecursive(array,part + 1,end);
    	
    	
    }

    // USE merge() FUNCTION BELOW
    public static void mergeSort(int[] array) {
	throw new UnsupportedOperationException();
    }

    // USE makeHeap() and popHeap() FUNCTIONS BELOW
    public static void heapSort(int[] array) {
	throw new UnsupportedOperationException();
    }

    public static void slowSort(int[] array) {
	try {
	    insertionSort(array);
	} catch (UnsupportedOperationException uoe) {
	    selectionSort(array);
	}
    }

    public static void fastSort(int[] array) {
	try {
	    quickSort(array);
	} catch (UnsupportedOperationException uoe) {
	    try {
		mergeSort(array);
	    } catch (UnsupportedOperationException uoe2) {
		heapSort(array);
	    }
	}
    }


    // Splits an array region into two parts, those elemements
    // above and below a "pivot".  The location of the pivot
    // is the value returned.
    //
    // the parameter "start" is inclusive and "end" is exclusive
    public static int partition(int[] array, int start, int end) {
    	
    	
    	int pivot = array[start];
    	int left = start + 1;
    	int temp;
    	int right = end - 1;
    	while(left<right){
    		while(array[left]<=pivot && left<right){
    			left++;
    		}
    		while(array[right] > pivot &&right>left){
    			right--;
    		}
    		
    		if(array[left]>= pivot && array[right]<pivot){
    			 temp = array[left];
    			array[left] = array[right];
    			array[right] = temp;
    			for(int i=0;i<array.length;i++){
    	    		System.out.println(array[i]);
    	    	}
    		}
    		
    		
    		
    	}

    	if(pivot >array[right]){
    		 temp = array[right];
    		array[right] = array[start];
    		array[start] = temp;
    		return right;
    	}else{
    		 temp = array[right-1];
    		array[right-1] = array[start];
    		array[start] = temp;
    		return right-1;
    	}
    	
    }

    // Assumes input1 and input2 are sorted from their respective starts 
    // (inclusive) to their respective ends (exclusive).
    // This function copies all the input data to the output array
    // such that the output array is sorted from start to end.
    //
    // There is no end3 parameter.  It must be equivalent to:
    //      end3 = start3 + (end1 - start1) + (end2 - start2) 
    public static void merge(int[] input1, int start1, int end1, int[] input2, int start2, int end2, int[] output, int start3 /*, end3  (see description above)*/) {
	throw new UnsupportedOperationException();
    }


    // Creates an in-array heap with the data from start (inclusive)
    // to start + length (exclusive)
    // Thus, the largest value in the range will be in array[start]
    // when it returns.
    public static void makeHeap(int[] array, int start, int length) {
	throw new UnsupportedOperationException();
    }


    // Removes the top item from the heap and modified the array
    // to restore the heap property.
    // The heap starts at size "length", but after the item
    // is returned, the heap will be size "length - 1"
    public static int popHeap(int[] array, int start, int length) {
	throw new UnsupportedOperationException();
    }
}
