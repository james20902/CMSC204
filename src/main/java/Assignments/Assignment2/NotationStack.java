package Assignments.Assignment2;

import java.util.ArrayList;

/**
 * Array based stack implementation with support to store a generic class, utilizes the standard
 * First in last out access model
 *
 * @author James Pham
 * @param <T> Any object type
 */
public class NotationStack<T> implements StackInterface<T>{

    int initSize;
    int headIndex = -1;
    T[] stackArray;


    /**
     * Creates an array to hold type T and stores the expected maximum size
     * @param initSize initial array size
     */
    public NotationStack(int initSize){
        this.initSize = initSize;
        stackArray = (T[])new Object[initSize];
    }

    /**
     * Measures the index of the head variable to determine whether or not an element is present in the queue
     * @return true if headIndex does not point to object T in the array.
     */
    @Override
    public boolean isEmpty() {
        return headIndex == -1;
    }

    /**
     * Measures the index of the head variable to determine whether or not the storage array is full
     * @return true if the head index is equal to the declared initial size
     */
    @Override
    public boolean isFull() {
        return headIndex == initSize - 1;
    }

    /**
     * Get the first object at the top of the stack and remove it from the stack
     * @return The object last added to the stack
     * @throws StackUnderflowException If the stack is popped while empty
     */
    @Override
    public T pop() throws StackUnderflowException {
        if(isEmpty()){
            throw new StackUnderflowException();
        }

        return stackArray[headIndex--];
    }

    /**
     * Get the first object at the top of the stack
     * @return The object last added to the stack
     * @throws StackUnderflowException If the stack is checked while empty
     */
    @Override
    public T top() throws StackUnderflowException {
        if(isEmpty()){
            throw new StackUnderflowException();
        }
        return stackArray[headIndex];
    }

    /**
     * Return the number of elements present in the array
     * @return The number of elements present in the stack
     */
    @Override
    public int size() {
        return headIndex + 1;
    }

    /**
     * Add an element to the top of the stack
     * @param e the element to add to the top of the stack
     * @return true if the operation completed successfully
     * @throws StackOverflowException If the stack has an element pushed while full
     */
    @Override
    public boolean push(T e) throws StackOverflowException {
        if(isFull()){
            throw new StackOverflowException();
        }

        stackArray[++headIndex] = e;
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
        for(int i = 0; i < headIndex; i++){
            builder.append(stackArray[i]);
            builder.append(delimiter);
        }
        builder.append(stackArray[headIndex]);
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
     * Adds every element in a given list to the stack
     * @param list elements to be added to the stack from bottom to top
     */
    @Override
    public void fill(ArrayList<T> list) {
        for(T element : list){
            push(element);
        }
    }
}
