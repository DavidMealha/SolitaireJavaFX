/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TadDeque;

/**
 *
 * @author David
 */
public class DNode<E> {

    private E element;
    private DNode<E> next;
    private DNode<E> last;

    public DNode(E element, DNode last, DNode next) {
        this.element = element;
        this.last = last;
        this.next = next;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public DNode<E> getNext() {
        return next;
    }

    public void setNext(DNode<E> next) {
        this.next = next;
    }
    
    public DNode<E> getLast() {
        return last;
    }

    public void setLast(DNode<E> last) {
        this.last = last;
    }
}

