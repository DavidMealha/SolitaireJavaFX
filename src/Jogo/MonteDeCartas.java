/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import PadroesSoftware.Iterator;
import TadStack.StackDynamic;
import TadStack.Stack;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class MonteDeCartas implements InterfaceMonteDeCartas {

    private Stack<Carta> adaptee;

    public MonteDeCartas() {
        adaptee = new StackDynamic<>();
    }

    public Stack<Carta> getMonteDeCartas() {
        return adaptee;
    }

    public void setMonteDeCartas(Stack<Carta> adaptee) {
        this.adaptee = adaptee;
    }

    /**
     *
     * @return É retornada a quandiade de cartas no monte.
     */
    @Override
    public int tamanhoMonte() {
        return adaptee.size();
    }

    /**
     * Método que verifica se o monte de cartas encontra-se vazio ou não.
     *
     * @return um valor boleano, se for true o monte está vazio
     */
    @Override
    public boolean monteVazio() {
        return adaptee.isEmpty();
    }

    /**
     * Este método consiste na adição de uma carta ao monte, ou seja, no topo do
     * monte.
     *
     * @param carta
     */
    @Override
    public void adicionarCarta(Carta carta) {
        if (carta != null) {
            adaptee.push(carta);
        }
    }

    /**
     * Este método consiste na adição de outro monte ao monte de cartas, ou
     * seja, são adicionadas todas as cartas do monte que é recebido, no topo da
     * pilha.
     *
     * @param monte
     */
    @Override
    public void adicionarMonte(MonteDeCartas monte) {

        MonteDeCartas mt = monte;
        Carta ct;
        ArrayList stc = new ArrayList();
        int size = mt.tamanhoMonte();

        for (int i = 0; i < size; i++) {
            ct = mt.retiraCarta();
            stc.add(ct);
        }

        for (int i = 0; i < stc.size(); i++) {
            this.adaptee.push((Carta) stc.get(i));
        }

    }

    /**
     * @return a carta que está no topo do monte de cartas.
     */
    @Override
    public Carta[] cartaTopo(){
        Carta[]ct = new Carta[1];
        ct[0] = adaptee.peek();
        return ct;
    }
    
    /**
     * Método que retira a última carta inserida no monte, a que está no topo.
     *
     * @return um objecto do tipo Carta
     */
    @Override
    public Carta retiraCarta() {
        if (!monteVazio()) {
            return adaptee.pop();
        }
        return null;
    }

    /**
     * Método que não será utilizado, apenas é implementado para ser chamada no
     * monte de cartas sai tres.
     *
     * @return
     */
    @Override
    public Carta[] retirarCartas() {
        return null;
    }

    @Override
    public Iterator getIterator() {
        return adaptee.getIterator();
    }

    @Override
    public String toString() {
        
        String mt = "";
        
        Stack<Carta> sct = new StackDynamic<>();
        int size = tamanhoMonte();
        
        Iterator it = getIterator();
        for (int i = 0; i < size; i++) {
            sct.push((Carta) it.next());
        }
        
        for (int i = 0; i < size; i++) {
            mt += sct.pop().toString();
        }
        
        return mt;
    }
    
    

}
