package Assignments.Assignment2;

import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface<T> {

    public NotationQueue(int initSize){

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        return false;
    }

    @Override
    public String toString(String delimiter) {
        return null;
    }

    @Override
    public void fill(ArrayList<T> list) {

    }
}
