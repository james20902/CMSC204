package Assignments.Assignment2;

import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface<T> {

    int initSize;
    int tailIndex = -1;
    T[] queueArray;

    public NotationQueue(int initSize){
        this.initSize = initSize;
        queueArray = (T[]) new Object[initSize];
    }

    @Override
    public boolean isEmpty() {
        return tailIndex == -1;
    }

    @Override
    public boolean isFull() {
        return tailIndex == initSize - 1;
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if(isEmpty()){
            throw new QueueUnderflowException();
        }
        T head = queueArray[0];
        System.arraycopy(queueArray, 1, queueArray, 0, tailIndex);
        tailIndex--;
        return head;
    }

    @Override
    public int size() {
        return tailIndex + 1;
    }

    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if(isFull()){
            throw new QueueOverflowException();
        }

        queueArray[++tailIndex] = e;
        return true;
    }

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

    @Override
    public String toString(){
        return toString("");
    }

    @Override
    public void fill(ArrayList<T> list) {
        for(T element : list){
            enqueue(element);
        }
    }
}
