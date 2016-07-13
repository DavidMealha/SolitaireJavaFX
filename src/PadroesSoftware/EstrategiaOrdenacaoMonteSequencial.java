/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PadroesSoftware;

import Jogo.Carta;
import Jogo.MonteDeCartas;
import TadStack.Stack;

/**
 *
 * @author David
 */
public interface EstrategiaOrdenacaoMonteSequencial {
    
    public boolean adicionarCarta(Carta carta, Stack<Carta> monteDeCartas);
    
    public boolean adicionarMonte(MonteDeCartas monte, Stack<Carta> monteDeCartas);
}
