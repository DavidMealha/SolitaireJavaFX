/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import PadroesSoftware.Iterator;

/**
 *
 * @author David
 */
public interface InterfaceMonteDeCartas {
    
    public int tamanhoMonte();
    
    public boolean monteVazio();
    
    public Carta[] cartaTopo();
    
    public void adicionarCarta(Carta carta);
    
    public void adicionarMonte(MonteDeCartas monte);
    
    public Carta retiraCarta();
    
    public Carta[] retirarCartas();
    
    public Iterator getIterator();
}
