package Assignments.Assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int index = -1;

    protected class Node<T> extends Object{

        protected Node<T> back;
        protected Node<T> front;
        protected T data;

        protected Node(T data){
            this.data = data;
        }

    }

    public BasicDoubleLinkedList(){

    }

    public int getSize(){
        return index + 1;
    }

    public BasicDoubleLinkedList<T> addToEnd(T data){
        if(this.head == null){
            this.head = new Node<>(data);
            index++;
            return this;
        }
        Node<T> modifierNode = (tail == null) ? head : tail;
        modifierNode.back = new Node<>(data);
        modifierNode.back.front = this.tail;
        this.tail = modifierNode.back;
        index++;
        return this;
    }

    public BasicDoubleLinkedList<T> addToFront(T data){
        if(this.head == null){
            this.head = new Node<>(data);
        } else {
            this.head.front = new Node<>(data);
            this.head.front.back = this.head;
            this.head = this.head.front;
        }
        index++;
        return this;
    }

    public T getFirst(){
        return head.data;
    }

    public Node<T> getFirstNode(){
        return head;
    }

    public T getLast(){
        return tail.data;
    }

    public ListIterator<T> iterator() throws UnsupportedOperationException,
                                                NoSuchElementException{
        return new ListIterator<>() {
            Node<T> storage = new Node<>(null);

            @Override
            public boolean hasNext() {
                return storage.back != null;
            }

            @Override
            public T next() throws NoSuchElementException{
                if(!hasNext()){
                    throw new NoSuchElementException();
                }
                T queueStorage = storage.data;
                if(storage.back != null){
                    storage = storage.back;
                }
                return queueStorage;
            }

            @Override
            public boolean hasPrevious() {
                System.out.println("current: " + storage.data);
                System.out.println("previous: " + storage.front.data);
                return storage.front != null;
            }

            @Override
            public T previous() throws NoSuchElementException{
                if(!hasPrevious()){
                    throw new NoSuchElementException();
                }
                T queueStorage = storage.data;
                storage = storage.front;
                return queueStorage;
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(T t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(T t) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator){
        Node<T> storage;
        if(head != null){
            storage = head;
            while(storage != null){
                if(comparator.compare(targetData, storage.data) == 0){
                    if(storage == head){
                        head = head.front;
                    } else if(storage == tail){
                        tail = tail.back;
                        tail.front = null;
                    } else {
                        storage.back.front = storage.front;
                        storage.front.back = storage.back;
                    }
                    index--;
                    break;
                }
                storage = storage.front;
            }
        }
        return this;
    }

    public T retrieveFirstElement(){
        return (T)new Object();
    }

    public T retrieveLastElement(){
        return (T)new Object();
    }

    public ArrayList<T> toArrayList(){
        ArrayList<T> list = new ArrayList<>();
        ListIterator<T> iterator = iterator();
        while(iterator.hasNext()){
            T val = iterator.next();
            System.out.println(val);
            list.add(val);
        }
        return list;
    }




}
