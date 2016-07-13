/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

/**
 *
 * @author David
 */
public class ObjectoHistorico<E> {
    
    private E elemento;
    private String dataEntrada;

    public ObjectoHistorico(E elemento, String data) {
        this.elemento = elemento;
        this.dataEntrada = data;
    }

    public E getElemento() {
        return elemento;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public void setDataEntrada(String data) {
        this.dataEntrada = data;
    }

    @Override
    public String toString() {
        return "Pontuação : " + elemento + " |  Data de entrada : (" + dataEntrada + ")";
    }

    
    
    
    
}
