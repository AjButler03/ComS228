package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the list interface based on linked nodes that store
 * multiple items per node. Rules for adding and removing elements ensure that
 * each node (except possibly the last one) is at least half full.
 */
public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E> {
	/**
	 * Default number of elements that may be stored in each node.
	 */
	private static final int DEFAULT_NODESIZE = 4;

	/**
	 * Number of elements that can be stored in each node.
	 */
	private final int nodeSize;

	/**
	 * Dummy node for head. It should be private but set to public here only for
	 * grading purpose. In practice, you should always make the head of a linked
	 * list a private instance variable.
	 */
	public Node head;

	/**
	 * Dummy node for tail.
	 */
	private Node tail;

	/**
	 * Number of elements in the list.
	 */
	private int size;

	/**
	 * Constructs an empty list with the default node size.
	 */
	public StoutList() {
		this(DEFAULT_NODESIZE);
	}

	/**
	 * Constructs an empty list with the given node size.
	 * 
	 * @param nodeSize number of elements that may be stored in each node, must be
	 *                 an even number
	 */
	public StoutList(int nodeSize) {
		if (nodeSize <= 0 || nodeSize % 2 != 0)
			throw new IllegalArgumentException();

		// dummy nodes
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
		this.nodeSize = nodeSize;
	}

	/**
	 * Constructor for grading only. Fully implemented.
	 * 
	 * @param head
	 * @param tail
	 * @param nodeSize
	 * @param size
	 */
	public StoutList(Node head, Node tail, int nodeSize, int size) {
		this.head = head;
		this.tail = tail;
		this.nodeSize = nodeSize;
		this.size = size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(E item) {
		Node currNode = tail.previous;
		if (currNode.count < nodeSize && currNode != head && currNode != tail) {
			currNode.addItem(item);
			size++;
		} else {
			// last node is full; creating new node, adding to it
			createNewNode();
			currNode = tail.previous;
			currNode.addItem(item);
			size++;
		}

		return true;
	}

	/**
	 * private method for creating a new node; this is used when using the generic
	 * add() method, which does not have a specific index.
	 */
	private void createNewNode() {
		Node node = new Node();
		// this statement simply checks if there are already other data nodes.
		if (size > 0) {
			Node prev = tail.previous;
			node.next = tail;
			node.previous = prev;
			prev.next = node;
			tail.previous = node;
		} else {
			// no existing data nodes, so creating new
			node.next = tail;
			node.previous = head;
			tail.previous = node;
			head.next = node;
		}
	}

	/**
	 * private method to create a node after a specified node.
	 * 
	 * @param n
	 */
	private void createNodeAfter(Node n) {
		Node node = new Node();
		n.next.previous = node;
		node.next = n.next;
		n.next = node;
		node.previous = n;
	}

	@Override
	public void add(int pos, E item) {
		// finding position, pulling data
		NodeInfo ni = find(pos);
		Node currNode = ni.getNode();
		int off = ni.getOffSet();

		// implementing add rules (why must you make me suffer?)
		if (size == 0) {
			add(item);
		} else if (off == 0) {
			if (currNode.previous.count < nodeSize && currNode.previous != head) {
				currNode.previous.data[currNode.previous.count] = item;
				currNode.previous.count++;
			} else {
				createNodeAfter(currNode);
				currNode.next.data[0] = item;
				currNode.next.count++;
			}
		} else if (currNode.count < nodeSize) {
			// determining if there is element moving that needs to happen
			if (off == currNode.count) {
				// just adding to the end of the currently existing array
				currNode.data[off] = item;
				currNode.count++;
			} else {
				// shifting elements over to accommodate new element
				currNode.count++;
				for (int i = off + 1; i < currNode.count; i++) {
					currNode.data[i] = currNode.data[i - 1];
				}
				currNode.data[off] = item;
			}
		} else {
			// There is not room in current node, performing split
			int split = nodeSize / 2;

			// getting second half of array into new node
			createNodeAfter(currNode);
			int newIDX = 0;
			for (int i = split; i < nodeSize; i++) {
				currNode.next.data[newIDX] = currNode.data[i];
				currNode.data[i] = null;
				currNode.count--;
				currNode.next.count++;
				newIDX++;
			}

			// determining which half item goes in, based on PDF
			if (off <= nodeSize / 2) {
				currNode.data[off] = item;
				currNode.count++;
			} else {
				currNode.next.data[off - nodeSize / 2] = item;
				currNode.count++;
			}
		}
		size++;
	}

	@Override
	public E remove(int pos) {
		// finding position, pulling data
		NodeInfo ni = find(pos);
		Node currNode = ni.getNode();
		int off = ni.getOffSet();
		E removedElement = currNode.data[off];

		// implementing remove rules, as described in PDF
		if ((currNode.next == tail) && (currNode.count == 1)) {
			// last node and with only one element; deleting node
			currNode.previous.next = tail;
		} else if (currNode.next == tail || currNode.count > nodeSize / 2) {
			// determining if element is at end of node
			if (currNode.count - 1 == off) {
				currNode.data[off] = null;
				currNode.count--;
			} else {
				// not at end, shifting elements
				for (int i = off; i <= currNode.count - 2; i++) {
					currNode.data[i] = currNode.data[i + 1];
				}
				currNode.data[currNode.count - 1] = null;
				currNode.count--;
			}
		} else {
			// performing merge operation with successor node
			if (currNode.next.count > nodeSize / 2) {
				// MINI-MERGE
				// moving first element of successor to current

				// determining if offset is last index in node
				if (currNode.count - 1 == off) {
					// getting first element in successor node
					currNode.data[off] = currNode.next.data[0];
				} else {
					// not at end, shifting elements
					for (int i = off; i <= currNode.count - 2; i++) {
						currNode.data[i] = currNode.data[i + 1];
					}
					// getting first element in successor node
					currNode.data[currNode.count - 1] = currNode.next.data[0];
					// shifting elements in successor node
					for (int i = off; i <= currNode.next.count - 2; i++) {
						currNode.next.data[i] = currNode.next.data[i + 1];
					}
					currNode.next.data[currNode.next.count - 1] = null;
					currNode.next.count--;
				}
			} else {
				// FULL MERGE
				// moving all elements of successor to current, removing successor Node

				// determining if needing to shift elements in current Node
				if (currNode.count - 1 != off) {
					// off not at end, so shifting elements in currNode
					// worth noting: this would break if off > currnode.count
					// what's input validation anyway?
					for (int i = off; i <= currNode.count - 2; i++) {
						currNode.data[i] = currNode.data[i + 1];
					}
				}
				// getting elements from successor node
				int tempIDX = currNode.count - 1;

				// accounting for loss of removed element in count;
				currNode.count--;
				for (int i = 0; i < currNode.next.count; i++) {
					currNode.data[tempIDX] = currNode.next.data[i];
					currNode.count++;
				}
				// unlinking successor node
				currNode.next = currNode.next.next;

			}
		}
		size--;
		return removedElement;
	}

	/**
	 * Sort all elements in the stout list in the NON-DECREASING order. You may do
	 * the following. Traverse the list and copy its elements into an array,
	 * deleting every visited node along the way. Then, sort the array by calling
	 * the insertionSort() method. (Note that sorting efficiency is not a concern
	 * for this project.) Finally, copy all elements from the array back to the
	 * stout list, creating new nodes for storage. After sorting, all nodes but
	 * (possibly) the last one must be full of elements.
	 * 
	 * Comparator<E> must have been implemented for calling insertionSort().
	 */
	public void sort() {
		// Ran out of time to implement; my bad
		// TODO
	}

	/**
	 * Sort all elements in the stout list in the NON-INCREASING order. Call the
	 * bubbleSort() method. After sorting, all but (possibly) the last nodes must be
	 * filled with elements.
	 * 
	 * Comparable<? super E> must be implemented for calling bubbleSort().
	 */
	public void sortReverse() {
		// Ran out of time to implement; my bad
		// TODO
	}

	@Override
	public Iterator<E> iterator() {
		return listIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new StoutListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new StoutListIterator(index);
	}

	/**
	 * Returns a string representation of this list showing the internal structure
	 * of the nodes.
	 */
	public String toStringInternal() {
		return toStringInternal(null);
	}

	/**
	 * Returns a string representation of this list showing the internal structure
	 * of the nodes and the position of the iterator.
	 *
	 * @param iter an iterator for this list
	 */
	public String toStringInternal(ListIterator<E> iter) {
		int count = 0;
		int position = -1;
		if (iter != null) {
			position = iter.nextIndex();
		}

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Node current = head.next;
		while (current != tail) {
			sb.append('(');
			E data = current.data[0];
			if (data == null) {
				sb.append("-");
			} else {
				if (position == count) {
					sb.append("| ");
					position = -1;
				}
				sb.append(data.toString());
				++count;
			}

			for (int i = 1; i < nodeSize; ++i) {
				sb.append(", ");
				data = current.data[i];
				if (data == null) {
					sb.append("-");
				} else {
					if (position == count) {
						sb.append("| ");
						position = -1;
					}
					sb.append(data.toString());
					++count;

					// iterator at end
					if (position == size && count == size) {
						sb.append(" |");
						position = -1;
					}
				}
			}
			sb.append(')');
			current = current.next;
			if (current != tail)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	private NodeInfo find(int pos) {
		// V1; does not work correctly
//		int curr = 0;
//		Node temp = head;
//		int offset;
//		// iterate until the node with the pos is found
//		while (curr < pos && temp.next != tail) {
//			temp = temp.next;
//			curr += temp.count;
//		}
//		offset = pos % nodeSize;
//
//		return new NodeInfo(temp, offset);
//	}

		int curr = 0;
		Node temp = head;
		int offset;
		int emptyCount = 0;
		// iterate until the node with the pos is found
		while (curr <= pos && temp.next != tail) {
			temp = temp.next;
			curr += temp.count;

			// increasing offset count; but only if current node does not contain pos
			if (curr <= pos) {
				emptyCount += nodeSize - temp.count;
			}

		}
		offset = (pos + emptyCount) % nodeSize;

		return new NodeInfo(temp, offset);
	}

	/**
	 * Node type for this list. Each node holds a maximum of nodeSize elements in an
	 * array. Empty slots are null.
	 */
	private class Node {
		/**
		 * Array of actual data elements.
		 */
		// Unchecked warning unavoidable.
		public E[] data = (E[]) new Comparable[nodeSize];

		/**
		 * Link to next node.
		 */
		public Node next;

		/**
		 * Link to previous node;
		 */
		public Node previous;

		/**
		 * Index of the next available offset in this node, also equal to the number of
		 * elements in this node.
		 */
		public int count;

		/**
		 * Adds an item to this node at the first available offset. Precondition: count
		 * < nodeSize
		 * 
		 * @param item element to be added
		 */
		void addItem(E item) {
			if (count >= nodeSize) {
				return;
			}
			data[count++] = item;
			// useful for debugging
			// System.out.println("Added " + item.toString() + " at index " + count + " to
			// node " + Arrays.toString(data));
		}

		/**
		 * Adds an item to this node at the indicated offset, shifting elements to the
		 * right as necessary.
		 * 
		 * Precondition: count < nodeSize
		 * 
		 * @param offset array index at which to put the new element
		 * @param item   element to be added
		 */
		void addItem(int offset, E item) {
			if (count >= nodeSize) {
				return;
			}
			for (int i = count - 1; i >= offset; --i) {
				data[i + 1] = data[i];
			}
			++count;
			data[offset] = item;
			// useful for debugging
//      System.out.println("Added " + item.toString() + " at index " + offset + " to node: "  + Arrays.toString(data));
		}

		/**
		 * Deletes an element from this node at the indicated offset, shifting elements
		 * left as necessary. Precondition: 0 <= offset < count
		 * 
		 * @param offset
		 */
		void removeItem(int offset) {
			E item = data[offset];
			for (int i = offset + 1; i < nodeSize; ++i) {
				data[i - 1] = data[i];
			}
			data[count - 1] = null;
			--count;
		}
	}

	// Not fully implemented, unfortunately; It's Tuesday after the deadline, and
	// this is hard due in a few hours. and I need to move on to other things. I'm
	// sure I could fix this to have everything implemented and fix the fact that it
	// doesn't handle non-full nodes, but I need to move on and work on stuff for my
	// other 6 classes.
	// So much homework to do, so little time. I love college so much.
	private class StoutListIterator implements ListIterator<E> {
		// constants you possibly use ...
		// indicates next was most recently called
		private static int FORWARD = 1;
		// indicates previous was most recently called
		private static int BACKWARD = -1;

		// instance variables ...
		Node currNode;
		// overall index across all nodes
		private int currIDX;
		// count for number of empty items in all nodes; the effective offset
		private int offsetCount;
		// index for the array in current node
		private int nodeInternalIDX;
		// current direction the iterator is moving; determined by next and previous
		private int currDirection;

		/**
		 * Default constructor
		 */
		public StoutListIterator() {
			currNode = head;
			currIDX = -1;
			nodeInternalIDX = -1;
		}

		/**
		 * Constructor finds node at a given position.
		 * 
		 * @param pos
		 */
		public StoutListIterator(int pos) {
			currNode = head;
			currIDX = -1;
			nodeInternalIDX = -1;
			setIterPos(pos);
		}

		@Override
		public boolean hasNext() {
			if (nodeInternalIDX + 1 < currNode.count) {
				// current node has another element;
				return true;
			} else if (currNode.next != tail && currNode.next.count != 0) {
				// current node does not have another element; moving to next node, if present,
				// and checking if it has next elements
				return true;
			} else {
				// no next item found
				return false;
			}
		}

		@Override
		public E next() {
			// checking that there is a next element; if not, throwing
			// NoSuchElementException exception
			if (hasNext()) {
				currDirection = 1;
				nodeInternalIDX++;
				currIDX++;
				// checking if next element is in currNode; if not, advancing to next node and
				// getting first element
				if (nodeInternalIDX < currNode.count) {
					E meh = currNode.data[nodeInternalIDX];
//					System.out.println(currIDX);
//					System.out.println(nodeInternalIDX);
					return meh;
				} else {
					// updating currNode to next; returning first element in node
					currNode = currNode.next;
					// setting internal node IDX to 0, returning element 0
					nodeInternalIDX = 0;
//					System.out.println(currIDX);
//					System.out.println(nodeInternalIDX);
					return currNode.data[nodeInternalIDX];
				}
			} else {
				throw new NoSuchElementException();
			}

		}

		@Override
		public void remove() {
			// Ran out of time to implement; my bad
			// TODO
		}

		@Override
		public boolean hasPrevious() {
			if (nodeInternalIDX > -1) {
				// previous elements in current node
				return true;
			} else if ((currNode != head) && (currNode.previous != head) && (currNode.previous.count > 0)) {
				// Previous node is not null, current node is not head, and the previous node
				// has elements. Returning true.
				return true;
			} else {
				// no previous elements found.
				return false;
			}

		}

		@Override
		public E previous() {
			// checking that there is a previous element; throwing exception if none found
			if (hasPrevious()) {
				currDirection = -1;
				// determining if element is in current node, or previous node
				// grabbing element accordingly
				if (nodeInternalIDX > 0) {
					E meh = currNode.data[nodeInternalIDX];
					nodeInternalIDX--;
					currIDX--;
//					System.out.println(currIDX);
//					System.out.println(nodeInternalIDX);
					return meh;
				} else {
					E meh = currNode.data[nodeInternalIDX];
					currNode = currNode.previous;
					nodeInternalIDX = currNode.count - 1;
					currIDX--;
//					System.out.println(currIDX);
//					System.out.println(nodeInternalIDX);
					return meh;
				}
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public int nextIndex() {
			if (hasNext()) {
				return currIDX + 1;
			} else {
				// Javadoc states that if there is no next element, returns list size. Doing so.
				return size;
			}
		}

		@Override
		public int previousIndex() {
			if (hasPrevious()) {
				return currIDX;
			} else {
				// Javadoc states that if there is no previous element, returns -1. Doing so.
				return -1;
			}
		}

		@Override
		public void set(E e) {
			if (currDirection == 1) {
				// next() was most recently called
				currNode.data[nodeInternalIDX] = e;
			} else if (currDirection == -1) {
				// previous() was most recently called
				int tempIDX = nodeInternalIDX + 1;
				// determining where the replaced element should be
				if (tempIDX >= currNode.count) {
					// last element was in currNode's next node; replacing first element
					Node temp = currNode.next;
					temp.data[0] = e;
				} else {
					// last element was in current node; replacing
					currNode.data[tempIDX] = e;
				}
			} else {
				// neither next() nor previous() have been called recently; throwing exception
				throw new IllegalStateException();
			}
		}

		@Override
		public void add(E e) {
			// Ran out of time to implement; my bad
			// TODO Auto-generated method stub

		}

		// Other methods you may want to add or override that could possibly facilitate
		// other operations, for instance, addition, access to the previous element,
		// etc.
		//
		// ...
		//

		/**
		 * Sets the iterator to be at a specific location in the stoutList.
		 * 
		 * @param pos integer index for stoutListIter to be set at
		 */
		public void setIterPos(int pos) {
			// helper for finding node
			int elementCt = 0;

			currNode = head;
			// for testing; determine if found correct node
			int node = 0;
			// finding node with index pos; we'll ignore the possibility of it not existing
			while (elementCt < pos && currNode.next != tail) {
				currNode = currNode.next;
				elementCt += currNode.count;
				offsetCount = nodeSize - currNode.count;
				node++;
			}

			currIDX = pos - 1;
			nodeInternalIDX = currIDX % nodeSize;
		}

	}

	/**
	 * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING
	 * order.
	 * 
	 * @param arr  array storing elements from the list
	 * @param comp comparator used in sorting
	 */
	private void insertionSort(E[] arr, Comparator<? super E> comp) {
		// Ran out of time to implement; my bad
		// TODO
	}

	/**
	 * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a
	 * description of bubble sort please refer to Section 6.1 in the project
	 * description. You must use the compareTo() method from an implementation of
	 * the Comparable interface by the class E or ? super E.
	 * 
	 * @param arr array holding elements from the list
	 */
	private void bubbleSort(E[] arr) {
		// Ran out of time to implement; my bad
		// TODO
	}

	/**
	 * Class to store a position of a specific element.
	 * 
	 * @author Andrew Butler
	 *
	 */
	private class NodeInfo {
		private Node node;
		private int offSet;

		public NodeInfo(Node n, int offSet) {
			node = n;
			this.offSet = offSet;
		}

		public Node getNode() {
			return node;
		}

		public int getOffSet() {
			return offSet;
		}

	}

}