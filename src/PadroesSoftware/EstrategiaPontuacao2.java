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
public class EstrategiaPontuacao2 implements EstrategiaPontuacao{

    @Override
    public int calcularPontuacao(int tipoOperacao) {
        
        if(tipoOperacao == 1){
            return -10;
        }else if(tipoOperacao == 2){
            return -10;
        }else if(tipoOperacao == 3){
            return 1000;
        }
        
        return 0;
    }

    @Override
    public int jogadaFinal(int tempoJogo) {
        return 0;
    }


    
}
