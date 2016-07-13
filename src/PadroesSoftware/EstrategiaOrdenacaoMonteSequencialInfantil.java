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
public class EstrategiaOrdenacaoMonteSequencialInfantil implements EstrategiaOrdenacaoMonteSequencial {

    @Override
    public boolean adicionarCarta(Carta carta, Stack<Carta> monteDeCartas) {

        //Retorna ultima carta do monte
        Carta ultimaCarta = null;
        int valorFaceUltimaCarta = 0;
        if (!monteDeCartas.isEmpty()) {
            ultimaCarta = monteDeCartas.peek();
            //Retorna valor da face da ultima carta do monte
            valorFaceUltimaCarta = ultimaCarta.getValorFace();
        }

        if (monteDeCartas.isEmpty()) {
            
            monteDeCartas.push(carta);
            return true;
            
        } else if ((valorFaceUltimaCarta == carta.getValorFace() + 1)) {
            
            monteDeCartas.push(carta);
            return true;

        } else if ((valorFaceUltimaCarta == carta.getValorFace() + 1)) {

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
        Iterator it = monte.getIterator();
        while(it.hasNext()){
            sct.push((Carta) it.next());
        }

        //Por cada carta do monte invertido recebido, vai tentar adicionar ao 
        //monte a que se pretende adicionar.
        Carta ct;
        //Stack<Carta> sctaux = new StackDynamic<>();
        boolean val;
        for (int i = 0; i < size; i++) {
            ct = sct.pop();
            val = adicionarCarta(ct, monteDeCartas);
            if (val && i == size - 1) { //Caso a ultima carta do monte seja adiciona com sucesso, o método irá retornar o valor true
                return true;
            }
        }

        return false;
    }

}
