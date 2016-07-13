/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import PadroesSoftware.EstrategiaOrdenacaoMonteSequencial;
import TadStack.Stack;
import TadStack.StackDynamic;

/**
 *
 * @author David
 */
public class MonteDeCartasSequencial extends MonteDeCartas{
    
    private EstrategiaOrdenacaoMonteSequencial estrategiaEscolhida;

    public MonteDeCartasSequencial(EstrategiaOrdenacaoMonteSequencial op) {
        super();
        estrategiaEscolhida = op;
    }

    public EstrategiaOrdenacaoMonteSequencial getEstrategiaEscolhida() {
        return estrategiaEscolhida;
    }

    /**
     * Este método consiste na adição de uma carta ao monte, ou seja, no topo do
     * monte. Mas apenas adiciona caso a anterior carta seja de uma cor
     * diferente do que se quer inserir, ou seja, se o naipe que se pretende
     * inserir seja paus ou espadas, o anterior deve ser copas ou ouros. E
     * deverá seguir a ordem: "AS, 2, 3, 4, 5, 6, 7, 8, 9, 10, V, D, R".
     *
     * @param carta
     */
    @Override
    public void adicionarCarta(Carta carta) {
        boolean valor = estrategiaEscolhida.adicionarCarta(carta, super.getMonteDeCartas());
        
        if(valor){
            //super.adicionarCarta(carta);
        }else{
            throw new RuntimeException("Não foi possível adicionar carta");
        }
        
    }
    
    /**
     * Método em que é feita a adição de um monte sequencial, para outro monte.
     * @param monte 
     */
    @Override
    public void adicionarMonte(MonteDeCartas monte){
        
        boolean valor = estrategiaEscolhida.adicionarMonte(monte, super.getMonteDeCartas());
        
        if(valor){
            //super.adicionarMonte(monte);
        }else{
            System.out.println("Não foi possível adicionar monte à coluna");
        }
        
    }

}
