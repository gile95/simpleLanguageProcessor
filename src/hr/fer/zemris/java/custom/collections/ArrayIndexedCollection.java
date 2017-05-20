package hr.fer.zemris.java.custom.collections;

/**
 * Represents a resizable array-backed collection of objects.
 * Duplicate elements are allowed; storage of null references is not allowed.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class ArrayIndexedCollection extends Collection{

	/** Number of elements stored */
	private int size;
	/** Number of elements that can be stored. */
	private int capacity;
	/** Array of Objects which represents an internal storage. */
	private Object[] elements; 
	/** Default capacity of internal storage. */
	private static final int defaultCapacity = 16;
	
	/**
	 * Default constructor for class ArrayIndexedCollection. Sets the
	 * capacity to defaultCapacity.
	 */
	public ArrayIndexedCollection(){
		this(null, defaultCapacity);
	}
	
	/**
	 * Constructor which allows to make a Collection with desired capacity
	 * @param initialCapacity The desired capacity of new ArrayIndexedCollection
	 * @throws IllegalArgumentException Thrown if initialCapacity is less than 1
	 */
	public ArrayIndexedCollection(int initialCapacity) throws IllegalArgumentException{
		this(null, initialCapacity);
	}
	
	/**
	 * Constructor which allows to make a Collection and copy elements of
	 * the given other Collection into it.
	 * @param other Other Collection which elements will be copied.
	 */
	public ArrayIndexedCollection(Collection other){
		this(other, defaultCapacity);
	}

	/**
	 * Constructor which allows to make a Collection with desired
	 * initialCpacity. Additionally, it can receive another Collection and
	 * copy its elements into its own.
	 * @param other Other Collection which elements will be copied.
	 * @param initialCapacity The desired capacity of the new 
	 * 		ArrayIndexedCollection.
	 * @throws IllegalArgumentException Thrown if the initial capacity is less 
	 * 		than 1
	 */
	public ArrayIndexedCollection(Collection other, int initialCapacity){
		if(initialCapacity < 1){
			throw new IllegalArgumentException("Initial capacity must be"
					+ "more than 1!");
		}
		
		capacity = initialCapacity;
		elements = new Object[capacity];
		size = 0;
		
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
	 * Adds the given object into this Collection. If the Collection is full, 
	 * the method reallocates it by doubling its size. Complexity is O(1).
	 * @param value Object to be added into this collection. Cannot be null.
	 * @throws IllegalArgumentException if the argument is null.
	 */
	@Override
	public void add(Object value){
		if(value == null){
			throw new IllegalArgumentException("Argument must not be null!");
		}	
		
		if(size == capacity){
			extendArray();
		}
		elements[size++] = value;
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
		for(int i = 0; i < size; i++){
			if(elements[i].equals(value)){
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
		for(int i = 0; i < size; i++){
			if(elements[i].equals(value)){
				for(int j = i; j < size-1; j++){
					elements[j] = elements[j+1];
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
		
		for(int i = 0; i < size; i++){
			newArray[i] = elements[i];
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
		for(int i = 0; i < size; i++){
			processor.process(elements[i]);
		}
	}
	
	/**
	 * Removes all elements from this collection. The allocated array is left
	 * at current capacity.
	 */
	@Override
	public void clear(){
		elements = new Object[capacity];
		size = 0;
	}
	
	/**
	 * Returns the object that is stored in backing array at position index.
	 * @param index Element at this index will be returned. Valid indexes are
	 * 0 to size-1. Complexity is O(1).
	 * @return Object stored at given index.
	 * @throws IndexOutOfBoundsException if index is less than 0 or more 
	 * 		than size-1 
	 */
	public Object get(int index){
		if(index < 0 || index > (size-1)){
			throw new IndexOutOfBoundsException("Valid indexes are 0 to"
					+ " size-1!");
		}
		
		return elements[index];
	}
	
	/**
	 * Inserts the given value at the given position in array.
	 * Average complexity is O(n/2).
	 * @param value Value to be inserted.
	 * @param position Position where the value will be inserted. 
	 * Valid positions are 0 to size.
	 * @throws IllegalArgumentException when argument is null
	 * @throws IndexOutOfBoundsException when the position is invalid.
	 */
	public void insert(Object value, int position){
		if(value == null){
			throw new IllegalArgumentException("Argument must not be null!");
		}
		if(position < 0 || position > size){
			throw new IndexOutOfBoundsException("Valid positions are 0"
					+ " to size!");
		}
		
		if(size == capacity){
			extendArray();
		}
		
		for(int i = size; i > position; i--){
			elements[i] = elements[i-1];
		}
		elements[position] = value;
		size++;
	}
	
	/**
	 * Searches the collection and returns the index of the first occurrence of 
	 * the given value or -1 if the value is not found. The equality is  
	 * determined using the equals method. Average complexity is O(n/2).
	 * @param value The value which index will be searched for.
	 * @return The index of the first occurrence of the given value or -1
	 * 		if the value is not found.
	 */
	public int indexOf(Object value){
		for(int i = 0; i < size; i++){
			if(elements[i].equals(value)) return i;
		}
		return -1;
	}
	
	/**
	 * Removes element at specified index from collection. 
	 * Element that was previously at location index+1 after this operation 
	 * is on location index, etc.
	 * @param index Element at this index will be removed. Valid ones are 0 to
	 * 		size-1
	 * @throws IndexOutOfBoundsException thrown if index is less than 0 or 
	 * 		more than size-1
	 */
	public void remove(int index){
		if(index < 0 || index > (size-1)){
			throw new IndexOutOfBoundsException("Valid indexes are 0"
					+ " to size-1!");
		}
		
		for(int i = index; i < size-1; i++){
			elements[i] = elements[i+1];
		}
		size--;
	}
	
	/**
	 * Method which reallocates the Collection by doubling its size.
	 */
	public void extendArray(){
		Object[] temporary = new Object[capacity];
		
		for(int i = 0; i < capacity; i++){
			temporary[i] = elements[i];
		}
		
		elements = new Object[capacity*2];
		
		for(int i = 0; i < capacity; i++){
			elements[i] = temporary[i];
		}
	}
}


