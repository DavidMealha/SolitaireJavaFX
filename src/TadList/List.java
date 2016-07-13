/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TadList;

/**
 *
 * @author David
 */
public interface List<E> {

    public int size();

    public boolean isEmpty();

    public E get(int position) throws OutOfBoundsException;

    public E set(int position, E elem) throws OutOfBoundsException;
     
    public void add(int position, E elem) throws OutOfBoundsException;

    public E remove(int position) throws OutOfBoundsException;
    
    public void swap(int posicaoAtual, int posicaoGerada);
    
}
