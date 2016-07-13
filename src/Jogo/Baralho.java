/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import TadList.List;
import TadList.ListArray;

/**
 *
 * @author David
 */
public abstract class Baralho {
    
    private List<Carta> baralho;
    private int nrCartas;

    public Baralho(int nrCartas) {
        baralho = new ListArray<>(nrCartas);
        this.nrCartas = nrCartas;
    }

    public List<Carta> getBaralho() {
        return baralho;
    }

    public int getNrCartas() {
        return nrCartas;
    }

    public void setBaralho(List<Carta> baralho) {
        this.baralho = baralho;
    }

    public void setNrCartas(int nrCartas) {
        this.nrCartas = nrCartas;
    }
    
    public void adicionarCarta(Carta carta){
        this.baralho.add(baralho.size(), carta);
    }
    
    public abstract void adicionarCartas();
    
    
    
    public abstract void baralhar();
    
    public abstract Carta[] retirarCartas(int quantidade);
    
    public abstract void limparBaralho();
}
