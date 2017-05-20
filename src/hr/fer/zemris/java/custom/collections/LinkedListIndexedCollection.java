package hr.fer.zemris.java.custom.collections;

/**
 * Represents a linked list-backed collection of objects.
 * Duplicate elements are allowed (each of those elements will be held in 
 * different list node); storage of null references is not allowed.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class LinkedListIndexedCollection extends Collection{
	
	/**
	 * Structure which represents one node of the list.
	 * @author Mislav Gillinger
	 * @version 1.0
	 */
	private static class ListNode{
		/** Value of the element stored in a node. */
		Object value;
		/** Reference to a previous node. */
		ListNode previous;
		/** Reference to a next node. */
		ListNode next;
	}
	
	/** Number of elements stored. */
	private int size;
	/** Reference to a first node. */
	private ListNode first;
	/** Reference to a last node. */
	private ListNode last;
	
	/**
	 * Default constructor for class LinkedListIndexedCollection.
	 */
	public LinkedListIndexedCollection(){
		first = null;
		last = null;
	}
	
	/**
	 * Constructor which adds all elements of the given other Collection to 
	 * its own Collection.
	 * @param other Reference to some other collection which elements are copied
	 * 		into this newly constructed collection.
	 */
	public LinkedListIndexedCollection(Collection other){
		addAll(other);
	}
	
	/**
	 * Counts how many objects are stored in this collection.
	 * @return The number of currently stored objects in this collection.
	 */
	@Override
	public int size(){
		return size;
	}
	
	/**
	 * Adds the given object into this collection at the end of collection.
	 * Complexity is O(1).
	 * @param value Value to be added to collection. Cannot be null.
	 * @throws IllegalArgumentException when argument is null
	 */
	@Override
	public void add(Object value){
		if(value == null){
			throw new IllegalArgumentException("Argument must not be null!");
		}
		
		ListNode temp = new ListNode();
		temp.value = value;
		temp.previous = last;
		temp.next = null;
		
		if(first == null && last == null){
			first = temp;
			last = temp;
		}
		else{
			last = temp;
			last.previous.next = last;
		}
		size++;
	}
	
	/**
	 * Checks whether this collection contains the given value as determined
	 * 		by equals method.
	 * @param value Object to be checked if contained in this collection.
	 * 		It is OK to ask if collection contains null.
	 * @return True only if the collection contains given value as determined
	 * 		by equals method.
	 */
	@Override
	public boolean contains(Object value){
		for(ListNode temp = first; temp != null; temp = temp.next){
			if(temp.value.equals(value)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks whether this collection contains the given value as determined
	 * 		by equals method, and removes first occurrence of it.
	 * @param value Object to be removed from this collection, if contained.
	 * @return True only if the collection contains given value as determined
	 * 		by equals method, and succeeds to remove first occurrence of it.
	 */
	@Override 
	public boolean remove(Object value){
		for(ListNode temp = first; temp != null; temp = temp.next){
			if(temp.value.equals(value)){
				if(temp == first){
					first.next.previous = null;
					first = first.next;
				}
				else if(temp == last){
					last.previous.next = null;
					last = last.previous;
				}
				else{
					temp.previous.next = temp.next;
					temp.next.previous = temp.previous;
				}
				size--;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Allocates new array with size equals to the size of this collection and 
	 * fills it with collection content.
	 * @return The newly made array. Never returns null.
	 */
	@Override
	public Object[] toArray(){
		Object[] newArray = new Object[size];
		int i = 0;
		
		for(ListNode temp = first; temp != null; temp = temp.next){
			newArray[i++] = temp.value;
		}
		
		return newArray;
	}
	
	/**
	 * Calls argument's method process for each element of this collection.
	 * @param processor Argument whose method process is called for each
	 * 		element of this collection.
	 */
	@Override
	public void forEach(Processor processor){
		for(ListNode temp = first; temp != null; temp = temp.next){
			processor.process(temp.value);
		}
	}
	
	/**
	 * Removes all elements from the collection.
	 */
	@Override
	public void clear(){
		first = null;
		size = 0;
	}
	
	/**
	 * Returns the object that is stored in linked list at position index.
	 * Worst case complexity is O(n/2+1).
	 * @param index Element at this index will be returned. 
	 * 		Valid indexes are 0 to size-1.
	 * @return Object stored at given index.
	 * @throws IndexOutOfBoundsException when index is invalid
	 */
	public Object get(int index){
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException("Valid indexes are from 0 to"
					+ " size-1!");
		}
		
		ListNode temp;
		
		if(((size-1) / 2) >= index){
			temp = first;
			for(int i = 0; i < index; i++){
				temp = temp.next;
			}
			return temp.value;
		}
		else{
			temp = last;
			for(int i = size-1; i > index; i--){
				temp = temp.previous;
			}
			return temp.value;
		}
	}
	
	/**
	 * Inserts the given value at the given position in linked-list. Elements
	 * starting from this position are shifted one position.
	 * Average complexity is O(n/2).
	 * @param value Value to be inserted.
	 * @param position The given value will be inserted on this position.
	 * 		The legal positions are 0 to size.
	 * @throws IndexOutOfBoundsException if position is invalid
	 * @throws IllegalArgumentException if value is null
	 */
	public void insert(Object value, int position){
		if(position < 0 || position > size){
			throw new IndexOutOfBoundsException("Invalid index!");
		}
		if(value == null){
			throw new IllegalArgumentException("Argument must not be null!");
		}
		
		ListNode newNode = new ListNode();
		newNode.value = value;
		
		if(position == 0){
			newNode.previous = null;
			newNode.next = first;
			first.previous = newNode;
			first = newNode;
		}
		else if(position == size){
			newNode.previous = last;
			newNode.next = null;
			last.next = newNode;
			last = newNode;
		}
		else{
			ListNode temp = first;
			
			for(int i = 0; i < position; i++){
				temp = temp.next;
			}
			
			newNode.previous = temp.previous;
			newNode.next = temp;
			temp.previous.next = newNode;
			temp.previous = newNode;
		}
		size++;
	}
	
	/**
	 * Searches the collection and returns the index of the first occurrence of 
	 * the given value or -1 if the value is not found. The equality is  
	 * determined using the equals method. Worst case complexity is O(n/2+1).
	 * @param value The value to be searched for.
	 * @return The index of the first occurrence of the given value or -1 if 
	 * the value is not found.
	 */
	public int indexOf(Object value){
		ListNode beginning = first, end = last;
		
		for(int i = 0, j = size-1; i<=j; i++, j--){
			if(beginning.value.equals(value)) return i;
			else if(end.value.equals(value)) return j;
			beginning = beginning.next;
			end = end.previous;
		}
		return -1;
	}
	
	/**
	 * Removes element at specified index from collection. Element that was 
	 * previously at location index+1 after this operation 
	 * is on location index, etc.
	 * @param index Element at this index will be removed.
	 * 		Legal indexes are 0 to size-1.
	 * @throws IndexOutOfBoundsException if index is less than 0 or more 
	 * 		than size-1
	 */
	public void remove(int index){
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException("Invalid index!");
		}
		if(first == last){
			first = null;
			last = null;
		}
		else if(index == 0){
			first.next.previous = null;
			first = first.next;
		}
		else if(index == size-1){
			last.previous.next = null;
			last = last.previous;
		}
		else{
			ListNode temp = first;
			
			for(int i = 0; i < index; i++){
				temp = temp.next;
			}
			
			temp.previous.next = temp.next;
			temp.next.previous = temp.previous;
		}
		size--;
	}
}
