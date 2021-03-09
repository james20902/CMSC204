package Assignments.Assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Linked list data structure with two pointers to represent the very front and back of the list
 *
 * @author James Pham
 * @param <T> type to store
 */
public class BasicDoubleLinkedList<T> {

    protected Node<T> head;
    protected Node<T> tail;
    protected int index;

    /**
     * Internal node data structure for data storage and two pointers representing the next and previous nodes
     * @param <T> type to store
     */
    protected class Node<T> extends Object{

        protected Node<T> back;
        protected Node<T> front;
        protected T data;

        /**
         * Create new node to store data
         * @param data data to be stored
         */
        protected Node(T data){
            this.data = data;
        }

    }

    /**
     * Initialize head and tail pointers, as well as index for length tracking
     */
    public BasicDoubleLinkedList(){
        head = null;
        tail = null;
        index = -1;
    }

    /**
     * Get the current number of elements being stored (indexed starting at 1)
     * @return The current number of elements present
     */
    public int getSize(){
        return index + 1;
    }

    /**
     * Add data to the very back of the list
     * @param data the given data to store
     * @return new linked list instance with new data attached
     */
    public BasicDoubleLinkedList<T> addToEnd(T data){
        Node<T> storage = new Node<>(data);
        //if there is nothing present
        if (index == -1) {
            //just set the first element to be both things
            //this assumes the tail will never be null unless the head is null as well
            head = storage;
            tail = storage;
        } else {
            //otherwise change the pointers to accept a new node
            tail.back = storage;
            tail.back.front = tail;
            tail = tail.back;
        }
        index++;
        return this;
    }

    /**
     * Add data to the very front of the list
     * @param data the given data to store
     * @return new linked list instance with new data attached
     */
    public BasicDoubleLinkedList<T> addToFront(T data){
        //if there is nothing present (assuming tail isn't populated)
        if(head == null){
            //just set the first element to be the data
            head = new Node<>(data);
        } else {
            //otherwise change the pointers to accept a new node
            head.front = new Node<>(data);
            head.front.back = head;
            head = head.front;
        }
        index++;
        return this;
    }

    /**
     * Get the first element in the list WITHOUT changing the list
     * @return the first element or null if none
     */
    public T getFirst(){
        return head.data;
    }

    /**
     * Get the last element in the list WITHOUT changing the list
     * @return the last element or null of none
     */
    public T getLast(){
        return tail.data;
    }

    /**
     * Get iterator representing the current items in the linked list
     * @return Iterator object for later processing
     * @throws UnsupportedOperationException if iterator operation
     * @throws NoSuchElementException if element is requested but none available
     */
    public ListIterator<T> iterator() throws UnsupportedOperationException,
                                                NoSuchElementException{
        return new ListIterator<>() {
            Node<T> current = head;
            Node<T> previous = null;

            /**
             * Check if the back pointer is not null
             * @return whether or not the back pointer is null
             */
            @Override
            public boolean hasNext() {
                return current != null;
            }

            /**
             * Get the next element in the chain relative to the current pointer
             * @return next data
             * @throws NoSuchElementException if back pointer is null
             */
            @Override
            public T next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T currentData = current.data;
                previous = current;
                current = current.back;
                return currentData;
            }

            /**
             * Check if the front pointer is not null
             * @return whether or not the front pointer is null
             */
            public boolean hasPrevious() {
                return previous != null;
            }

            /**
             * Get the previous element in the chain relative to the current pointer
             * @return previous data
             * @throws NoSuchElementException if front pointer is null
             */
            public T previous() throws NoSuchElementException {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                T previousData = previous.data;
                current = previous;
                previous = previous.front;
                return previousData;
            }
            /**
             * Unsupported operation
             * @return N/A
             */
            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException();
            }
            /**
             * Unsupported operation
             * @return N/A
             */
            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }
            /**
             * Unsupported operation
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
            /**
             * Unsupported operation
             */
            @Override
            public void set(T t) {
                throw new UnsupportedOperationException();
            }
            /**
             * Unsupported operation
             */
            @Override
            public void add(T t) {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * Remove given data from chain based on comparator to check if entries are equals
     * @param targetData The data to remove
     * @param comparator Comparator to check equality
     * @return new linked list instance with data removed
     */
    public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator){
        Node<T> storage = head;
        while(storage != null){
            if(comparator.compare(targetData, storage.data) == 0){
                if(storage == head){
                    head = head.back;
                } else if(storage == tail){
                    tail = tail.front;
                    tail.back = null;
                } else {
                    storage.front.back = storage.back;
                    storage.back.front = storage.front;
                }
                index--;
                break;
            }
            storage = storage.back;
        }
        return this;
    }

    /**
     * Gets the first element and removes it from the list
     * @return the first element
     */
    public T retrieveFirstElement(){
        T data = head.data;
        head = head.back;
        return data;
    }

    /**
     * Gets the last element and removes it from the list
     * @return the last element
     */
    public T retrieveLastElement(){
        T data = tail.data;
        tail = tail.front;
        return data;
    }

    /**
     * Get all elements in the list and stream them into an array
     * @return array list for later processing
     */
    public ArrayList<T> toArrayList(){
        ArrayList<T> list = new ArrayList<>();
        ListIterator<T> iterator = iterator();
        while(iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }




}
