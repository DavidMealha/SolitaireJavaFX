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
public class DequeCircular<E> implements Deque<E>{

    private int begin, end;
    private final static int CAPACIDADE_MAXIMA = 500;
    private E[] arrayElem;

    public DequeCircular() {
        begin = end = 0;
        arrayElem = (E[]) new Object[CAPACIDADE_MAXIMA];
    }

    @Override
    public int size() {
        if(isFull()){
            return arrayElem.length;
        }
        return (CAPACIDADE_MAXIMA-begin+end) % arrayElem.length;
    }

    private boolean isFull(){
        return begin==end && arrayElem[begin]!=null;
    }
    
    @Override
    public boolean isEmpty() {
        return begin==end && arrayElem[begin] == null;
    }
    
    @Override
    public E getfirst() throws EmptyDequeException {
        if(isEmpty()){
            throw new EmptyDequeException();
        }
        return arrayElem[begin];
    }

    @Override
    public E getLast() throws EmptyDequeException {
        if(isEmpty()){
            throw new EmptyDequeException();
        }
        return arrayElem[end];
    }

    @Override
    public void addFirst(E elem) throws FullDequeException {
        if(isFull()){
            throw new FullDequeException();
        }
        if(begin == 0 && arrayElem[begin] != null){
            begin = ((arrayElem.length + (begin-1)) % arrayElem.length);
        }
        arrayElem[begin] = elem;
        System.out.println("inicio sem elems" + begin);
        begin = ((arrayElem.length + (begin-1)) % arrayElem.length);
        System.out.println("inicio com elems" + begin);
        System.out.println("Fim" + end);
    }

    @Override
    public void addLast(E elem) throws FullDequeException {
        if(isFull()){
            throw new FullDequeException();
        }
        if(end == 0 && arrayElem[end] != null){
            end = (end+1) % arrayElem.length;
        }
        arrayElem[end] = elem;
        end = (end+1) % arrayElem.length;
    }

    @Override
    public E removeFirst() throws EmptyDequeException {
        E elem = getfirst();
        arrayElem[begin] = null;
        begin = (begin+1) % arrayElem.length;
        return elem;
    }

    @Override
    public E removeLast() throws EmptyDequeException {
        E elem = getLast();
        arrayElem[end] = null;
        end = (arrayElem.length + (end-1)) % arrayElem.length;
        return elem;
    }

    @Override
    public Iterator getIterator() {
        return new IteratorDeque();
    }

    @Override
    public Iterator getIteratorInverted() {
        return new IteratorDequeInverted();
    }

    private class IteratorDeque implements Iterator<E>{
        //Do inicio para o fim
        
        private int cursor;
        
        public IteratorDeque() {
            cursor = begin;
        }

        @Override
        public boolean hasNext() {
            return cursor < size();
        }

        @Override
        public E next() {
            E elem = arrayElem[cursor];
            cursor = (cursor+1) % arrayElem.length;
            return elem;
        }
    
    
}
    
    private class IteratorDequeInverted implements Iterator<E>{
        //Do fim para o inicio
        
        private int cursor;
        
        public IteratorDequeInverted() {
            cursor = end;
        }

        @Override
        public boolean hasNext() {
            return cursor > size();
        }

        @Override
        public E next() {
            E elem = arrayElem[cursor];
            cursor--;
            return elem;
        }
    
    
}
    
    
    
}
