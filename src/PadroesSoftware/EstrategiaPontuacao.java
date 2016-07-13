/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PadroesSoftware;

/**
 *
 * @author David
 */
public interface EstrategiaPontuacao {
    
    /**
     * Tipo de operacao
     * 1- adicao carta a uma coluna
     * 2- adicao de uma carta a uma casa de naipe
     * 3- sequencia completa
     * @param tipoOperacao
     * @return 
     */
    public int calcularPontuacao(int tipoOperacao);
    
    public int jogadaFinal(int tempoJogo);
}
