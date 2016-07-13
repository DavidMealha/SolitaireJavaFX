/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import PadroesSoftware.Iterator;
import TadStack.Stack;

/**
 *
 * @author David
 */
public class MonteCartasSaiTres extends MonteDeCartas {

    public MonteCartasSaiTres() {
        super();
    }

    /**
     * Método que retira as últimas três cartas inseridas no monte.
     *
     * @return
     */
    @Override
    public Carta[] retirarCartas() {

        if (super.tamanhoMonte() > 3) {
            Carta[] ct = new Carta[3];
            for (int i = 0; i < 3; i++) {
                ct[i] = super.retiraCarta();
            }
            return ct;
        }else if(super.tamanhoMonte() > 0 && super.tamanhoMonte() < 3){
            Carta[] ct = new Carta[super.tamanhoMonte()];
            for (int i = 0; i < super.tamanhoMonte(); i++) {
                ct[i] = super.retiraCarta();
            }
            return ct;
        }
        return null;
    }

    /**
     * Método que mostra as ultimas três cartas inseridas no monte
     *
     * @return
     */
    @Override
    public Carta[] cartaTopo() {

        Iterator it = super.getIterator();

        int count = 0;
        if (super.tamanhoMonte() >= 3) {
            Carta[] ct = new Carta[3];
            while (it.hasNext() && count < 3) {
                ct[count] = (Carta) it.next();
                count++;
            }
            return ct;
        }

        return null;
    }
}
