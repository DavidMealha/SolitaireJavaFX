/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.JogoSolitario;

import Jogo.Baralho;
import Jogo.ColunaDeCartas;
import Jogo.MonteDeCartas;
import Jogo.MonteDeCartasSequencial;

/**
 *
 * @author David
 */
public class Memento {
    
    public Baralho baralhoDeJogo;
    public int pontuacao;
    public int nrJogadas;
    public int tempoInicial;
    public MonteDeCartas casaDoBaralho;
    public MonteDeCartas monteLixo;
    public MonteDeCartas[] casaDeNaipe;
    public ColunaDeCartas[] colunas;

    public Memento(Baralho baralhoDeJogo, int pontuacao, int nrJogadas, int tempoInicial, MonteDeCartas casaDoBaralho, MonteDeCartas monteLixo, MonteDeCartas[] casaDeNaipe, ColunaDeCartas[] colunas) {
        this.baralhoDeJogo = baralhoDeJogo;
        this.pontuacao = pontuacao;
        this.nrJogadas = nrJogadas;
        this.tempoInicial = tempoInicial;
        this.casaDoBaralho = casaDoBaralho;
        this.monteLixo = monteLixo;
        this.casaDeNaipe = casaDeNaipe;
        this.colunas = colunas;
    }
    
    
}
