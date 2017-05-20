package hr.fer.zemris.java.custom.collections;

/**
 * Represents some general collection of objects.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class Collection {
	
	/**
	 * Default constructor for class Collection.
	 */
	protected Collection(){
		
	}
	
	/**
	 * Checks whether a collection contains any objects.	
	 * @return True if collection contains no objects and false otherwise.
	 */
	public boolean isEmpty(){
		if(size() == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * Counts how many objects are stored in this collection.
	 * @return The number of currently stored objects in this collection.
	 */
	public int size(){
		return 0;
	}
	
	/**
	 * Adds the given object into this collection.
	 * @param value Object to be added into this collection.
	 */
	public void add(Object value){
		
	}
	
	/**
	 * Checks whether this collection contains the given value as determined
	 * 		by equals method.
	 * @param value Object to be checked if contained in this collection.
	 * 		It is OK to ask if collection contains null.
	 * @return True only if the collection contains given value as determined
	 * 		by equals method.
	 */
	public boolean contains(Object value){
		return false;
	}
	
	/**
	 * Checks whether this collection contains the given value as determined
	 * 		by equals method, and removes one occurrence of it (in this class
	 * 		it is not specified which one).
	 * @param value Object to be removed from this collection, if contained.
	 * @return True only if the collection contains given value as determined
	 * 		by equals method, and succeeds to remove one occurrence of it(in
	 * 		this class it is not specified which one).
	 */
	public boolean remove(Object value){
		return false;
	}
	
	/**
	 * Allocates new array with size equals to the size of this collection and 
	 * fills it with collection content.
	 * @return The newly made array. Never returns null.
	 */
	public Object[] toArray(){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Calls argument's method process for each element of this collection.
	 * @param processor Argument whose method process is called for each
	 * 		element of this collection.
	 */
	public void forEach(Processor processor){
		
	}
	
	/**
	 * Adds all elements of the given collection into this collection.
	 * @param other Other collection whose elements will be added. Remains 
	 * 		unchanged.
	 */
	public void addAll(Collection other){
		if(other == null) return;
		
		Processor processor = new Processor(){
			@Override
			public void process(Object value){
				add(value);
			}
		};
		
		other.forEach(processor);
	}
	
	/**
	 * Removes all elements from this collection.
	 */
	public void clear(){
		
	}
}
