/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TadDeque;

import PadroesSoftware.Iterator;

/**
 *
 * @author David
 */
public class DequeDinamic<E> implements Deque<E> {

    private int size;
    private DNode<E> head, tail;

    public DequeDinamic() {
        size = 0;
        head = tail = null;
    }

    @Override
    public E getfirst() throws EmptyDequeException {
        if (isEmpty()) {
            throw new EmptyDequeException();
        }
        return head.getElement();

    }

    @Override
    public E getLast() throws EmptyDequeException {
        if (isEmpty()) {
            throw new EmptyDequeException();
        }
        return tail.getElement();

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
    public void addFirst(E elem) throws FullDequeException {
        if (isEmpty()) {
            DNode n = new DNode(elem, null, null);
            head = tail = n;
        } else {
            DNode n2 = new DNode(elem, null, head);
            head.setLast(n2);
            head = n2;
        }
        size++;

    }

    @Override
    public void addLast(E elem) throws FullDequeException {
        if (isEmpty()) {
            DNode n = new DNode(elem, null, null);
            head = tail = n;
        } else {
            DNode n2 = new DNode(elem, tail, null);
            tail.setNext(n2);
            tail = n2;
        }
        size++;
    }

    @Override
    public E removeFirst() throws EmptyDequeException {

        E elemento = getfirst();

        if (size == 1) {
            head = tail = null;
        } else {

            DNode no = head.getNext();
            head.setLast(null);
            head = no;

        }

        size--;
        return elemento;
    }

    @Override
    public E removeLast() throws EmptyDequeException {

        E elemento = getLast();

        if (size == 1) {
            head = tail = null;
        } else {

            DNode no = tail.getLast();
            tail.setNext(null);
            tail = no;
        }

        size--;
        return elemento;
    }

    @Override
    public Iterator getIterator() {
        return new IteratorDeque();
    }
    
    private class IteratorDeque<E> implements Iterator<E>{

        private DNode<E> cursor;
        
        public IteratorDeque() {
            cursor = (DNode<E>) head;
        }

        @Override
        public boolean hasNext() {
            return (cursor != null);
        }

        @Override
        public E next() {
            E elem = cursor.getElement();
            cursor = cursor.getNext();
            return elem;
        }
        
        
    }

    @Override
    public Iterator getIteratorInverted() {
        return new IteratorDequeInverted();
    }
    
    private class IteratorDequeInverted<E> implements Iterator<E>{

        private DNode<E> cursor;
        
        public IteratorDequeInverted() {
            cursor = (DNode<E>) tail;
        }

        @Override
        public boolean hasNext() {
            return (cursor != null);
        }

        @Override
        public E next() {
            E elem = cursor.getElement();
            cursor = cursor.getLast();
            return elem;
        }
        
        
    }

}