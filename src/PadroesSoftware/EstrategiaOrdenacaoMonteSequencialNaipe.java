/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PadroesSoftware;

import Jogo.Carta;
import Jogo.MonteDeCartas;
import TadStack.Stack;
import TadStack.StackDynamic;

/**
 *
 * @author David
 */
public class EstrategiaOrdenacaoMonteSequencialNaipe implements EstrategiaOrdenacaoMonteSequencial {

    /**
     * A estrategia A corresponde à ordenação ascendente por naipe( Do AS para
     * REI).
     *
     * @param carta
     * @param monteDeCartas
     * @return monte de cartas com a nova carta
     */
    @Override
    public boolean adicionarCarta(Carta carta, Stack<Carta> monteDeCartas) {

        //Retorna ultima carta do monte
        Carta ultimaCarta = null;
        String naipeUltimaCarta = null;
        int valorUltimaCarta = 0;

        if (!monteDeCartas.isEmpty()) {
            ultimaCarta = monteDeCartas.peek();
            naipeUltimaCarta = ultimaCarta.getNaipe();
            valorUltimaCarta = ultimaCarta.getValorFace();
        }

        if (monteDeCartas.isEmpty() && carta.getValorFace() == 1) {
            monteDeCartas.push(carta);
            return true;
        } else if (carta.getNaipe().equals(naipeUltimaCarta) && valorUltimaCarta == carta.getValorFace() - 1) {
            monteDeCartas.push(carta);
            return true;
        }
        return false;
    }

    @Override
    public boolean adicionarMonte(MonteDeCartas monte, Stack<Carta> monteDeCartas) {

        MonteDeCartas mt = monte;
        int size = mt.tamanhoMonte();
        

        //Inverte o monte que se pretende adicionar
        Stack<Carta> sct = new StackDynamic<>();
        for (int i = 0; i < size; i++) {
            sct.push(mt.retiraCarta());
        }

        //Por cada carta do monte invertido recebido, vai tentar adicionar ao 
        //monte a que se pretende adicionar.
        Carta ct;
        //Stack<Carta> sctaux = new StackDynamic<>();
        boolean val;
        
        for (int i = 0; i < size; i++) {
            ct = mt.retiraCarta();
            val = adicionarCarta(ct, monteDeCartas);
            if(val && i == size-1){ //Caso a ultima carta do monte seja adiciona com sucesso, o método irá retornar o valor true
                return true;
            }
        }

        return false;
    }

}
