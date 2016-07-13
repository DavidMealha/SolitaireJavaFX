/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TadStack;

import PadroesSoftware.Iterator;


/**
 *
 * @author David
 */
public class StackDynamic<E> implements Stack<E> {

    private Node<E> top;
    private int size;

    public StackDynamic() {
        top = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getElement();
    }

    @Override
    public void push(E elem) {
        Node no = new Node(elem, top);
        top = no;
        size++;
    }

    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E element = top.getElement();
        top = top.getNext();
        size--;
        
        return element;
    }

    @Override
    public Iterator<E> getIterator() {
        return new IteratorDynamic();
    }
    
    private class IteratorDynamic implements Iterator<E>{
        //Do topo para a base
        
        private Node<E> cursor;
        
        public IteratorDynamic() {
            cursor = top;
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() {
            E elem = cursor.getElement();
            cursor = cursor.getNext();
            return elem;
        }
}

}


