/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import TadList.ListArray;
import TadList.List;

/**
 *
 * @author David
 */
public class BaralhoSolitario extends Baralho {

    private final String[] naipes = {"OUROS", "ESPADAS", "COPAS", "PAUS"};
    private final String[] faces = {"AS", "DOIS", "TRES", "QUATRO", "CINCO",
            "SEIS", "SETE", "OITO", "NOVE", "DEZ", "VALETE", "DAMA", "REI"};
    
    public BaralhoSolitario() {
        super(52);
        adicionarCartas();
    }

    /**
     * Método responsável pela adição de todas as cartas possíveis ao baralho.
     */
    @Override
    public void adicionarCartas() {

        List<Carta> aux = super.getBaralho();
        int x = 0;
        
        for (int i = 0; i < naipes.length; i++) {
            for (int j = 0; j < faces.length; j++) {
                aux.add(x, new Carta(naipes[i], faces[j],j+1, i+1));
                x++;
            }

        super.setBaralho(aux);
    }
}

    /**
     * Algoritmo que consiste em baralhar as cartas todas do baralho.
     */
    @Override
    public void baralhar() {

        ListArray<Carta> aux = (ListArray<Carta>) super.getBaralho();
        
        int nrVezesBaralhar, primeiroRandom, segundoRandom;
        nrVezesBaralhar = (int) (20+Math.random()*30);
        
        int x = 0;
        while(x < nrVezesBaralhar){
            primeiroRandom = (int) (Math.random()*52);
            segundoRandom = (int) (Math.random()*52);
            aux.swap(primeiroRandom, segundoRandom);
            x++;
        }
        
        super.setBaralho(aux);
    }

    /**
     * Método responsável por retirar o número de cartas pretendidas do baralho.
     * As cartas são sempre retiradas de cima.
     *
     * @param quantidade
     * @return uma lista de cartas, com a quantidade pretendida.
     */
    @Override
    public Carta[] retirarCartas(int quantidade) throws RuntimeException{
        
        if(quantidade > super.getNrCartas()){
            throw new RuntimeException("A quantidade que pretende retirar é superior a quantidade de cartas no baralho");
        }
        Carta[] cartasRetiradas = new Carta[quantidade];

        for (int i = 0; i < quantidade; i++) {
            cartasRetiradas[i] = super.getBaralho().remove(super.getBaralho().size()-1);
        }
        
        super.setNrCartas(super.getNrCartas()-quantidade);
        
        return cartasRetiradas;

    }

    /**
     * Método que limpa por completo o baralho, fazendo com que fique sem
     * cartas.
     */
    @Override
    public void limparBaralho() {

        for (int i = 0; i < 52; i++) {
            super.getBaralho().remove(super.getBaralho().size()-1);
        }

    }


}
