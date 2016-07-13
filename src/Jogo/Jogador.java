/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class Jogador implements Serializable{
    
    private String loginName;
    private Historico historico;
    private int nrJogosJogados;

    public Jogador(String loginName) {
        this.loginName = loginName;
        historico = new Historico();
        nrJogosJogados = 0;
    }

    public String getLoginName() {
        return loginName;
    }

    public Historico getHistorico() {
        return historico;
    }

    public int getNrJogosJogados() {
        return nrJogosJogados;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
    public void incrementaNrJogosJogado(){
        nrJogosJogados++;
    }
    
    
}
