/*
 * SimpleOrderedDynamicArrayMap
 *
 * Version 1.0
 *
 * 2015-06-25
 *  
 * (c) Michael Nahas
 *
 * CTY 2015 DATA LAN
 * 
 * Implement the Map interface using a linked list
 *
 */

import java.util.Map;  // uses Map.Entry<K,V>
import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Comparator;


public class SimpleOrderedDynamicArrayMap<K,V> 
    implements Iterable<Map.Entry<K,V>>, SimpleMap<K,V> {

    SimpleDynamicArray< Map.Entry<K,V> > array;
    Comparator<K> ordering;
    public SimpleOrderedDynamicArrayMap(Comparator<K> o){
	array = new SimpleDynamicArray();
	ordering = o;
    }


    public int size() {
	return array.size();
    }


    boolean isLessThan(K k1, K k2) {
	if (ordering != null) {
	    return ordering.compare(k1, k2) < 0;
	}
	else {
	    // assume they are Comparable
	    return ((Comparable<K>) k1).compareTo(k2) < 0;
	}
    }


    // This is a private helper function.
    // The other functions call it to locate the Entry for a given key
    private int find(K key) {
    	int x= binarySearch(0,array.size(),key);
    	if(x == array.size()){
    		return -1;
    	}else{
    		return x;
    	}
    	
    	
    	/*for (int i = 0; i < array.size(); i++) {
	    Map.Entry<K,V> entry = array.get(i);
	    if (!isLessThan(key, entry.getKey())) {
		return i;
	    }
		}
	    */

    }
    
    private int binarySearch(int leftIndex,int rightIndex,K value){
		if(leftIndex == rightIndex){
			return rightIndex;
		}
		else if(leftIndex +1 == rightIndex){
			 Map.Entry<K,V> entry = array.get(leftIndex);
			if(value.equals(array.get(leftIndex).getKey()) ||isLessThan(value, entry.getKey())){
				return leftIndex;
			}
			else{
				return rightIndex;
			}
		}
		else{
			int midpoint = (rightIndex + leftIndex)/2;
			Map.Entry<K,V> entry = array.get(midpoint);
			if(value.equals(array.get(leftIndex).getKey()) ||isLessThan(value, entry.getKey())){
				return binarySearch(leftIndex,midpoint,value);
			}
			else{
				return binarySearch(midpoint,rightIndex,value);
			}
		}
    	
    	
    	
    }


    // NOTE: cannot use "return get(key) != null"
    // because the key may be in the table and mapped to null.
    public boolean containsKey(K key) {
	// We could support a null key, but easier not to.
	if (key == null) 
	    throw new NullPointerException();

	int index = find(key);
	if (index >= 0) {
	    Map.Entry<K,V> entry = array.get(index);
	    if (key.equals(entry.getKey())) {
		return true;
	    }
	}

	return false;
    }


    public V put(K key, V value) {
	// We could support a null key, but easier not to.
	if (key == null) 
	    throw new NullPointerException();

	int index = find(key);
	if (index >= 0) {
	    Map.Entry<K,V> entry = array.get(index);
	    if (key.equals(entry.getKey())) {
		return entry.setValue(value);
	    }
	    else {
		array.add(index, new Pair<K,V>(key, value));
		return null;
	    }
	}
	else {
	    array.add(array.size(), new Pair<K,V>(key, value));
	    return null;
	}
    }


    public V get(K key) {
	// We could support a null key, but easier not to.
	if (key == null) 
	    throw new NullPointerException("hj");

	int index = find(key);
	System.out.println(index);
	if (index >= 0) {
	    Map.Entry<K,V> entry = array.get(index);
	    if (key.equals(entry.getKey())) {
		return entry.getValue();
	    }
	}

	return null;
    }


    // remove() cannot call find(), because it needs
    // to call remove on the iterator.
    public V remove(Object key) {
	// We could support a null key, but easier not to.
	if (key == null) 
	    throw new NullPointerException("asdfads");

	int index = find((K) key);
	if (index >= 0) {
	    Map.Entry<K,V> entry = array.get(index);
	    if (key.equals(entry.getKey())) {
		// remove this entry and put last in its place.
		array.set(index, array.get(array.size()-1));
		array.remove(array.size()-1);
		return entry.getValue();
	    }
	}

	// key not present in array
	// ? Should we throw an exception in this case?
	return null;
    }

    public Iterator<Map.Entry<K,V>> iterator() {
	return array.iterator();
    }
}
