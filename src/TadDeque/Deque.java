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
public interface Deque<E> {
    
    public E getfirst() throws EmptyDequeException;
    
    public E getLast() throws EmptyDequeException;
    
    public int size();
    
    public boolean isEmpty();
    
    public void addFirst(E elem) throws FullDequeException;
    
    public void addLast(E elem) throws FullDequeException;
    
    public E removeFirst() throws EmptyDequeException;
    
    public E removeLast() throws EmptyDequeException;
    
    public Iterator getIterator();
    
    public Iterator getIteratorInverted();
    
}
