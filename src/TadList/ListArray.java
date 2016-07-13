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
public class ListArray<E> implements List<E> {

    private int size;
    private E[] lista;
    private int capacidade;

    public ListArray(int capacidade) {
        size = 0;
        lista = (E[]) new Object[capacidade];
        this.capacidade = capacidade;
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
    public E get(int position) throws OutOfBoundsException {
        if (position < 0 || position > size() - 1) {
            throw new OutOfBoundsException();
        }
        return lista[position];
    }

    @Override
    public E set(int position, E elem) throws OutOfBoundsException {

        if (position < 0 || position > size() - 1) {
            throw new OutOfBoundsException();
        }
        if (elem != null) {
            lista[position] = elem;
            return elem;
        }

        return null;
    }
    
    @Override
    public void add(int position, E elem) throws OutOfBoundsException {
        if (position < 0 || position > size()) {
            throw new OutOfBoundsException();
        }
        if (elem != null) {
            lista[position] = elem;
            size++;
        }
    }

    @Override
    public E remove(int position) throws OutOfBoundsException {
        if (position < 0 || position > size() - 1) {
            throw new OutOfBoundsException();
        }
        E elem = lista[position];
        for (int i = position; i < size-1; i++) {
            lista[i] = lista[i+1];
        }
        
        size--;
        return elem;
    }

    @Override
    public void swap(int posicaoAtual, int posicaoGerada) {
        
        E aux = lista[posicaoAtual];
        lista[posicaoAtual] = lista[posicaoGerada];
        lista[posicaoGerada] = aux;

    }

}
