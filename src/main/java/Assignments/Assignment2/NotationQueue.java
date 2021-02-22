package Assignments.Assignment2;

import java.util.ArrayList;

/**
 * Array based queue implementation with support to store a generic class, utilizes the standard
 * First in First out access model
 *
 * @author James Pham
 * @param <T> Any object type
 */
public class NotationQueue<T> implements QueueInterface<T> {

    int initSize;
    int tailIndex = -1;
    T[] queueArray;

    /**
     * Creates an array to hold type T and stores the expected maximum size
     * @param initSize initial array size
     */
    public NotationQueue(int initSize){
        this.initSize = initSize;
        queueArray = (T[]) new Object[initSize];
    }

    /**
     * Measures the index of the tail variable to determine whether or not an element is present in the queue
     * @return true if tailIndex does not point to object T in the array.
     */
    @Override
    public boolean isEmpty() {
        return tailIndex == -1;
    }

    /**
     * Measures the index of the tail variable to determine whether or not the storage array is full
     * @return true if the tail index is equal to the declared initial size
     */
    @Override
    public boolean isFull() {
        return tailIndex == initSize - 1;
    }

    /**
     * Remove the first object in line and bring all other objects up in the index
     * @return the latest object in the queue prior to removal
     * @throws QueueUnderflowException if a dequeue is attempted while nothing is in storage
     */
    @Override
    public T dequeue() throws QueueUnderflowException {
        if(isEmpty()){
            throw new QueueUnderflowException();
        }
        T head = queueArray[0];
        //copy every element from index 1 onwards, and replace itself with that copy
        System.arraycopy(queueArray, 1, queueArray, 0, tailIndex);
        tailIndex--;
        return head;
    }

    /**
     * Return the number of elements present in the array
     * @return the number of elements present in the queue
     */
    @Override
    public int size() {
        return tailIndex + 1;
    }

    /**
     * Add an element to the end of the storage array
     * @param e the element to add to the end of the Queue
     * @return whether or not the element was added successfully
     * @throws QueueOverflowException If attempting to add an object to queue when no space is available
     */
    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if(isFull()){
            throw new QueueOverflowException();
        }

        queueArray[++tailIndex] = e;
        return true;
    }

    /**
     * String method for debugging/visual observation
     * @param delimiter String to separate each element by
     * @return the final concatenated string with every element separated by the delimiter
     */
    @Override
    public String toString(String delimiter) {
        if(isEmpty()){
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < tailIndex; i++){
            builder.append(queueArray[i]);
            builder.append(delimiter);
        }
        builder.append(queueArray[tailIndex]);
        return builder.toString();
    }

    /**
     * String method for debugging/visual observation delimited by nothing
     * @return the final concatenated string with every element
     */
    @Override
    public String toString(){
        return toString("");
    }

    /**
     * Adds every element in a given list to the queue
     * @param list elements to be added to the Queue
     */
    @Override
    public void fill(ArrayList<T> list) {
        for(T element : list){
            enqueue(element);
        }
    }
}
