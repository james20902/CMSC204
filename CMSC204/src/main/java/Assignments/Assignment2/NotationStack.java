package Assignments.Assignment2;

import java.util.ArrayList;

public class NotationStack<T> implements StackInterface<T>{

    int initSize;
    int headIndex = -1;
    Object[] stackArray;

    public NotationStack(int initSize){
        this.initSize = initSize;
        stackArray = new Object[initSize];
    }

    @Override
    public boolean isEmpty() {
        return headIndex == -1;
    }

    @Override
    public boolean isFull() {
        return headIndex == initSize - 1;
    }

    @Override
    public T pop() throws StackUnderflowException {
        if(isEmpty()){
            throw new StackUnderflowException();
        }

        return (T)stackArray[headIndex--];
    }

    @Override
    public T top() throws StackUnderflowException {
        return (T)stackArray[headIndex];
    }

    @Override
    public int size() {
        return headIndex + 1;
    }

    @Override
    public boolean push(T e) throws StackOverflowException {
        if(isFull()){
            throw new StackOverflowException();
        }

        stackArray[++headIndex] = e;
        return true;
    }

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

    @Override
    public String toString(){
        return toString("");
    }

    @Override
    public void fill(ArrayList<T> list) {
        for(T element : list){
            push(element);
        }
    }
}
