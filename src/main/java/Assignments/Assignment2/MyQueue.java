package Assignments.Assignment2;

import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> {

    int initSize;
    int tailIndex = -1;
    Object[] queueArray;

    public MyQueue(int initSize){
        this.initSize = initSize;
        queueArray = new Object[initSize];
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
        T head = (T)queueArray[0];
        for(int i = 0; i < tailIndex; i++){
            queueArray[i] = queueArray[i+1];
        }
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