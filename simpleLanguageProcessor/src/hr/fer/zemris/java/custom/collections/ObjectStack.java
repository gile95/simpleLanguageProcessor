package hr.fer.zemris.java.custom.collections;

/**
 * This class implements methods to work with a stack.
 * @author Mislav Gillinger
 * @version 1.0
 */

public class ObjectStack {

	/** Chosen internal storage. */
	ArrayIndexedCollection storage;
	
	/**
	 * Default constructor for class ObjectStack.
	 */
	public ObjectStack(){
		storage = new ArrayIndexedCollection();
	}
	
	/**
	 * Checks whether a collection contains any objects.	
	 * @return True if collection contains no objects and false otherwise.
	 */
	public boolean isEmpty(){
		return storage.isEmpty();
	}
	
	/**
	 * Counts how many objects are stored in this collection.
	 * @return The number of currently stored objects in this collection.
	 */
	public int size(){
		return storage.size();
	}
	
	/**
	 * Pushes the given value on stack.
	 * @param value Value to be pushed on stack. Cannot be null.
	 * @throws IllegalArgumentException when argument is null
	 */
	public void push(Object value){
		if(value == null){
			throw new IllegalArgumentException("Argument must not be null!");
		}
		
		storage.add(value);
	}
	
	/**
	 * Removes last value pushed on stack from stack and returns it.
	 * @return The last value pushed on stack.
	 * @throws EmptyStackException if stack is empty
	 */
	public Object pop(){
		if(size() == 0){
			throw new EmptyStackException("Stack is empty!");
		}
		
		Object ret = storage.get(size()-1);
		storage.remove(size()-1);
		return ret;
	}
	
	/**
	 * Returns last value pushed on stack.
	 * @return Last element placed on stack.
	 * @throws EmptyStackException if stack is empty
	 */
	public Object peek(){
		if(size() == 0){
			throw new EmptyStackException("Stack is empty!");
		}
		
		return storage.get(size()-1);
	}
	
	/**
	 * Removes all elements from stack.
	 */
	public void clear(){
		storage.clear();
	}
}
