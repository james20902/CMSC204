package Assignments.Assignment3;

import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{

    Comparator<T> comparatorStorage;

    /**
     * Initialize and accept comparator
     * @param comparator2 comparator for sorting
     */
    public SortedDoubleLinkedList(Comparator<T> comparator2){
        comparatorStorage = comparator2;
    }

    /**
     * Add data based on comparator results
     * @param data data to be added
     * @return instance of linked list with new data attached
     */
    public SortedDoubleLinkedList<T> add(T data){
        Node<T> storage = new Node<T>(data);

        //if no element
        if (index == -1) {
            head = storage;
            tail = storage;
        } else if (comparatorStorage.compare(head.data, data) > 0) {
            head.front = storage;
            storage.back = head;
            head = storage;
        } else if (comparatorStorage.compare(tail.data, data) < 0) {
            tail.back = storage;
            storage.front = tail;
            tail = storage;
        } else {
            Node<T> pointer = head.back;
            while (pointer.back != null && comparatorStorage.compare(data, pointer.data) >= 1) {
                pointer = pointer.back;
            }
            storage.back = pointer;
            storage.front = pointer.front;
            pointer.front.back = storage;
            pointer.front = storage;
        }

        index++;
        return this;
    }

    /**
     *
     * @param data the given data to store
     * @return nothing
     * @throws UnsupportedOperationException
     */
    @Override
    public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Invalid operation for sorted list.");
    }

    /**
     *
     * @param data the given data to store
     * @return nothing
     * @throws UnsupportedOperationException
     */
    @Override
    public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Invalid operation for sorted list.");
    }

    /**
     * Get iterator representing the current items in the linked list
     * @return Iterator object for later processing
     */
    @Override
    public ListIterator<T> iterator(){
        return super.iterator();
    }

    /**
     *
     * @param data the target data to remove
     * @param comparator Comparator to check equality
     * @return instance of linked list with new data attached
     */
    @Override
    public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator){
        return (SortedDoubleLinkedList<T>) super.remove(data, comparator);
    }

}
