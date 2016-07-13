/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.JogoSolitario;

import Jogo.Baralho;
import Jogo.Baralho;
import Jogo.Carta;
import Jogo.Carta;
import Jogo.ColunaDeCartas;
import Jogo.JogoSolitario.Memento;
import Jogo.MonteDeCartasSequencial;
import Jogo.MonteDeCartasSequencial;
import java.util.Observable;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author David
 */
public abstract class Jogo extends Observable implements Cloneable{
    
    private Baralho baralhoDeJogo;
    protected int pontuacao;
    protected int nrJogadas;
    protected int tempoInicial;
    private int tempoJogo;

    public Jogo(Baralho bt) {
        this.pontuacao = 0;
        this.nrJogadas = 0;
        this.tempoJogo = 0;
        this.baralhoDeJogo = bt;
        
        long time = System.currentTimeMillis();
        this.tempoInicial = (int) (time/1000);
        
    }

    public Baralho getBaralhoDeJogo() {
        return baralhoDeJogo;
    }

    public void setBaralhoDeJogo(Baralho baralhoDeJogo) {
        this.baralhoDeJogo = baralhoDeJogo;
    }
    
    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    
    public void contarJogada(){
        this.nrJogadas++;
    }
    
    public void adicionarPontos(int pontos){
        this.pontuacao += pontos;
    }
    
    public int calcTempoJogo(){
        long time = System.currentTimeMillis(); 
        int aux = (int) ((time/1000) - tempoInicial);
        this.tempoJogo = aux;
        
        return tempoJogo;
    }
    
    public abstract ColunaDeCartas[] getColunas();
    public abstract void distribuirCartas();
    public abstract void calcularPontuacaoFinal();
    public abstract boolean adicionarCartaColuna(Carta carta, int nrColuna);
    public abstract Carta retirarCartaColuna(int nrColuna);
    public abstract boolean adicionarMonteColuna(MonteDeCartasSequencial monte, int nrColuna);
    public abstract MonteDeCartasSequencial retirarMonteColuna(int nrColuna, int monteSize);
    public abstract boolean adicionarCartaCasaNaipe(Carta carta, int nrCasa);
    public abstract void virarCarta();
    public abstract Carta retirarCartaMonteLixo();
    public abstract Memento save();
    public abstract void restore(Object objMemento);
    public abstract void desenharCartas(GraphicsContext gc);
    //public abstract Object clone();
    
}
