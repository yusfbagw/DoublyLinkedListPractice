/**
 * Your implementation of a non-circular DoublyLinkedList with a tail pointer.
 *
 * @author Yusuf Bagwan
 * @version 1.0
 * @userid ybagwan3 (i.e. gburdell3)
 * @GTID 903891335 (i.e. 900000000)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 * Used chatgpt to do the checkstyle for all the bracket thingies.
 * 
 * By typing 'I agree' below, you are agreeing that this is your
 * own work and that you are responsible for all the contents of 
 * this file. If this is left blank, this homework will receive a zero.
 * 
 * Agree Here: I agree
 */
public class DoublyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index. Don't forget to consider whether
     * traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The index is out of bounds.");
        } else if (data == null) {
            throw new IllegalArgumentException("The data can't be null.");
        }
        //Needed to create a straight up new Node here.
        DoublyLinkedListNode<T> block = new DoublyLinkedListNode<>(data);  // Why this have data in it though?
        if (size == 0) {
            head = block;
            tail = block;
            size++;
            return;
        } else if (index == 0) {
            //You have to add a .setNext call and a .setPrevious call cuz if you don't you lose the data
            //in the whole DLL. 
            block.setNext(head);
            head.setPrevious(block);
            head = block;
            size++;
            return;
        } else if (index == size) {
            block.setPrevious(tail);
            tail.setNext(block);
            tail = block;
            size++;
            return;
        } else {
            DoublyLinkedListNode<T> curr;
            if (index <= size / 2) {
                curr = head;
                for (int i = 0; i < index; i++) {
                    curr = curr.getNext();
                }
            } else {
                curr = tail;
                for (int i = size - 1; i > index; i--) {
                    curr = curr.getPrevious();
                }
            }
            DoublyLinkedListNode<T> prev = curr.getPrevious();
            block.setPrevious(prev);
            block.setNext(curr);
            prev.setNext(block);
            curr.setPrevious(block);
            size++;
            return;
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        addAtIndex(0, data);
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        addAtIndex(size, data);
    }

    /**
     * Removes and returns the element at the specified index. Don't forget to
     * consider whether traversing the list from the head or tail is more
     * efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified indexr
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Your index is out of bounds.");
        }
        if (index == 0) {
            DoublyLinkedListNode<T> oldHead = head;
            T data = oldHead.getData();
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                head = head.getNext();
                head.setPrevious(null);
            }
            size--;
            return data;
        } else if (index == size - 1) {
            DoublyLinkedListNode<T> oldTail = tail;
            T data = oldTail.getData();
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                tail = tail.getPrevious();
                tail.setNext(null);
            }
            size--;
            return data;
        } else {
            DoublyLinkedListNode<T> curr;
            if (index <= size / 2) {
                curr = head;
                for (int i = 0; i < index; i++) {
                    curr = curr.getNext();
                }
            } else {
                curr = tail;
                for (int i = size - 1; i > index; i--) {
                    curr = curr.getPrevious();
                }
            }
            T data = curr.getData();
            DoublyLinkedListNode<T> prev = curr.getPrevious();
            DoublyLinkedListNode<T> next = curr.getNext();
            prev.setNext(next);
            next.setPrevious(prev);
            size--;
            return data;
        }
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The list is empty.");
        }
        T data = head.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);
        }
        size--;
        return data;
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The list is empty.");
        }
        T data = tail.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
        }
        size--;
        return data;
    }

    /**
     * Returns the element at the specified index. Don't forget to consider
     * whether traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index is out of bounds.");
        }
        if (index == 0) {
            T data = head.getData();
            return data;
        } else if (index == size - 1) {
            T data = tail.getData();
            return data;
        } else {
            DoublyLinkedListNode<T> curr;
            if (index <= size / 2) {
                curr = head;
                for (int i = 0; i < index; i++) {
                    curr = curr.getNext();
                }
            } else {
                curr = tail;
                for (int i = size - 1; i > index; i--) {
                    curr = curr.getPrevious();
                }
            }
            return curr.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(1) if data is in the tail and O(n) for all other cases.
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data can't be null");
        }
        if (size == 0) {
            throw new java.util.NoSuchElementException("Data not found.");
        }
        if (data.equals(tail.getData())) {
            return removeFromBack();
        }
        DoublyLinkedListNode<T> curr = tail.getPrevious();
        while (curr != null && !data.equals(curr.getData())) {
            curr = curr.getPrevious();
        }
        if (curr == null) {
            throw new java.util.NoSuchElementException("Data not found.");
        }
        T res = curr.getData();
        if (curr == head) {
            head = head.getNext();
            if (head != null) {
                head.setPrevious(null);
            } else {
                tail = null;
            }
        } else {
            DoublyLinkedListNode<T> prev = curr.getPrevious();
            DoublyLinkedListNode<T> next = curr.getNext();
            prev.setNext(next);
            if (next != null) {
                next.setPrevious(prev);
            } else {
                tail = prev;
            }
        }
        size--;
        return res;
    }

    /**
     * Returns an array representation of the linked list. If the list is
     * size 0, return an empty array.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length size holding all of the objects in the
     * list in the same order
     */
    public Object[] toArray() {
        if (size == 0) {
            Object[] theArray = new Object[size];
            return theArray;
        } else {
            Object[] theArrayTwo = new Object[size];
            DoublyLinkedListNode<T> curr = head;
            for (int i = 0; i < size; i++) {
                theArrayTwo[i] = curr.getData();
                curr = curr.getNext();
            }
            return theArrayTwo;
        }
    }
    public int count (T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data can't be null");
        }
        int count = 0;
        DoublyLinkedListNode<T> curr = head;
        for (int i = 0; i < size; i++) {
            if (data.equals(curr.getData())) {
                count++;
            }
            curr.getNext();
        }
        return count;
    }
    public void removeOddIndices(){
        DoublyLinkedListNode<T> curr = head;
        int index = 0;
        while (curr != null) {
            DoublyLinkedListNode<T> next = curr.getNext();
            if (index % 2 == 1) {
                DoublyLinkedListNode<T> prev = curr.getPrevious();
                DoublyLinkedListNode<T> nxt = curr.getNext();
                if (prev != null) {
                    prev.setNext(nxt);
                }
                if (nxt != null) {
                    prev.setPrevious(prev);
                }
                if (curr == tail) {
                    tail = prev;
                }
                size--;
            }
            curr = next;
            index++;
        }
    }
    public void swap(int index1, int index2) {
        DoublyLinkedListNode<T> curr = head;
        boolean hello = true;
        int index = 0;
        if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size) {
            throw new IllegalArgumentException("You can't use this index");
        }
        else {
            
        }
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public DoublyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public DoublyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}