/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		int counter = 0;
		Node current = first;
		while (current != null) {
			if (counter == index)
			break;
			counter++;
			current=current.next;
		}
		return current;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		Node addedNode = new Node(block);
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		if (index == 0) { // Insert the node first
			addedNode.next = first;
			first = addedNode;
			if (size == 0) // Edge Case: List is Empty
			last = addedNode;
		}
		else if (index == size) { // Insert the node last
			if (last != null)
			last.next = addedNode;
			last = addedNode;
			if (size == 0) // Edge Case: List is empty
			first = addedNode;
		}

		else {
			Node current = first;
			int counter = 0;
			while (counter < index-1) {
				current = current.next;
				counter++;
			}
			addedNode.next = current.next;
			current.next = addedNode;
		}
		size++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node addedNode = new Node(block);
		if (size == 0) { // Edge Case: List is empty
			first = addedNode;
			last = addedNode;
		}
		else {
			last.next = addedNode;
			last = addedNode;
		}
		size++;
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node addedNode = new Node(block);
		if (size == 0) { // Edge Case: List is empty
			first = addedNode;
			last = addedNode;
		} else {
			addedNode.next = first;
			first = addedNode;
		}
		size++;
	}
	

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (index < 0 || index >=size)
		throw new IllegalArgumentException("index must be between 0 and size");
		return getNode(index).block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		if (size == 0) //Edge Case: List is empty
		return -1;
		Node current = first;
		int index = 0;
		while (current != null) {
			if (current.block == block)
			return index;
			index++;
			current = current.next;
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		//Edge Cases: List is empty, or node is null
		if (node == null)
		throw new NullPointerException("node must not be null");
		if (size == 0) 
		return;

		int index = indexOf(node.block);
		if (index == -1) //Edge Case: "node" is not present in the list
		return;
		if (index == 0) { //Edge Case: "node" is the first in the list
			first = first.next;
			if (size == 1) //Edge Case: "node" was the only node in the list
			last = null;
		}

		else {
			int counter = 0;
			Node current = first;
			while (counter < index - 1) {
				current = current.next;
				counter++;
			}
			current.next = node.next;
			if (node == last) { //Edge Case: Updating "last" if the node removed was the last one.
				last = current;
			}
		}
		size--;
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index < 0 || index >=size)
		throw new IllegalArgumentException("index must be between 0 and size");

		remove(getNode(index));
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		int index = indexOf(block);
		if (index == -1)
		throw new IllegalArgumentException("index must be between 0 and size");

		remove(index);
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		String results = "";
		Node current = this.first;
		while (current != null) {
			results += current.block.toString() + " ";
			current = current.next;
		}
		return results;
	}
}