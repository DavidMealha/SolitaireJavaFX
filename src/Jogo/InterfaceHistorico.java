/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;


/**
 *
 * @author David
 */
public interface InterfaceHistorico<E> {

    public boolean isHistoricoEmpty();
    
    public void adicionarAoHistorico(E elem);

    public Historico historicoDiario(String data);

    public Historico historicoUltimosDias(int quantidadeDias);

    public Historico historicoMesCorrente();
}
