/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.JogoSolitario;

import Jogo.ColunaDeCartas;
import PadroesSoftware.EstrategiaOrdenacaoMonteSequencialInfantil;
import PadroesSoftware.EstrategiaOrdenacaoMonteSequencialValor;
import PadroesSoftware.EstrategiaPontuacao;

/**
 *
 * @author David
 */
public class JogoSolitarioInfantil extends JogoSolitarioBase{

    public JogoSolitarioInfantil(EstrategiaPontuacao estrategiaPontos) {
        super(estrategiaPontos);
        
    }
    
    @Override
    public void inicializarColunas(){
        
        for (int i = 0; i < colunas.length; i++) {
            colunas[i] = new ColunaDeCartas(new EstrategiaOrdenacaoMonteSequencialInfantil());
        }
        
    }
    
}
