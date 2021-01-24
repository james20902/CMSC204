package Assignments.Assignment2;

import java.util.ArrayList;

public class NotationStack<T> implements StackInterface<T>{

    public NotationStack(int initSize){

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
    public T pop() throws StackUnderflowException {
        return null;
    }

    @Override
    public T top() throws StackUnderflowException {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean push(T e) throws StackOverflowException {
        return false;
    }

    @Override
    public String toString(String delimiter) {
        return null;
    }

    @Override
    public void fill(ArrayList list) {

    }
}
