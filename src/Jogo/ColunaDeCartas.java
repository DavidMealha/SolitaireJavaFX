/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import PadroesSoftware.EstrategiaOrdenacaoMonteSequencial;

/**
 *
 * @author David
 */
public class ColunaDeCartas {

    private MonteDeCartas cartasOcultas;
    private MonteDeCartasSequencial cartasVistas;

    public ColunaDeCartas(EstrategiaOrdenacaoMonteSequencial est) {
        cartasOcultas = new MonteDeCartas();
        cartasVistas = new MonteDeCartasSequencial(est);
    }

    public MonteDeCartas getCartasOcultas() {
        return cartasOcultas;
    }

    public MonteDeCartasSequencial getCartasVistas() {
        return cartasVistas;
    }
    
    public double getXUltimaCarta(){
        return cartasVistas.cartaTopo()[0].getPosX();
    }
    
    public double getYUltimaCarta(){
        return cartasVistas.cartaTopo()[0].getPosY();
    }
    
    public void setXUltimaCarta(double x){
        cartasVistas.cartaTopo()[0].setPosX(x);
    }
    
    public void setYUltimaCarta(double y){
        cartasVistas.cartaTopo()[0].setPosY(y);
    }

    /**
     * Método para adicionar uma carta no monte de cartas ocultas
     *
     * @param carta
     */
    public void adicionarCartaOculta(Carta carta) {
        cartasOcultas.adicionarCarta(carta);
    }

    /**
     * Método para adicionar uma carta no monte de cartas vistas
     *
     * @param carta
     * @return true caso seja bem sucedido, caso contrário retorna false
     */
    public boolean adicionarCartaVista(Carta carta) {

        cartasVistas.adicionarCarta(carta);

        return true;

    }

    /**
     * Método para adicionar um monte sequencial ao monte de cartas vistas.
     *
     * @param monte
     * @return true caso seja bem sucedido, caso contrário retorna false
     */
    public boolean adicionarMonte(MonteDeCartasSequencial monte) {

        int size = monte.tamanhoMonte();
        int x = cartasVistas.tamanhoMonte();
        cartasVistas.adicionarMonte(monte);
        int y = cartasVistas.tamanhoMonte();

        if (x + size == y) {
            return true;
        }

        return false;
    }

    /**
     * Função para retirar a carta do topo da coluna
     *
     * @return carta retirada
     */
    public Carta retirarCartaTopo() {

        boolean val = verificaMonteSequencial();
        if (val) {
            return cartasVistas.retiraCarta();
        }
        return null;
    }

    /**
     * Função para retirar um monte sequencial da coluna
     *
     * @param nrCartas
     * @return
     */
    public MonteDeCartasSequencial retirarMonte(int nrCartas) {

        if (nrCartas <= cartasVistas.tamanhoMonte()) {
            MonteDeCartasSequencial mt = new MonteDeCartasSequencial(cartasVistas.getEstrategiaEscolhida());
            MonteDeCartas mtx = new MonteDeCartas();
            for (int i = 0; i < nrCartas; i++) {
                mtx.adicionarCarta(cartasVistas.retiraCarta());
            }
            for (int i = 0; i < nrCartas; i++) {
                mt.adicionarCarta(mtx.retiraCarta());
            }
            return mt;
        }

        return null;
    }

    public int tamanhoMonteOculto() {
        return cartasOcultas.tamanhoMonte();
    }

    public int tamanhoMonteVisto() {
        return cartasVistas.tamanhoMonte();
    }

    public boolean verificaMonteSequencial() {
        if (cartasVistas.tamanhoMonte() == 0 && cartasOcultas.tamanhoMonte() > 0) {
            cartasVistas.adicionarCarta(cartasOcultas.retiraCarta());
            return true;
        } else if (cartasVistas.tamanhoMonte() > 0) {
            return true;
        } else if(cartasVistas.tamanhoMonte() == 0 && cartasOcultas.tamanhoMonte() == 0){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {

        String mostrar = "";

        for (int j = 0; j < tamanhoMonteOculto(); j++) {
            mostrar += " * ";

        }
        mostrar += " " + cartasVistas.toString();
        
        
        mostrar += "\n";
        return mostrar;
    }

}
